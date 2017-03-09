package com.nomadays.eventuate.backend;


import io.eventuate.DispatchedEvent;
import io.eventuate.EventHandlerMethod;
import io.eventuate.EventSubscriber;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.nomadays.eventuate.common.event.TodoCreatedEvent;
import com.nomadays.eventuate.common.event.TodoDeletedEvent;
import com.nomadays.eventuate.common.event.TodoUpdatedEvent;

@EventSubscriber(id = "todoQuerySideEventHandlers")
public class TodoQueryWorkflow {

  private TodoQueryService todoQueryService;
  private Logger logger = LoggerFactory.getLogger(getClass());

  public TodoQueryWorkflow(TodoQueryService todoQueryService) {
    this.todoQueryService = todoQueryService;
  }

  @EventHandlerMethod
  public void create(DispatchedEvent<TodoCreatedEvent> de) {
    Todo todo = new Todo(de.getEvent().getTodo());
    todo.setId(de.getEntityId());
    logger.info("TodoCreatedEvent {}", todo);
    todoQueryService.save(todo);
  }

  @EventHandlerMethod
  public void delete(DispatchedEvent<TodoDeletedEvent> de) {
	logger.info("TodoDeletedEvent {}", de.getEntityId());
    todoQueryService.remove(de.getEntityId());
  }

  @EventHandlerMethod
  public void update(DispatchedEvent<TodoUpdatedEvent> de) {
    Todo todo = new Todo(de.getEvent().getTodo());
    todo.setId(de.getEntityId());
    logger.info("TodoUpdatedEvent {}", todo);
    todoQueryService.save(todo);
  }
}
