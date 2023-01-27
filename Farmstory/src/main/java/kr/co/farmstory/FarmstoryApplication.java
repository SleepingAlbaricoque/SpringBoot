package kr.co.farmstory;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.filter.HiddenHttpMethodFilter;

@SpringBootApplication
public class FarmstoryApplication {

	public static void main(String[] args) {
		SpringApplication.run(FarmstoryApplication.class, args);
	}


}
