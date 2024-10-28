package com.myBank.service.ServiceImpl;

import com.myBank.Repository.AccountRepository;
import com.myBank.Repository.CustomerRepository;
import com.myBank.dto.CustomerDTO;
import com.myBank.entity.Account;
import com.myBank.entity.Customer;
import com.myBank.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDateTime;


@Service
public class CustomerServiceImpl implements CustomerService {
    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private AccountRepository accountRepository;
    private static final BigDecimal INITIAL_SAVINGS_BALANCE = new BigDecimal("500.00");


    private CustomerDTO convertToDTO(Customer customer){
        return  new CustomerDTO(
                customer.getCustomerId(),
                customer.getName(),
                customer.getAddress(),
                customer.getContactNumber(),
                customer.getEmail(),
                customer.getDob(),
                customer.getRegistrationDate()
        );
    }
    private Customer convertToEntity(CustomerDTO customerDTO){
        return  new Customer(
              customerDTO.getCustomerId(),
                customerDTO.getName(),
                customerDTO.getAddress(),
                customerDTO.getContactNumber(),
                customerDTO.getEmail(),
                customerDTO.getDob(),
                customerDTO.getRegistrationDate()
        );
    }

    @Override
    public CustomerDTO onboardCustomer(CustomerDTO customerDTO) {
        Customer customer=convertToEntity(customerDTO);
        customer=customerRepository.save(customer);

        // Create Current Account
        Account currentAccount = new Account();
        currentAccount.setCustomer(customer);
        currentAccount.setAccountType("Current");
        currentAccount.setBalance(BigDecimal.ZERO); // Initial balance is 0 for Current Account
        currentAccount.setCreationDate(LocalDateTime.now()); // Set creation date
        currentAccount.setStatus("Active"); // Set status to Active
        accountRepository.save(currentAccount); // Save Current Account

        // Create Savings Account with initial credit of R500.00
        Account savingsAccount = new Account();
        savingsAccount.setCustomer(customer);
        savingsAccount.setAccountType("Savings");
        savingsAccount.setBalance(INITIAL_SAVINGS_BALANCE); // Set initial balance to R500.00
        savingsAccount.setCreationDate(LocalDateTime.now()); // Set creation date
        savingsAccount.setStatus("Active"); // Set status to Active
        accountRepository.save(savingsAccount); // Save Savings Account
        return convertToDTO(customer);
    }
}
