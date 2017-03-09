package com.nomadays.eventuate.backend;


import io.eventuate.CompletableFutureUtil;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.concurrent.CompletableFuture;

import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;


public class TodoQueryService {

    private MongoTemplate mongoTemplate;

    public TodoQueryService(MongoTemplate mongoTemplate) {
        this.mongoTemplate = mongoTemplate;
    }

    public Todo save(Todo todo) {
        mongoTemplate.save(todo);
        return todo;
    }

    public List<Todo> getAll() {
        return mongoTemplate.findAll(Todo.class);
    }

    public void remove(String id) {
        mongoTemplate.remove(new Query(Criteria.where("id").is(id)), Todo.class);
    }

    public void removeAll() {
    	mongoTemplate.remove(new Query(), Todo.class);
    }

    public CompletableFuture<Todo> findById(String todoId) {
        Todo res = mongoTemplate.findById(todoId, Todo.class);
        if (res != null) {
            return CompletableFuture.completedFuture(res);
        }
        return CompletableFutureUtil.failedFuture(new NoSuchElementException("No todo with given id found"));
    }


}
