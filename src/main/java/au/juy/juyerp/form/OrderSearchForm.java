package au.juy.juyerp.form;

import lombok.Data;

@Data
public class OrderSearchForm {
    private Integer supplierId;
    private Integer invalid;
    private Integer status;
    private String employeeName;
    private String orderDate1;
    private String orderDate2;
}
