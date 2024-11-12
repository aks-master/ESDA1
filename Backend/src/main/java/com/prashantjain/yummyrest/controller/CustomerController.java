package com.prashantjain.yummyrest.controller;

import com.prashantjain.yummyrest.dto.CustomerRequest;
import com.prashantjain.yummyrest.dto.Product;
import com.prashantjain.yummyrest.service.CustomerService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.GetMapping;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1/customer")
public class CustomerController {

    private final CustomerService customerService;

    @PostMapping("/create")
    public ResponseEntity<String> createCustoemr(@RequestBody @Valid CustomerRequest request) {
        return ResponseEntity.ok(customerService.createCustomer(request));
    }

    @GetMapping("/login")
    public ResponseEntity<String> checkLogin(@RequestBody CustomerRequest request){
        return ResponseEntity.ok(customerService.checkLogin(request));
    }

    @GetMapping("/get")
    public ResponseEntity<String> get(@RequestBody  CustomerRequest request){
        return ResponseEntity.ok(customerService.getdata(request));
    }

}
