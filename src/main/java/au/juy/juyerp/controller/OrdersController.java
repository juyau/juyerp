package au.juy.juyerp.controller;


import au.juy.juyerp.form.OrderSearchForm;
import au.juy.juyerp.mapper.OrdersMapper;
import au.juy.juyerp.service.OrdersService;
import au.juy.juyerp.service.SupplierService;
import au.juy.juyerp.utils.PageObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author Samuel Ju
 * @since 2024-04-05
 */
@Controller
@RequestMapping("/orders")
public class OrdersController {

    @Autowired
    private OrdersService ordersService;

    @Autowired
    private SupplierService supplierService;

    @RequestMapping ("/list")
    public String list(Model model, PageObject pageObject, OrderSearchForm orderSearchForm){
        model.addAttribute("page", ordersService.ordersList(pageObject, orderSearchForm));
        model.addAttribute("supplierList", supplierService.list());
        model.addAttribute("form", orderSearchForm);
        return "ordersList";
    }

    @PostMapping("/batchDelete")
    @ResponseBody
    public String batchDelete(String orderNoArr){
        boolean delete = ordersService.batchDelete(orderNoArr);
        if(delete) return "success";
        return "failed";
    }

}

