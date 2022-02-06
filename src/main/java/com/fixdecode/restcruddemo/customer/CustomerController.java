package com.fixdecode.restcruddemo.customer;

import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import java.util.Map;

import static org.springframework.http.HttpStatus.CREATED;
import static org.springframework.http.HttpStatus.OK;

@AllArgsConstructor
@RestController
@RequestMapping("/api/customers/")
public class CustomerController {
    private CustomerService customerService;

    // Getting all customers from the database
    @GetMapping
    public ResponseEntity<ResponseMessage> getCustomers(){
        return ResponseEntity.ok(
                ResponseMessage.builder()
                        .data(Map.of("Customers", customerService.getCustomers()))
                        .message("Customers found")
                        .status(OK)
                        .statusCode(OK.value())
                        .build());
    }
    //Adding a new customer to the database
    @PostMapping
    public ResponseEntity<ResponseMessage> addCustomer(@RequestBody Customer customer){
        return ResponseEntity.ok(
                ResponseMessage.builder()
                        .data(Map.of("customer", customerService.saveCustomer(customer)))
                        .message("New customer was added")
                        .status(CREATED)
                        .statusCode(CREATED.value())
                        .build());
    }

    //Getting a single customer by the email as id
    @GetMapping("email/{email}")
    public ResponseEntity<ResponseMessage> getCustomer(@PathVariable("email") String email){
        return ResponseEntity.ok(
                ResponseMessage.builder()
                        .data(Map.of("Customer", customerService.getCustomer(email)))
                        .message("Customer found")
                        .status(OK)
                        .statusCode(OK.value())
                        .build());
    }

    //Deleting a customer by the id
    @DeleteMapping("delete/{email}")
    public ResponseEntity<ResponseMessage> deleteCustomer(@PathVariable("email") String email){
       return ResponseEntity.ok(
               ResponseMessage.builder()
                       .data(Map.of("Deleted", customerService.deleteCustomer(email)))
                       .message("A customer was deleted")
                       .status(OK)
                       .statusCode(OK.value())
                       .build());
    }
    //Updating customer
    @PutMapping("update")
    public ResponseEntity<ResponseMessage> updateCustomer(@RequestBody Customer customer){
        return ResponseEntity.ok(
                ResponseMessage.builder()
                        .data(Map.of("customer", customerService.updateCustomer(customer)))
                        .message("A customer was updated")
                        .status(CREATED)
                        .statusCode(CREATED.value())
                        .build());
    }


}
