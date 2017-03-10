package com.nomadays.cao.customer;

import io.eventuate.AggregateRepository;
import io.eventuate.EntityWithIdAndVersion;

import java.util.concurrent.CompletableFuture;

import com.nomadays.cao.common.domain.Money;

public class CustomerServiceImpl implements CustomerService {

  private final AggregateRepository<Customer, CustomerCommand> customerRepository;

  public CustomerServiceImpl(AggregateRepository<Customer, CustomerCommand> customerRepository) {
    this.customerRepository = customerRepository;
  }

  @Override
  public CompletableFuture<EntityWithIdAndVersion<Customer>> createCustomer(String name, Money creditLimit) {
    return customerRepository.save(new CreateCustomerCommand(name, creditLimit));
  }
}
