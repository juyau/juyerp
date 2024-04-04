package au.juy.juyerp.utils;

import lombok.Data;

@Data
public class ImportResult {
    // 0 for success, -1 for failure
    private Integer code;
    private String meg;
}
