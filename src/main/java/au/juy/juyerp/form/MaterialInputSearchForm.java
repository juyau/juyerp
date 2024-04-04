package au.juy.juyerp.form;

import lombok.Data;

@Data
public class MaterialInputSearchForm {
    private Integer supplierId;
    private String materialName;
    private String batchNo;
    private Integer status;
    private String orderDate1;
    private String orderDate2;
}
