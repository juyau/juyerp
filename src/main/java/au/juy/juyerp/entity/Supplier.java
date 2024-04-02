package au.juy.juyerp.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import java.io.Serializable;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * <p>
 * 
 * </p>
 *
 * @author Samuel Ju
 * @since 2024-03-28
 */
@Data
  @EqualsAndHashCode(callSuper = false)
    public class Supplier implements Serializable {

    private static final long serialVersionUID=1L;

      @TableId(value = "supplier_id", type = IdType.AUTO)
      private Integer supplierId;

    private String supplierCode;

    private String supplierName;

    private String address;

    private String country;

    private String province;

    private String city;

    private String contact;

    private String email;

    private String phone;

    private String taxId;

    private String bankName;

    private String bankAccount;

    private String bankChairman;

    private String remark;


}
