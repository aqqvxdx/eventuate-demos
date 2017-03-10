package com.nomadays.cao.views.orderhistory;

import org.springframework.data.mongodb.repository.MongoRepository;

public interface CustomerViewRepository extends MongoRepository<CustomerView, String> {
}
