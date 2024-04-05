package au.juy.juyerp.service;

import au.juy.juyerp.entity.MaterialInput;
import au.juy.juyerp.form.MaterialInputSearchForm;
import au.juy.juyerp.utils.ImportResult;
import au.juy.juyerp.utils.MaterialInputExportModel;
import au.juy.juyerp.utils.PageObject;
import com.baomidou.mybatisplus.extension.service.IService;

import java.io.InputStream;
import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author Samuel Ju
 * @since 2024-03-24
 */
public interface MaterialInputService extends IService<MaterialInput> {

    public ImportResult excelImport(InputStream inputStream);
    public PageObject materialInputList(PageObject pageObject, MaterialInputSearchForm materialInputSearchForm);

    List<MaterialInputExportModel> getExportList();
    boolean verifyOrStockin(Integer status, String idArray);
}
