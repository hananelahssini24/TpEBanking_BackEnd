package com.lahssini.tp6ebankingbackend.web;

import java.util.List;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.lahssini.tp6ebankingbackend.dtos.CustomerDTO;
import com.lahssini.tp6ebankingbackend.entities.Customer;
import com.lahssini.tp6ebankingbackend.exceptions.CustomerNotFoundException;
import com.lahssini.tp6ebankingbackend.services.BankAccountService;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@RestController
@AllArgsConstructor
@Slf4j
@CrossOrigin("*")
public class CustomerRestController {
    private BankAccountService bankAccountService;
    @GetMapping("/customers")
    @PreAuthorize("hasAuthority('SCOPE_USER')")
  public List<CustomerDTO> customers(){
    return bankAccountService.listCustomers();
  }
  @GetMapping("/customers/search")
  @PreAuthorize("hasAuthority('SCOPE_USER')")
  public List<CustomerDTO> searchCustomers(@RequestParam(name = "keyword",defaultValue = "")String keyword ){
    return bankAccountService.searchCustomers(keyword);
  }
  @GetMapping("/customers/{id}")
  @PreAuthorize("hasAuthority('SCOPE_USER')")
  public CustomerDTO getCustomer(@PathVariable(name = "id") Long customerId) throws CustomerNotFoundException {
  return bankAccountService.getCustomer(customerId);
  }

  @PostMapping("/customers")
  @PreAuthorize("hasAuthority('SCOPE_ADMIN')")
  public CustomerDTO saveCustomer(@RequestBody CustomerDTO customerDTO){
   return bankAccountService.saveCustomer(customerDTO);
  }
  @PutMapping("/customers/{customerId}")
  @PreAuthorize("hasAuthority('SCOPE_ADMIN')")
  public CustomerDTO updateCustomer(@PathVariable Long customerId,@RequestBody CustomerDTO customerDTO){
    customerDTO.setId(customerId);
   return bankAccountService.updateCustomer(customerDTO);
  }
  @DeleteMapping("/customers/{id}")
  @PreAuthorize("hasAuthority('SCOPE_ADMIN')")
  public void deleteCustomer(@PathVariable Long id){
    bankAccountService.deleteCustomer(id);
  }
}
