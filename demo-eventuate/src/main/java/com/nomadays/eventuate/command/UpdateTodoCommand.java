package com.nomadays.eventuate.command;

import com.nomadays.eventuate.model.TodoInfo;

public class UpdateTodoCommand implements TodoCommand {
    private String id;
    private TodoInfo todo;

    public UpdateTodoCommand(String id, TodoInfo todo) {
        this.id = id;
        this.todo = todo;
    }

    public String getId() {
        return id;
    }

    public TodoInfo getTodo() {
        return todo;
    }
}
