package au.juy.juyerp.mapper;

import au.juy.juyerp.entity.Orders;
import au.juy.juyerp.form.OrderSearchForm;
import au.juy.juyerp.mo.OrdersMO;
import au.juy.juyerp.vo.OrdersVO;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.sun.org.apache.xpath.internal.operations.Or;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author Samuel Ju
 * @since 2024-04-05
 */
public interface OrdersMapper extends BaseMapper<Orders> {
    List<OrdersVO> ordersVOList(Long index, Long length, OrderSearchForm orderSearchForm);

    Long ordersVOCount(OrderSearchForm orderSearchForm);

    Long batchVerify(OrdersMO ordersMO);

    Long batchInvalidate(OrdersMO ordersMO);

}
