package com.nomadays.eventuate.common.event;


import io.eventuate.Event;
import io.eventuate.EventEntity;

@EventEntity(entity = "com.nomadays.eventuate.domain.TodoAggregate")
public interface TodoEvent extends Event {
}
