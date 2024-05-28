package com.lahssini.tp6ebankingbackend.services;

import java.util.List;

import com.lahssini.tp6ebankingbackend.dtos.AccountHistoryDTO;
import com.lahssini.tp6ebankingbackend.dtos.AccountOperationDTO;
import com.lahssini.tp6ebankingbackend.dtos.BankAccountDTO;
import com.lahssini.tp6ebankingbackend.dtos.CurrentBankAccountDTO;
import com.lahssini.tp6ebankingbackend.dtos.CustomerDTO;
import com.lahssini.tp6ebankingbackend.dtos.SavingBankAccountDTO;
import com.lahssini.tp6ebankingbackend.entities.BankAccount;
import com.lahssini.tp6ebankingbackend.entities.CurrentAccount;
import com.lahssini.tp6ebankingbackend.entities.Customer;
import com.lahssini.tp6ebankingbackend.entities.SavingAccount;
import com.lahssini.tp6ebankingbackend.exceptions.BalanceNotSufficientException;
import com.lahssini.tp6ebankingbackend.exceptions.BankAccountNotFoundException;
import com.lahssini.tp6ebankingbackend.exceptions.CustomerNotFoundException;

public interface BankAccountService {
  CustomerDTO saveCustomer(CustomerDTO customerDTO);

  CurrentBankAccountDTO saveCurrentBankAccount(double initialBalance, double overdraft, Long customerId) throws CustomerNotFoundException;
  SavingBankAccountDTO saveSavingBankAccount(double initialBalance, double interestRate, Long customerId) throws CustomerNotFoundException;

  List<CustomerDTO> listCustomers();
  BankAccountDTO getBankAccount(String accountId) throws BankAccountNotFoundException;
  void debit(String accountId,double amount, String description) throws BankAccountNotFoundException, BalanceNotSufficientException;
  void credit(String accountId,double amount, String description) throws BankAccountNotFoundException, BalanceNotSufficientException;
  void transfer(String accountIdSource, String accountIdDestination, double amount) throws BankAccountNotFoundException, BalanceNotSufficientException;


  List<BankAccountDTO> bankAccountList();

  CustomerDTO getCustomer(Long customerId) throws CustomerNotFoundException;

  CustomerDTO updateCustomer(CustomerDTO customerDTO);

  void deleteCustomer(Long customerId);

  List<AccountOperationDTO> accountHistory(String accountId);

  AccountHistoryDTO getAccountHistory(String accountId, int page, int size) throws BankAccountNotFoundException;
  List<CustomerDTO>searchCustomers(String keyword);
}


  