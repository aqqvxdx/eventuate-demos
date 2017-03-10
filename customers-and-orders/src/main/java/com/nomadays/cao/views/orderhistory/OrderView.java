package com.nomadays.cao.views.orderhistory;

import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Version;
import org.springframework.data.mongodb.core.mapping.Document;

import com.nomadays.cao.common.domain.Money;
import com.nomadays.cao.common.order.OrderState;

@Document
public class OrderView {

  @Id
  private String id;

  private OrderState state;
  private Money orderTotal;


  public OrderView() {
  }

  public OrderView(String id, Money orderTotal) {
    this.id = id;
    this.orderTotal = orderTotal;
    this.state = OrderState.CREATED;
  }

  public Money getOrderTotal() {
    return orderTotal;
  }

  public OrderState getState() {
    return state;
  }
}
