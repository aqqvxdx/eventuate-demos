package com.nomadays.eventuate.web;


import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.async.DeferredResult;

import com.nomadays.eventuate.backend.Todo;
import com.nomadays.eventuate.backend.TodoQueryService;
import com.nomadays.eventuate.domain.TodoAggregate;
import com.nomadays.eventuate.domain.TodoService;
import com.nomadays.eventuate.model.TodoInfo;

import io.eventuate.EntityWithIdAndVersion;

@RestController
@RequestMapping(value = "/todos")
public class TodoController {
	
	@Autowired
	private TodoService todoService;
	@Autowired
    private TodoQueryService todoQueryService;
	
	@PostMapping
	public DeferredResult<EntityWithIdAndVersion<TodoAggregate>> save(@RequestBody TodoInfo todo){
		DeferredResult<EntityWithIdAndVersion<TodoAggregate>> deferredResult = new DeferredResult<>();
		todoService.save(todo).whenCompleteAsync((result, throwable) -> deferredResult.setResult(result));
		return deferredResult;
	}
	
	@PutMapping("/{id}")
	public DeferredResult<EntityWithIdAndVersion<TodoAggregate>> update(@PathVariable String id, @RequestBody TodoInfo todo){
		DeferredResult<EntityWithIdAndVersion<TodoAggregate>> deferredResult = new DeferredResult<>();
		todoService.update(id, todo).whenCompleteAsync((result, throwable) -> deferredResult.setResult(result));
		return deferredResult;
	}
	
	@DeleteMapping("/{id}")
	public DeferredResult<EntityWithIdAndVersion<TodoAggregate>> delete(@PathVariable String id){
		DeferredResult<EntityWithIdAndVersion<TodoAggregate>> deferredResult = new DeferredResult<>();
		todoService.remove(id).whenCompleteAsync((result, throwable) -> deferredResult.setResult(result));
		return deferredResult;
	}
	
	@DeleteMapping
	public void deleteAll(){
		List<String> ids =
			todoQueryService.getAll()
				.stream()
				.map(Todo::getId)
				.collect(Collectors.toList());
		todoService.deleteAll(ids);
	}
}
