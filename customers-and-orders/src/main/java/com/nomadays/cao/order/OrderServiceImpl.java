package com.nomadays.cao.order;

import io.eventuate.AggregateRepository;
import io.eventuate.EntityWithIdAndVersion;

import java.util.concurrent.CompletableFuture;

import com.nomadays.cao.common.domain.Money;

public class OrderServiceImpl implements OrderService {

  private final AggregateRepository<Order, OrderCommand> orderRepository;

  public OrderServiceImpl(AggregateRepository<Order, OrderCommand> orderRepository) {
    this.orderRepository = orderRepository;
  }

  @Override
  public CompletableFuture<EntityWithIdAndVersion<Order>>
        createOrder(String customerId, Money orderTotal) {
    return orderRepository.save(new CreateOrderCommand(customerId, orderTotal));
  }
}
