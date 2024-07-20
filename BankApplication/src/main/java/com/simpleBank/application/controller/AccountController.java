package com.simpleBank.application.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.Map;
import com.simpleBank.application.entity.Account;
import com.simpleBank.application.service.BankService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/api/accounts")
public class AccountController {

	@Autowired 
	BankService bankService;
	
	@PostMapping
    public Account createAccount(@RequestBody Account account) {
        return bankService.createAccount(account);
    }
	
	@GetMapping("/{id}")
    public Account getAccount(@PathVariable Long id) {
        return bankService.getAccount(id).orElseThrow(() -> new RuntimeException("Account not found"));
    }

	@GetMapping("/account/{acctID}/balance")
	public int getBalance(@PathVariable int acctID) {
		return bankService.getBalance(acctID);
	}
	
    @PostMapping("/{id}/deposit")
    public Account deposit(@PathVariable Long id, @RequestBody Map<String, Double> request) {
        Double amount = request.get("amount");
        return bankService.deposit(id, amount);
    }

    @PostMapping("/{id}/withdraw")
    public Account withdraw(@PathVariable Long id, @RequestBody Map<String, Double> request) {
        Double amount = request.get("amount");
        return bankService.withdraw(id, amount);
    }
    
    @PutMapping("/account/{acctID}/transfer/{destAcctID}/{amount}")
	public void transferAmount(@PathVariable int acctID, @PathVariable int destAcctID, @PathVariable int amount) {
		bankService.transferAmount(acctID, destAcctID, amount);
		
	}
}
