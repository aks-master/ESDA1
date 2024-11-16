package com.aks.yummy.controller;

import com.aks.yummy.dto.CustomerRequest;
import com.aks.yummy.dto.CustomerResponse;
import com.aks.yummy.dto.LoginRequest;
import com.aks.yummy.helper.JWTHelper;
import com.aks.yummy.service.CustomerService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import com.aks.yummy.validation.ValidationGroups;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.validation.annotation.Validated;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1/customer")

public class CustomerController {
    @Autowired
    private final CustomerService customerService;

    @Autowired
    private final JWTHelper jwtHelper;

    @PostMapping("/create")
    @Validated(ValidationGroups.CreateGroup.class)
    public ResponseEntity<String> createCustomer(@RequestBody @Valid CustomerRequest request) {
        return ResponseEntity.ok(customerService.createCustomer(request));
    }

    @PutMapping("/update")
    @Validated(ValidationGroups.UpdateGroup.class)
     public ResponseEntity<String> updateCustomer(@RequestHeader(HttpHeaders.AUTHORIZATION) String token, @RequestBody @Valid CustomerRequest request) {
        String jwtToken = token.startsWith("Bearer ") ? token.substring(7) : token;
        String email = jwtHelper.extractEmail(jwtToken); 

        if (!jwtHelper.validateToken(jwtToken, email)) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(null);
        }

        return ResponseEntity.ok(customerService.updateCustomerByEmail(request));
    }

    @DeleteMapping("/delete")
    public ResponseEntity<String> deleteCustomer(@RequestHeader(HttpHeaders.AUTHORIZATION) String token) {
        String jwtToken = token.startsWith("Bearer ") ? token.substring(7) : token;
        String email = jwtHelper.extractEmail(jwtToken);

        if (!jwtHelper.validateToken(jwtToken, email)) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Unauthorized");
        }

        customerService.deleteCustomerByEmail(email);
        return ResponseEntity.ok("Customer deleted successfully");
    }

    @GetMapping("/retrieve")
    public ResponseEntity<CustomerResponse> retrieveCustomer(@RequestHeader(HttpHeaders.AUTHORIZATION) String token) {
        String jwtToken = token.startsWith("Bearer ") ? token.substring(7) : token;
        String email = jwtHelper.extractEmail(jwtToken);

        if (!jwtHelper.validateToken(jwtToken, email)) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(null);
        }

        CustomerResponse customer = customerService.getCustomerByEmail(email);
        return ResponseEntity.ok(customer);
    }
}
