package com.myBank.controller;

import com.myBank.dto.CustomerDTO;
import com.myBank.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/customers")
public class CustomerController {

    @Autowired
    private CustomerService customerService;

    @PostMapping("/onboard")
    public ResponseEntity<CustomerDTO> onboardCustomer(@RequestBody CustomerDTO customerDTO) {
        CustomerDTO createdCustomer = customerService.onboardCustomer(customerDTO);
        return new ResponseEntity<>(createdCustomer, HttpStatus.CREATED);
    }
}
