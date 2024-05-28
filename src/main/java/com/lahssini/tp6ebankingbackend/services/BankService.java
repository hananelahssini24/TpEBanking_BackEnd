package com.lahssini.tp6ebankingbackend.services;

import jakarta.transaction.Transactional;
import com.lahssini.tp6ebankingbackend.entities.BankAccount;
import com.lahssini.tp6ebankingbackend.entities.CurrentAccount;
import com.lahssini.tp6ebankingbackend.entities.SavingAccount;
import com.lahssini.tp6ebankingbackend.repositories.BankAccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Transactional
public class BankService {
  @Autowired
  private BankAccountRepository bankAccountRepository;
  public void consulter(){
        BankAccount bankAccount =
          bankAccountRepository.findById("2c0ec42a-2835-4d92-9e89-e735de75b236").orElse(null);
        if (bankAccount != null) {
          System.out.println("--------------------------------------------");
          System.out.println(bankAccount.getId());
          System.out.println(bankAccount.getBalance());
          System.out.println(bankAccount.getStatus());
          System.out.println(bankAccount.getCreatedAt());
          System.out.println(bankAccount.getCustomer().getName());
          System.out.println(bankAccount.getClass().getSimpleName());
          if (bankAccount instanceof SavingAccount) {
            System.out.println("Over Draft=>" + ((CurrentAccount) bankAccount).getOverDraft());
          } else if (bankAccount instanceof SavingAccount) {
            System.out.println("Rate=>" + ((SavingAccount) bankAccount).getInterestRate());
          }
          bankAccount.getAccountOperations().forEach(op -> {
            System.out.println("====================================");
            System.out.println(op.getType() + "\t" + op.getOperationDate() + "\t" + op.getAmount());
          });
    }
  }
}
