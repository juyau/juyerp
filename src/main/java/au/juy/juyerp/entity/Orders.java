package au.juy.juyerp.entity;

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
 * @since 2024-04-05
 */
@Data
  @EqualsAndHashCode(callSuper = false)
    public class Orders implements Serializable {

    private static final long serialVersionUID=1L;

      private String orderNo;

    private Integer supplierId;

    private Integer invalid;

    private Integer status;

    private String verifyPerson;

    private LocalDateTime verifyDate;

    private String employeeName;

    private LocalDateTime orderDate;

    private String remark;

    private Integer orderType;


}
