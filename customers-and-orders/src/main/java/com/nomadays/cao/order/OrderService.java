package com.nomadays.cao.order;

import io.eventuate.EntityWithIdAndVersion;

import java.util.concurrent.CompletableFuture;

import com.nomadays.cao.common.domain.Money;

public interface OrderService {

    CompletableFuture<EntityWithIdAndVersion<Order>> createOrder(String customerId, Money orderTotal);
}
