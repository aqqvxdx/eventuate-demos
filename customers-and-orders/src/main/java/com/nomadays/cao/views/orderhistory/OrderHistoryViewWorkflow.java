package com.nomadays.cao.views.orderhistory;

import io.eventuate.DispatchedEvent;
import io.eventuate.EventHandlerMethod;
import io.eventuate.EventSubscriber;
import org.springframework.beans.factory.annotation.Autowired;

import com.nomadays.cao.common.customer.events.CustomerCreatedEvent;
import com.nomadays.cao.common.domain.Money;
import com.nomadays.cao.common.order.OrderApprovedEvent;
import com.nomadays.cao.common.order.OrderCreatedEvent;
import com.nomadays.cao.common.order.OrderRejectedEvent;

@EventSubscriber(id = "orderHistoryWorkflow")
public class OrderHistoryViewWorkflow {

  private OrderHistoryViewService orderHistoryViewService;


  @Autowired
  public OrderHistoryViewWorkflow(OrderHistoryViewService orderHistoryViewService) {
    this.orderHistoryViewService = orderHistoryViewService;
  }

  @EventHandlerMethod
  public void createCustomer(DispatchedEvent<CustomerCreatedEvent> de) {
    String customerId = de.getEntityId();
    orderHistoryViewService.createCustomer(customerId, de.getEvent().getName(), de.getEvent().getCreditLimit());
  }

  @EventHandlerMethod
  public void createOrder(DispatchedEvent<OrderCreatedEvent> de) {
    String customerId = de.getEvent().getCustomerId();
    String orderId = de.getEntityId();
    Money orderTotal = de.getEvent().getOrderTotal();
    orderHistoryViewService.addOrder(customerId, orderId, orderTotal);
  }

  @EventHandlerMethod
  public void orderApproved(DispatchedEvent<OrderApprovedEvent> de) {
    String customerId = de.getEvent().getCustomerId();
    String orderId = de.getEntityId();
    orderHistoryViewService.approveOrder(customerId, orderId);  }

  @EventHandlerMethod
  public void orderRejected(DispatchedEvent<OrderRejectedEvent> de) {
    String customerId = de.getEvent().getCustomerId();
    String orderId = de.getEntityId();
    orderHistoryViewService.rejectOrder(customerId, orderId);
  }


}
