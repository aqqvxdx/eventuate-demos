package com.nomadays.cao.customer;

import com.nomadays.cao.common.domain.Money;

public class CreateCustomerCommand implements CustomerCommand {
  private final String name;
  private final Money creditLimit;

  public CreateCustomerCommand(String name, Money creditLimit) {
    this.name = name;
    this.creditLimit = creditLimit;
  }

  public Money getCreditLimit() {
    return creditLimit;
  }

  public String getName() {
    return name;
  }
}
