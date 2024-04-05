package au.juy.juyerp.utils;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;

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

    public static String createOrderNo(Integer count,Integer orderType){
        // order number format example - VO20240416000001
        StringBuilder stringBuilder = new StringBuilder();
        switch (orderType){
            case 1:
                stringBuilder.append("VO");
                break;
            case 2:
                stringBuilder.append("OR");
                break;
            case 3:
                stringBuilder.append("MI");
                break;
            case 4:
                stringBuilder.append("MR");
                break;
        }
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyyMMdd");
        String currentDateString = simpleDateFormat.format(new Date());
        stringBuilder.append(currentDateString);
        count++;
        StringBuilder sixDigitNumber = new StringBuilder(count+"");
        while (sixDigitNumber.length() < 6){
            sixDigitNumber.insert(0, "0");
        }
        stringBuilder.append(sixDigitNumber);
        return stringBuilder.toString();
    }
}
