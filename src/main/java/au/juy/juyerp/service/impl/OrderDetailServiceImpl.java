package au.juy.juyerp.service.impl;

import au.juy.juyerp.entity.OrderDetail;
import au.juy.juyerp.entity.Orders;
import au.juy.juyerp.entity.Supplier;
import au.juy.juyerp.mapper.OrderDetailMapper;
import au.juy.juyerp.mapper.OrdersMapper;
import au.juy.juyerp.mapper.SupplierMapper;
import au.juy.juyerp.service.OrderDetailService;
import au.juy.juyerp.vo.OrdersVO;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author Samuel Ju
 * @since 2024-04-05
 */
@Service
public class OrderDetailServiceImpl extends ServiceImpl<OrderDetailMapper, OrderDetail> implements OrderDetailService {


}
