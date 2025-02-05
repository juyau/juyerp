package au.juy.juyerp.mapper;

import au.juy.juyerp.entity.MaterialInput;
import au.juy.juyerp.mo.MaterialInputMO;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author Samuel Ju
 * @since 2024-03-24
 */
public interface MaterialInputMapper extends BaseMapper<MaterialInput> {
    int verify(MaterialInputMO materialInputMO);

}
