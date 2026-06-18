package com.example.demo.repository;

import com.example.demo.model.Customer;
import org.springframework.data.domain.Limit;
import org.springframework.data.domain.ScrollPosition;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Window;
import org.springframework.data.repository.ListCrudRepository;

public interface CustomerRepository extends ListCrudRepository<Customer, Long> {

    /**
     * Executes a scrolling query using Spring Data Scroll API.
     * 
     * @param position the scroll position (cursor) to resume scrolling from.
     * @param limit the maximum number of results to return.
     * @param sort the sorting criteria.
     * @return a Window containing the retrieved slice of Customers and metadata for the next cursor.
     */
    Window<Customer> findBy(ScrollPosition position, Limit limit, Sort sort);
}
