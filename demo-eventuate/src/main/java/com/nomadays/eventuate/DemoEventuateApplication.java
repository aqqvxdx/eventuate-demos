package com.nomadays.eventuate;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Import;

import io.eventuate.javaclient.driver.EventuateDriverConfiguration;

@SpringBootApplication
@Import({EventuateDriverConfiguration.class})
public class DemoEventuateApplication {

	public static void main(String[] args) {
		SpringApplication.run(DemoEventuateApplication.class, args);
	}
}
