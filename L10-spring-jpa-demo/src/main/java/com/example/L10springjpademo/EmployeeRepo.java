package com.example.L10springjpademo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EmployeeRepo extends JpaRepository<Employee,Integer> {
    // implementation is provided by hibernate at runtime
    // here, the implementation will be provided for the JpaRepository as we are extending it.
    // similarly if we extended something else like only CrudRepository, MongoRepository,etc. then implementation
    // will be provided for that.
}
