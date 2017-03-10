package com.nomadays.cao.customer;

import io.eventuate.EntityWithIdAndVersion;

import java.util.concurrent.CompletableFuture;

import com.nomadays.cao.common.domain.Money;

public interface CustomerService {

  CompletableFuture<EntityWithIdAndVersion<Customer>> createCustomer(String name, Money creditLimit);
}
