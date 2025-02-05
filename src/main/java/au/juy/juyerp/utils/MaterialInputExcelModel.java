package au.juy.juyerp.utils;

import com.alibaba.excel.annotation.ExcelProperty;
import lombok.Data;

import java.math.BigDecimal;

@Data
public class MaterialInputExcelModel {
    @ExcelProperty("Supplier Code")
    private String supplierCode;
    @ExcelProperty("Receive Date")
    private String orderDate;
    @ExcelProperty("Storage Code")
    private String storageCode;
    @ExcelProperty("Material Code")
    private String materialCode;
    @ExcelProperty("Material Name")
    private String materialName;
    @ExcelProperty("Specification")
    private String style;
    @ExcelProperty("Unit")
    private String unitName;
    @ExcelProperty("Purchase Order")
    private String orderId;
    @ExcelProperty("Batch No.")
    private String batchNo;
    @ExcelProperty("Qty")
    private BigDecimal orderCount;
}
