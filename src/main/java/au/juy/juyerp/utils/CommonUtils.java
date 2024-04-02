package au.juy.juyerp.utils;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

public class CommonUtils {
    public static LocalDateTime excelDateStringToLocalDate(String date){
        String[] split = date.split("/");
        StringBuilder stringBuffer = new StringBuilder();
        for (String s : split) {
            System.out.println(s);
            if(s.length() < 2){
                stringBuffer.append("0").append(s);
            } else {
                stringBuffer.append(s);
            }
        }
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyyMMdd");

        return LocalDateTime.of(LocalDate.parse(stringBuffer, dateTimeFormatter), LocalTime.of(0,0,0));
    }
}
