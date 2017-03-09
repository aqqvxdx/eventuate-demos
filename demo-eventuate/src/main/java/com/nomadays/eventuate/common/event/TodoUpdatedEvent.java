package com.nomadays.eventuate.common.event;

import com.nomadays.eventuate.model.TodoInfo;

public class TodoUpdatedEvent implements TodoEvent {

    private TodoInfo todo;
    
    @SuppressWarnings("unused")
	private TodoUpdatedEvent() {
    	
    }

    public TodoUpdatedEvent(TodoInfo todo) {
        this.todo = todo;
    }

    public TodoInfo getTodo() {
        return todo;
    }

    public void setTodo(TodoInfo todo) {
        this.todo = todo;
    }
}