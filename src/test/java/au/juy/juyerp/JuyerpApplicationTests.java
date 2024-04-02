package au.juy.juyerp;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeFormatterBuilder;

@SpringBootTest
class JuyerpApplicationTests {

	@Test
	void contextLoads() {
		String date = "15/2/2024";
		LocalDateTime localDate = this.excelDateStringToLocalDate(date);


		System.out.println(localDate);


	}


	LocalDateTime excelDateStringToLocalDate(String date){
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
		DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("ddMMyyyy");

		LocalDateTime localDateTime = LocalDateTime.of(LocalDate.parse(stringBuffer, dateTimeFormatter), LocalTime.of(0,0,0));

		return localDateTime;
	}

}
