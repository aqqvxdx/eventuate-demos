package com.nomadays.eventuate.common.event;

import com.nomadays.eventuate.model.TodoInfo;

public class TodoCreatedEvent implements TodoEvent {

    TodoInfo todo;
    
    // for JSON serialization
    private TodoCreatedEvent() {
    	
    }

    public TodoCreatedEvent(TodoInfo todo) {
        this.todo = todo;
    }

    public TodoInfo getTodo() {
        return todo;
    }

    public void setTodo(TodoInfo todo) {
        this.todo = todo;
    }
}