package com.fixdecode.restcruddemo.customer;

import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
@AllArgsConstructor
public class CustomerService {

    private CustomerRepository customerRepository;

    public List<Customer> getCustomers() {
     return customerRepository.findAll()
             .stream().collect(Collectors.toList());
    }

    public Customer saveCustomer(Customer customer) {
        boolean customerAlreadyExists =
                customerRepository.findByEmail(customer.getEmail()).isPresent();
        if (customerAlreadyExists){
            throw new IllegalStateException("Customer already exists");
        }
        return customerRepository.save(customer);
    }

    public Customer getCustomer(@RequestParam String email) {
        return customerRepository.findByEmail(email)
                .orElseThrow(() -> new CustomerNotFoundException("Customer not found"));

    }
    public Boolean deleteCustomer(@RequestParam String email) {
       customerRepository.deleteByEmail(email);
       return true;
    }
    public Customer updateCustomer(Customer customer) {
      return customerRepository.save(customer);
    }
}
