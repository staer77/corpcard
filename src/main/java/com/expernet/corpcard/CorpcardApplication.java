package com.expernet.corpcard;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.sql.DataSource;

@SpringBootApplication(exclude = {SecurityAutoConfiguration.class})
@EnableScheduling
public class CorpcardApplication {

	public static void main(String[] args) {
		SpringApplication.run(CorpcardApplication.class, args);
	}

}
