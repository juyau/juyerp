package au.juy.juyerp.service.impl;

import au.juy.juyerp.entity.Orders;
import au.juy.juyerp.mapper.OrdersMapper;
import au.juy.juyerp.service.OrdersService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author Samuel Ju
 * @since 2024-04-05
 */
@Service
public class OrdersServiceImpl extends ServiceImpl<OrdersMapper, Orders> implements OrdersService {

}
