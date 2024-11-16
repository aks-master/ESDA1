package com.aks.yummy.service;

import com.aks.yummy.dto.CustomerRequest;
import com.aks.yummy.dto.CustomerResponse;
import com.aks.yummy.dto.LoginRequest;
import com.aks.yummy.entity.Customer;
import com.aks.yummy.helper.EncryptionService;
import com.aks.yummy.helper.JWTHelper;
import com.aks.yummy.mapper.CustomerMapper;
import com.aks.yummy.repo.CustomerRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import static java.lang.String.format;

@Service
@RequiredArgsConstructor
public class CustomerService {

    private final CustomerRepo repo;
    private final CustomerMapper mapper;
    private final EncryptionService encryptionService;
    private final JWTHelper jwtHelper;

    public String createCustomer(CustomerRequest request) {

        Customer customer = mapper.toEntity(request);
        customer.setPassword(encryptionService.encode(customer.getPassword()));
        repo.save(customer);
        return "Customer Created Successfully";
    }

    public Customer getCustomer(String email) {
        return repo.findByEmail(email)
                .orElseThrow(() -> new RuntimeException(
                        format("Cannot update Customer:: No customer found with the provided ID:: %s", email)));
    }

    public String login(LoginRequest request) {
        Customer customer = getCustomer(request.email());
        if (!encryptionService.validates(request.password(), customer.getPassword())) {
            return "Wrong Password or Email";
        }

        return jwtHelper.generateToken(request.email());
    }

    public String updateCustomerByEmail(CustomerRequest request) {
        Customer customer = repo.findByEmail(request.email())
                .orElseThrow(() -> new RuntimeException("No customer found with email: " + request.email()));

        customer.setFirstName(request.firstName());
        customer.setLastName(request.lastName());
        customer.setAddress(request.address());
        customer.setCity(request.city());
        customer.setPincode(request.pincode());

        repo.save(customer);
        return "Customer updated successfully";
    }

    public void deleteCustomerByEmail(String email) {
        Customer customer = repo.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("No customer found with email: " + email));

        repo.delete(customer);
    }

    public CustomerResponse getCustomerByEmail(String email) {
        Customer customer = repo.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("Customer not found"));
        return mapper.toCustomerResponse(customer);
    }

}
