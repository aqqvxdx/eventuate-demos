package com.nomadays.eventuate.command;

import com.nomadays.eventuate.model.TodoInfo;

public class CreateTodoCommand implements TodoCommand {

    private TodoInfo todo;

    public CreateTodoCommand(TodoInfo todo) {
        this.todo = todo;
    }

    public TodoInfo getTodo() {
        return todo;
    }
}
