package au.juy.juyerp.service.impl;

import au.juy.juyerp.entity.Material;
import au.juy.juyerp.mapper.MaterialMapper;
import au.juy.juyerp.service.MaterialService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author Samuel Ju
 * @since 2024-03-28
 */
@Service
public class MaterialServiceImpl extends ServiceImpl<MaterialMapper, Material> implements MaterialService {

}
