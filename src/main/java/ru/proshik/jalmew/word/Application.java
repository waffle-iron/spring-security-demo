package ru.proshik.jalmew.word;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@SpringBootApplication
@EnableTransactionManagement(proxyTargetClass = true)
public class Application {

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}
}
