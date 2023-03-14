package com.registration.registration.Repository;

import com.registration.registration.Infrastructure.Models.Customer;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface CustomerRepository extends MongoRepository<Customer, Long> {
    List<Customer> findByfirstName(String firstName);
}
