package com.nomadays.eventuate.domain;

import io.eventuate.AggregateRepository;
import io.eventuate.EntityWithIdAndVersion;

import com.nomadays.eventuate.command.*;
import com.nomadays.eventuate.model.TodoInfo;

import java.util.List;
import java.util.concurrent.CompletableFuture;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;



public class TodoService {

    private final AggregateRepository<TodoAggregate, TodoCommand> aggregateRepository;
    private final AggregateRepository<TodoBulkDeleteAggregate, TodoCommand> bulkDeleteAggregateRepository;
    
    private Logger logger = LoggerFactory.getLogger(getClass());


    public TodoService(AggregateRepository<TodoAggregate, TodoCommand> todoRepository, AggregateRepository<TodoBulkDeleteAggregate, TodoCommand> bulkDeleteAggregateRepository) {
        this.aggregateRepository = todoRepository;
        this.bulkDeleteAggregateRepository = bulkDeleteAggregateRepository;
    }

    public CompletableFuture<EntityWithIdAndVersion<TodoAggregate>> save(TodoInfo todo) {
    	logger.debug("save {}", todo);
        return aggregateRepository.save(new CreateTodoCommand(todo));
    }

    public CompletableFuture<EntityWithIdAndVersion<TodoAggregate>> remove(String id) {
    	logger.debug("remove {}", id);
        return aggregateRepository.update(id, new DeleteTodoCommand());
    }

    public CompletableFuture<EntityWithIdAndVersion<TodoAggregate>> update(String id, TodoInfo newTodo) {
    	logger.debug("update {}", id);
        return aggregateRepository.update(id, new UpdateTodoCommand(id, newTodo));
    }

    public CompletableFuture<EntityWithIdAndVersion<TodoBulkDeleteAggregate>> deleteAll(List<String> ids) {
    	logger.debug("deleteAll {}", ids);
        return bulkDeleteAggregateRepository.save(new DeleteTodosCommand(ids));
    }
}
