package com.simpleBank.application.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.simpleBank.application.entity.Account;

@Repository
public interface AccountRepository extends JpaRepository<Account, Long>{
	Optional<Account> findById(Long id);
    
	@Query("select balance from Account where number = ?1")
	public int findBalanceByAcctId(int accountNumber);
	
	@Transactional
	@Modifying(clearAutomatically = true)
	@Query("update Account set balance = balance+?2 where number=?1")
	public void saveBalanceByAcctID(int accountNumber, int balance);

	@Transactional
	@Modifying(clearAutomatically = true)
	@Query("update Account set balance = balance-?2 where number=?1")
	public void withdrawAmountByAcctID(int accountNumber, int balance);

}
