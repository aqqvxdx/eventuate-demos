package com.nomadays.eventuate.domain;

import io.eventuate.Event;
import io.eventuate.ReflectiveMutableCommandProcessingAggregate;
import com.nomadays.eventuate.command.DeleteTodosCommand;
import com.nomadays.eventuate.command.TodoCommand;
import com.nomadays.eventuate.common.event.TodoDeletionRequestedEvent;

import java.util.List;
import java.util.stream.Collectors;


public class TodoBulkDeleteAggregate extends ReflectiveMutableCommandProcessingAggregate<TodoBulkDeleteAggregate, TodoCommand> {

    public List<Event> process(DeleteTodosCommand cmd) {
        return cmd.getIds()
                .stream()
                .map(TodoDeletionRequestedEvent::new)
                .collect(Collectors.toList());
    }

    public void apply(TodoDeletionRequestedEvent event) {

    }
}
