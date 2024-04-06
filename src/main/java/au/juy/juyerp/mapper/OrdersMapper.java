package au.juy.juyerp.mapper;

import au.juy.juyerp.entity.Orders;
import au.juy.juyerp.form.OrderSearchForm;
import au.juy.juyerp.vo.OrdersVO;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

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

}
