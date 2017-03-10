package com.nomadays.cao.orders.web.controllers;


public class CreateOrderResponse {
  private String orderId;

  public CreateOrderResponse() {
  }

  public CreateOrderResponse(String orderId) {
    this.orderId = orderId;

  }

  public String getOrderId() {
    return orderId;
  }
}
