package com.nomadays.cao.customers.web.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.nomadays.cao.common.domain.Money;
import com.nomadays.cao.customer.CustomerService;

import java.util.concurrent.CompletableFuture;

@RestController
public class CustomerController {

    private CustomerService customerService;

    @Autowired
    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }

    @RequestMapping(value = "/customers", method = RequestMethod.POST)
    public CompletableFuture<CreateCustomerResponse> createCustomer(@RequestBody CreateCustomerRequest createCustomerRequest) {
        return customerService.createCustomer(createCustomerRequest.getName(), createCustomerRequest.getCreditLimit())
                .thenApply(ewidv -> new CreateCustomerResponse(ewidv.getEntityId()));
    }
    
    @GetMapping("/customers")
    public CreateCustomerRequest generate(){
    	return new CreateCustomerRequest("John", new Money(100));
    }
}
