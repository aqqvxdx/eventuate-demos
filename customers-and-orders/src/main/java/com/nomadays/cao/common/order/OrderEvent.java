package com.nomadays.cao.common.order;

import io.eventuate.Event;
import io.eventuate.EventEntity;

@EventEntity(entity = "com.nomadays.cao.order.Order")
public interface OrderEvent extends Event {
}
