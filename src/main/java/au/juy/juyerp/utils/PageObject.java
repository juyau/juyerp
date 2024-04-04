package au.juy.juyerp.utils;

import lombok.Data;

import java.util.List;

@Data
public class PageObject {
    private Long current = 1L;
    private Long size = 5L;
    private Long total;
    private List data;

}
