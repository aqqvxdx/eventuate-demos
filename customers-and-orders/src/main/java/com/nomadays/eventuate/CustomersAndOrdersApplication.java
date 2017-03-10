package com.nomadays.eventuate;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Import;

import com.nomadays.cao.customer.CustomerConfiguration;
import com.nomadays.cao.order.OrderConfiguration;
import com.nomadays.cao.views.orderhistory.OrderHistoryViewConfiguration;

import io.eventuate.javaclient.driver.EventuateDriverConfiguration;

@SpringBootApplication
@Import({
	EventuateDriverConfiguration.class, 
	CustomerConfiguration.class, 
	OrderConfiguration.class,
	OrderHistoryViewConfiguration.class
})
@ComponentScan({
	"com.nomadays.cao.customers.web.controllers",
	"com.nomadays.cao.orders.web.controllers"
})
public class CustomersAndOrdersApplication {

	public static void main(String[] args) {
		SpringApplication.run(CustomersAndOrdersApplication.class, args);
	}
}
