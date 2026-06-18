package com.example.demo.controller;

import com.example.demo.model.Customer;
import com.example.demo.repository.CustomerRepository;
import org.springframework.data.domain.Limit;
import org.springframework.data.domain.ScrollPosition;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Window;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/customers")
public class CustomerController {

    private final CustomerRepository customerRepository;

    public CustomerController(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    /**
     * Endpoint to extract a paginated list of customers using the Spring Data Window API.
     *
     * @param lastId optional cursor representing the last seen customer ID. If absent, pagination starts from the first item.
     * @param limit the number of items to retrieve per page (default is 5).
     * @return a JSON object containing the records list, a hasNext flag, and the nextId token.
     */
    @GetMapping
    public Map<String, Object> getCustomers(
            @RequestParam(required = false) Long lastId,
            @RequestParam(defaultValue = "5") int limit) {

        // Determine the scroll position. Start at the keyset origin or resume from lastId.
        ScrollPosition position = lastId == null
                ? ScrollPosition.keyset()
                : ScrollPosition.of(Map.of("id", lastId), ScrollPosition.Direction.FORWARD);

        // Sorting by "id" ascending provides a stable sort for pagination
        Sort sort = Sort.by("id").ascending();

        // Perform the query on the database using the repository
        Window<Customer> window = customerRepository.findBy(position, Limit.of(limit), sort);

        Map<String, Object> response = new HashMap<>();
        response.put("content", window.getContent());
        response.put("hasNext", window.hasNext());

        // Extract the keyset value from the last element to serve as the next page's scroll position
        if (window.hasNext() && !window.isEmpty()) {
            ScrollPosition nextPosition = window.positionAt(window.size() - 1);
            if (nextPosition instanceof org.springframework.data.domain.KeysetScrollPosition keyset) {
                Object nextId = keyset.getKeys().get("id");
                response.put("nextId", nextId);
            }
        }

        return response;
    }
}
