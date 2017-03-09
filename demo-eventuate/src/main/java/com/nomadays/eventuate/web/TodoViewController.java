package com.nomadays.eventuate.web;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.nomadays.eventuate.backend.Todo;
import com.nomadays.eventuate.backend.TodoQueryService;

@RestController
@RequestMapping(value = "/todos")
public class TodoViewController {
	
	@Autowired
	private TodoQueryService todoQueryService;
	
	@GetMapping
	public List<Todo> getAll() {
		return todoQueryService.getAll();
	}

}
