package au.juy.juyerp.service;

import au.juy.juyerp.entity.Orders;
import au.juy.juyerp.form.OrderSearchForm;
import au.juy.juyerp.utils.PageObject;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author Samuel Ju
 * @since 2024-04-05
 */
public interface OrdersService extends IService<Orders> {

    PageObject ordersList(PageObject pageObject, OrderSearchForm orderSearchForm);

    boolean batchDelete(String orderNoArray);

    boolean batchVerify(String orderNoArr);

    boolean batchInvalidate(String orderNoArr);
}
