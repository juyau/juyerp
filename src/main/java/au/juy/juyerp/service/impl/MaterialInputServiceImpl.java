package au.juy.juyerp.service.impl;

import au.juy.juyerp.entity.Material;
import au.juy.juyerp.entity.MaterialInput;
import au.juy.juyerp.entity.Storage;
import au.juy.juyerp.entity.Supplier;
import au.juy.juyerp.mapper.MaterialInputMapper;
import au.juy.juyerp.mapper.MaterialMapper;
import au.juy.juyerp.mapper.StorageMapper;
import au.juy.juyerp.mapper.SupplierMapper;
import au.juy.juyerp.service.MaterialInputService;
import au.juy.juyerp.utils.CommonUtils;
import au.juy.juyerp.utils.MaterialInputExcelModel;
import com.alibaba.excel.EasyExcel;
import com.alibaba.excel.context.AnalysisContext;
import com.alibaba.excel.event.AnalysisEventListener;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

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
    public boolean excelImport(InputStream inputStream) {
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
        for (MaterialInputExcelModel materialInputExcelModel : list) {
            MaterialInput materialInput = new MaterialInput();
            BeanUtils.copyProperties(materialInputExcelModel, materialInput);

            // query and set material ID
            QueryWrapper<Material> materialQueryWrapper = new QueryWrapper<>();
            materialQueryWrapper.eq("material_code", materialInputExcelModel.getMaterialCode());
            Material material = materialMapper.selectOne(materialQueryWrapper);
            materialInput.setMaterialId(material.getMaterialId());

            // query and set supplier id and name
            QueryWrapper<Supplier> supplierQueryWrapper = new QueryWrapper<>();
            supplierQueryWrapper.eq("supplier_code", materialInputExcelModel.getSupplierCode());
            Supplier supplier = supplierMapper.selectOne(supplierQueryWrapper);
            materialInput.setSupplierId(supplier.getSupplierId());
            materialInput.setSupplierName(supplier.getSupplierName());

            // query and set storage id and name
            QueryWrapper<Storage> storageQueryWrapper = new QueryWrapper<>();
            storageQueryWrapper.eq("storage_code", materialInputExcelModel.getStorageCode());
            Storage storage = storageMapper.selectOne(storageQueryWrapper);
            materialInput.setStorageId(storage.getStorageId());
            materialInput.setStorageName(storage.getStorageName());

            // set username, to be updated
            materialInput.setUserName("TestUser");
            materialInput.setStatus(0);

            materialInput.setOrderDate(CommonUtils.excelDateStringToLocalDate(materialInputExcelModel.getOrderDate()));

            int insert = materialInputMapper.insert(materialInput);
            if(insert !=1) return false;

            //TODO if some record insert failed, partial insert acceptable?
        }

        return true;
    }
}
