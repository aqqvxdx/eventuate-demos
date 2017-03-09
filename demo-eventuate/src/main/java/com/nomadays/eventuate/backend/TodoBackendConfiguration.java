package com.nomadays.eventuate.backend;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.core.MongoTemplate;

import com.nomadays.eventuate.command.TodoCommand;
import com.nomadays.eventuate.domain.TodoAggregate;
import com.nomadays.eventuate.domain.TodoBulkDeleteAggregate;
import com.nomadays.eventuate.domain.TodoCommandWorkflow;
import com.nomadays.eventuate.domain.TodoService;

import io.eventuate.AggregateRepository;
import io.eventuate.EventuateAggregateStore;
import io.eventuate.javaclient.spring.EnableEventHandlers;

@Configuration
@EnableEventHandlers
public class TodoBackendConfiguration {

	@Autowired
	private MongoTemplate mongoTemplate;
	
	@Bean
	public TodoQueryService todoQueryService(){
		return new TodoQueryService(mongoTemplate);
	}
	
	@Bean
    public AggregateRepository<TodoAggregate, TodoCommand> aggregateRepository(EventuateAggregateStore eventStore) {
        return new AggregateRepository<>(TodoAggregate.class, eventStore);
    }

    @Bean
    public AggregateRepository<TodoBulkDeleteAggregate, TodoCommand> bulkDeleteAggregateRepository(EventuateAggregateStore eventStore) {
        return new AggregateRepository<>(TodoBulkDeleteAggregate.class, eventStore);
    }

    @Bean
    public TodoService updateService(AggregateRepository<TodoAggregate, TodoCommand> aggregateRepository, AggregateRepository<TodoBulkDeleteAggregate, TodoCommand> bulkDeleteAggregateRepository) {
        return new TodoService(aggregateRepository, bulkDeleteAggregateRepository);
    }

    @Bean
    public TodoQueryWorkflow todoQueryWorkflow(TodoQueryService queryService) {
        return new TodoQueryWorkflow(queryService);
    }
    
    @Bean
    public TodoCommandWorkflow todoCommandWorkflow(){
    	return new TodoCommandWorkflow();
    }
}
