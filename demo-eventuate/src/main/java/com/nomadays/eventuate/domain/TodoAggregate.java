package com.nomadays.eventuate.domain;


import io.eventuate.Event;
import io.eventuate.EventUtil;
import io.eventuate.ReflectiveMutableCommandProcessingAggregate;

import java.util.Collections;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.nomadays.eventuate.command.CreateTodoCommand;
import com.nomadays.eventuate.command.DeleteTodoCommand;
import com.nomadays.eventuate.command.TodoCommand;
import com.nomadays.eventuate.command.UpdateTodoCommand;
import com.nomadays.eventuate.common.event.TodoCreatedEvent;
import com.nomadays.eventuate.common.event.TodoDeletedEvent;
import com.nomadays.eventuate.common.event.TodoUpdatedEvent;
import com.nomadays.eventuate.model.TodoInfo;

public class TodoAggregate extends ReflectiveMutableCommandProcessingAggregate<TodoAggregate, TodoCommand> {

    private TodoInfo todo;
    private boolean deleted;
    
    private Logger logger = LoggerFactory.getLogger(getClass());

    public List<Event> process(CreateTodoCommand cmd) {
        if (this.deleted) {
            return Collections.emptyList();
        }
        logger.debug("processing {}", cmd);
        return EventUtil.events(new TodoCreatedEvent(cmd.getTodo()));
    }

    public List<Event> process(UpdateTodoCommand cmd) {
        if (this.deleted) {
            return Collections.emptyList();
        }
        logger.debug("processing {}", cmd);
        return EventUtil.events(new TodoUpdatedEvent(cmd.getTodo()));
    }

    public List<Event> process(DeleteTodoCommand cmd) {
        if (this.deleted) {
            return Collections.emptyList();
        }
        logger.debug("processing {}", cmd);
        return EventUtil.events(new TodoDeletedEvent());
    }


    public void apply(TodoCreatedEvent event) {
    	logger.debug("applying {}", event);
        this.todo = event.getTodo();
    }

    public void apply(TodoUpdatedEvent event) {
    	logger.debug("applying {}", event);
        this.todo = event.getTodo();
    }

    public void apply(TodoDeletedEvent event) {
    	logger.debug("applying {}", event);
        this.deleted = true;
    }

    public TodoInfo getTodo() {
        return todo;
    }

}


