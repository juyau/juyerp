package au.juy.juyerp.service.impl;

import au.juy.juyerp.entity.OrderDetail;
import au.juy.juyerp.entity.Orders;
import au.juy.juyerp.entity.Supplier;
import au.juy.juyerp.form.OrderSearchForm;
import au.juy.juyerp.mapper.OrderDetailMapper;
import au.juy.juyerp.mapper.OrdersMapper;
import au.juy.juyerp.mapper.SupplierMapper;
import au.juy.juyerp.service.OrdersService;
import au.juy.juyerp.utils.PageObject;
import au.juy.juyerp.vo.OrdersVO;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.sun.org.apache.xpath.internal.operations.Or;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
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
public class OrdersServiceImpl extends ServiceImpl<OrdersMapper, Orders> implements OrdersService {

    @Autowired
    private OrdersMapper ordersMapper;

    @Autowired
    private OrderDetailMapper orderDetailMapper;

    @Autowired
    private SupplierMapper supplierMapper;

    @Override
    public PageObject ordersList(PageObject pageObject, OrderSearchForm orderSearchForm) {

        Long index = (pageObject.getCurrent() - 1) * pageObject.getSize();
        Long length = pageObject.getSize();
        PageObject resultPageObject = new PageObject();
        resultPageObject.setCurrent(pageObject.getCurrent());
        pageObject.setSize(pageObject.getSize());

        List<OrdersVO> ordersVOList = ordersMapper.ordersVOList(index, length, orderSearchForm);
        pageObject.setData(ordersVOList);

        pageObject.setTotal(ordersMapper.ordersVOCount(orderSearchForm));

        return pageObject;
    }

    @Override
    public boolean batchDelete(String orderNoArray) {
        if(orderNoArray == null) return false;
        String[] orderNos = orderNoArray.split(",");
        ArrayList<String> list = new ArrayList<>();
        Collections.addAll(list, orderNos);

        QueryWrapper<Orders> ordersQueryWrapper = new QueryWrapper<>();
        ordersQueryWrapper.in("order_no", list);
        int deletedOrder = ordersMapper.delete(ordersQueryWrapper);
        if (deletedOrder ==0) return false;

        QueryWrapper<OrderDetail> orderDetailQueryWrapper = new QueryWrapper<>();
        orderDetailQueryWrapper.in("order_no", list);
        int deletedOrderDetail = orderDetailMapper.delete(orderDetailQueryWrapper);
        if(deletedOrderDetail == 0) return false;
        return true;
    }

}
