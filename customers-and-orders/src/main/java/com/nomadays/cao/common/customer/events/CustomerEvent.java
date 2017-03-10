package com.nomadays.cao.common.customer.events;


import io.eventuate.Event;
import io.eventuate.EventEntity;

@EventEntity(entity = "com.nomadays.cao.customer.Customer")
public interface CustomerEvent extends Event {
}
