package com.nomadays.eventuate.domain;

import io.eventuate.EntityWithIdAndVersion;
import io.eventuate.EventHandlerContext;
import io.eventuate.EventHandlerMethod;
import io.eventuate.EventSubscriber;

import com.nomadays.eventuate.command.DeleteTodoCommand;
import com.nomadays.eventuate.common.event.TodoDeletionRequestedEvent;

import java.util.concurrent.CompletableFuture;


@EventSubscriber(id = "todoCommandSideEventHandlers")
public class TodoCommandWorkflow {

    @EventHandlerMethod
    public CompletableFuture<EntityWithIdAndVersion<TodoAggregate>> deleteTodo(EventHandlerContext<TodoDeletionRequestedEvent> ctx) {
        String todoId = ctx.getEvent().getTodoId();

        return ctx.update(TodoAggregate.class, todoId, new DeleteTodoCommand());
    }

}
