package au.juy.juyerp.service.impl;

import au.juy.juyerp.entity.Material;
import au.juy.juyerp.entity.MaterialInput;
import au.juy.juyerp.entity.Storage;
import au.juy.juyerp.entity.Supplier;
import au.juy.juyerp.form.MaterialInputSearchForm;
import au.juy.juyerp.mapper.MaterialInputMapper;
import au.juy.juyerp.mapper.MaterialMapper;
import au.juy.juyerp.mapper.StorageMapper;
import au.juy.juyerp.mapper.SupplierMapper;
import au.juy.juyerp.service.MaterialInputService;
import au.juy.juyerp.utils.*;
import com.alibaba.excel.EasyExcel;
import com.alibaba.excel.context.AnalysisContext;
import com.alibaba.excel.event.AnalysisEventListener;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.apache.commons.lang.StringUtils;
import org.apache.poi.util.StringUtil;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.InputStream;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author Samuel Ju
 * @since 2024-03-24
 */
@Service
public class MaterialInputServiceImpl extends ServiceImpl<MaterialInputMapper, MaterialInput> implements MaterialInputService {

    @Autowired
    private MaterialInputMapper materialInputMapper;

    @Autowired
    private MaterialMapper materialMapper;

    @Autowired
    private SupplierMapper supplierMapper;

    @Autowired
    private StorageMapper storageMapper;
    @Override
    public ImportResult excelImport(InputStream inputStream) {
        // read excel data list
        List<MaterialInputExcelModel> list = new ArrayList<>();
        try {
            EasyExcel.read(inputStream)
                    .head(MaterialInputExcelModel.class)
                    .sheet()
                    .registerReadListener(new AnalysisEventListener<MaterialInputExcelModel>() {
                        @Override
                        public void invoke(MaterialInputExcelModel data, AnalysisContext analysisContext) {
                            list.add(data);
                        }
                        @Override
                        public void doAfterAllAnalysed(AnalysisContext analysisContext) {
                            System.out.println("excel file analysis completed.");
                        }
                    })
                    .doRead();
        } catch (Exception e) {
            e.getStackTrace();
        }

        // persist excel data list
        ImportResult importResult = new ImportResult();

        // row to record row number of excel file
        int row =0;
        for (MaterialInputExcelModel materialInputExcelModel : list) {
            row++;
            MaterialInput materialInput = new MaterialInput();
            BeanUtils.copyProperties(materialInputExcelModel, materialInput);

            // query and set material ID
            QueryWrapper<Material> materialQueryWrapper = new QueryWrapper<>();
            materialQueryWrapper.eq("material_code", materialInputExcelModel.getMaterialCode());
            Material material = materialMapper.selectOne(materialQueryWrapper);
            if (material == null) {
                importResult.setCode(-1);
                importResult.setMeg("Row " + row + " Error, Material not exist.");
                return importResult;
            }
            materialInput.setMaterialId(material.getMaterialId());

            // query and set supplier id and name
            QueryWrapper<Supplier> supplierQueryWrapper = new QueryWrapper<>();
            supplierQueryWrapper.eq("supplier_code", materialInputExcelModel.getSupplierCode());
            Supplier supplier = supplierMapper.selectOne(supplierQueryWrapper);

            if (supplier == null) {
                importResult.setCode(-1);
                importResult.setMeg("Row " + row + " Error, Supplier not exist.");
                return importResult;
            }
            materialInput.setSupplierId(supplier.getSupplierId());
            materialInput.setSupplierName(supplier.getSupplierName());

            // query and set storage id and name
            QueryWrapper<Storage> storageQueryWrapper = new QueryWrapper<>();
            storageQueryWrapper.eq("storage_code", materialInputExcelModel.getStorageCode());
            Storage storage = storageMapper.selectOne(storageQueryWrapper);
            if (storage == null) {
                importResult.setCode(-1);
                importResult.setMeg("Row " + row + " Error, Storage not exist.");
                return importResult;
            }
            materialInput.setStorageId(storage.getStorageId());
            materialInput.setStorageName(storage.getStorageName());

            // set username, to be updated
            materialInput.setUserName("TestUser");
            materialInput.setStatus(0);

            materialInput.setOrderDate(CommonUtils.excelDateStringToLocalDate(materialInputExcelModel.getOrderDate()));

            int insert = materialInputMapper.insert(materialInput);
            if(insert !=1) {
                importResult.setCode(-1);
                importResult.setMeg("Import failed.");
                return importResult;
            };

            //TODO if some record insert failed, partial insert acceptable?
        }

        importResult.setCode(0);
        importResult.setMeg("Import success.");
        return importResult;

    }

    @Override
    public PageObject materialInputList(PageObject pageObject, MaterialInputSearchForm materialInputSearchForm) {
        Page<MaterialInput> page = new Page<>(pageObject.getCurrent(), pageObject.getSize());

        QueryWrapper<MaterialInput> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq(Objects.nonNull(materialInputSearchForm.getSupplierId()), "supplier_id", materialInputSearchForm.getSupplierId())
                .like(StringUtils.isNotBlank(materialInputSearchForm.getMaterialName()), "material_name", materialInputSearchForm.getMaterialName())
                .eq(Objects.nonNull(materialInputSearchForm.getStatus()), "status", materialInputSearchForm.getStatus())
                .like(StringUtils.isNotBlank(materialInputSearchForm.getBatchNo()), "batch_no", materialInputSearchForm.getBatchNo())
                .between(StringUtils.isNotBlank(materialInputSearchForm.getOrderDate1()) && StringUtils.isNotBlank(materialInputSearchForm.getOrderDate2()),
                        "order_date", materialInputSearchForm.getOrderDate1(), materialInputSearchForm.getOrderDate2());

        Page<MaterialInput> materialInputPage = materialInputMapper.selectPage(page, queryWrapper);

        PageObject resultPageObject = new PageObject();
        resultPageObject.setCurrent(materialInputPage.getCurrent());
        resultPageObject.setSize(materialInputPage.getSize());
        resultPageObject.setTotal(materialInputPage.getTotal());
        resultPageObject.setData(materialInputPage.getRecords());
        return resultPageObject;
    }

    @Override
    public List<MaterialInputExportModel> getExportList() {
        List<MaterialInput> materialInputs = materialInputMapper.selectList(null);
        List<MaterialInputExportModel> list = new ArrayList<>();

        for (MaterialInput materialInput : materialInputs) {
            MaterialInputExportModel materialInputExportModel = new MaterialInputExportModel();
            BeanUtils.copyProperties(materialInput, materialInputExportModel);
            // convert date to string for excel
            String formattedDate = materialInput.getOrderDate().format(DateTimeFormatter.ofPattern("dd-MM-yyyy"));
            materialInputExportModel.setOrderDate(formattedDate);
            // show status name instead of status code
            String status = "";
            switch (materialInput.getStatus()) {
                case 0:
                    status = "unverified";
                    break;
                case 1:
                    status = "verified";
                    break;
                case 2:
                    status = "received";
                    break;
            }
            materialInputExportModel.setStatus(status);

            list.add(materialInputExportModel);

        }

        return list;
    }

    @Override
    public boolean verify(Integer status, String idArray) {
        // need to check status again, in case other user just did verify ??
        boolean flag = false;
        switch (status) {
            case 1:
                // verify
                String[] ids = idArray.split(",");
                for (String id : ids) {
                    MaterialInput materialInput = materialInputMapper.selectById(id);

                    assert materialInput != null;
                    if (materialInput.getStatus() == 0){
                        materialInput.setStatus(1);
                    }

                    int updated = materialInputMapper.updateById(materialInput);
                    if (updated != 1) return false;
                }
                flag = true;
                break;
            case 2:
                // receive
                break;
        }


        return flag;
    }
}
