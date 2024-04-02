package au.juy.juyerp.service;

import au.juy.juyerp.entity.MaterialInput;
import com.baomidou.mybatisplus.extension.service.IService;

import java.io.InputStream;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author Samuel Ju
 * @since 2024-03-24
 */
public interface MaterialInputService extends IService<MaterialInput> {

    public boolean excelImport(InputStream inputStream);

}
