package au.juy.juyerp;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

@SpringBootApplication
@MapperScan("au.juy.juyerp.mapper")
public class JuyerpApplication {

	public static void main(String[] args) {
		SpringApplication.run(JuyerpApplication.class, args);
	}

}
