package au.juy.juyerp.entity;

import java.math.BigDecimal;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import java.time.LocalDateTime;
import java.io.Serializable;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * <p>
 * 
 * </p>
 *
 * @author Samuel Ju
 * @since 2024-03-24
 */
@Data
  @EqualsAndHashCode(callSuper = false)
    public class MaterialInput implements Serializable {

    private static final long serialVersionUID=1L;

      @TableId(value = "material_input_id", type = IdType.AUTO)
      private Integer materialInputId;

    private Integer materialId;

    private String materialCode;

    private String materialName;

    private String batchNo;

    private String orderId;

    private Integer supplierId;

    private String supplierName;

    private Integer storageId;

    private String storageName;

    private String style;

    private String unitName;

    private BigDecimal orderCount;

    private String userName;

    private LocalDateTime orderDate;

    private Integer status;

    private String orderNo;


}
