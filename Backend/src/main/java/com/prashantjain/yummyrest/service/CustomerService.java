package com.prashantjain.yummyrest.service;

import com.prashantjain.yummyrest.dto.CustomerRequest;
import com.prashantjain.yummyrest.dto.CustomerResponse;
import com.prashantjain.yummyrest.dto.Product;
import com.prashantjain.yummyrest.entity.Customer;
import com.prashantjain.yummyrest.entity.product;
import com.prashantjain.yummyrest.mapper.CustomerMapper;
import com.prashantjain.yummyrest.repo.CustomerRepo;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CustomerService {

private final CustomerRepo repo;                    
    private final CustomerMapper mapper;
    public String createCustomer(@Valid CustomerRequest request) {
        Customer customer = mapper.toEntity(request);
        repo.save(customer);
        return "Created";
    }

    public String checkLogin(@Valid CustomerRequest request) {
        Customer customer = repo.findByEmailAndPassword(request.email(), request.password()) .orElse(null);
        if (customer != null) return "UserName : "+customer.getFirstName() + "\nLastName : " + customer.getLastName()+"\nEmail : " + customer.getEmail();
        return "No ID Password Found";
    }

    public String getdata(@Valid  CustomerRequest request) {
        List<product> products = repo.findTop2ByPriceRange(request.p21(),request.p22()); // Adjust criteria as needed
        if (!products.isEmpty()) {
            return products.toString(); // Or customize the output as needed
        } else {
            return "No products found";
        }

    }


}
