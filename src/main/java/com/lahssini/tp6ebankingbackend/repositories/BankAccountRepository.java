package com.lahssini.tp6ebankingbackend.repositories;
import com.lahssini.tp6ebankingbackend.entities.BankAccount;
import org.springframework.data.jpa.repository.JpaRepository;


public interface BankAccountRepository extends JpaRepository<BankAccount,String> {
}
