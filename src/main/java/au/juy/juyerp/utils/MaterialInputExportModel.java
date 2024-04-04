package au.juy.juyerp.utils;

import com.alibaba.excel.annotation.ExcelProperty;
import lombok.Data;

import java.math.BigDecimal;

@Data
public class MaterialInputExportModel {
    @ExcelProperty("Material Code")
    private String materialCode;
    @ExcelProperty("Material Name")
    private String materialName;
    @ExcelProperty("Specification")
    private String style;
    @ExcelProperty("Unit")
    private String unitName;
    @ExcelProperty("Batch No.")
    private String batchNo;
    @ExcelProperty("Purchase Order")
    private String orderId;
    @ExcelProperty("Supplier Name")
    private String supplierName;
    @ExcelProperty("Storage Name")
    private String storageName;
    @ExcelProperty("Qty")
    private BigDecimal orderCount;
    @ExcelProperty("Operator")
    private String userName;
    @ExcelProperty("Order Date")
    private String orderDate;
    @ExcelProperty("Order Status")
    private String status;
    @ExcelProperty("Order No.")
    private String orderNo;
}
