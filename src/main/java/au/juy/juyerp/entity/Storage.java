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
    public class Storage implements Serializable {

    private static final long serialVersionUID=1L;

      @TableId(value = "storage_id", type = IdType.AUTO)
      private Integer storageId;

    private String storageCode;

    private String storageName;

    private String chairman;

    private String phone;

    private String position;


}
