package com.capgemini.service;

import com.capgemini.exceptions.InsufficientBalanceException;
import com.capgemini.exceptions.InsufficientInitialAmountException;
import com.capgemini.exceptions.InvalidAccountNumberException;
import com.capgemini.model.Account;
import com.capgemini.repository.AccountRepository;

public class AccountServiceImpl implements AccountService {
	
	/* (non-Javadoc)
	 * @see com.capgemini.service.AccountService#createAccount(int, int)
	 */
	
	AccountRepository accountRepository;
	
	
	public AccountServiceImpl(AccountRepository accountRepository) {
		super();
		this.accountRepository = accountRepository;
	}


	@Override
	public Account createAccount(int accountNumber,int amount) throws InsufficientInitialAmountException
	{
		int a,b,c;
		if(amount<500)
		{
			throw new InsufficientInitialAmountException();
		}
		Account account = new Account();
		account.setAccountNumber(accountNumber);
		
		account.setAmount(amount);
		 
		if(accountRepository.save(account))
		{
			return account;
		}
	     
		return null;
		
	}
	
	@Override
	public Account searchAccount(int accountNumber) throws InvalidAccountNumberException
	{
		Account account=accountRepository.searchAccount(accountNumber);
	
		if(account!=null)
		{
			return account;
		}
		else
			throw new InvalidAccountNumberException();
	}


	@Override
	public Account withdraw(int accountNumber, int amount) throws Exception {
		
		Account account1=accountRepository.searchAccount(accountNumber);
		if(account1==null)
			throw new InvalidAccountNumberException();
		if((account1.getAmount()-amount)<500)
			throw new InsufficientBalanceException();
		//System.out.println(account1.getAmount());
		account1.setAmount(account1.getAmount()-amount);
		return account1;
	}


	@Override
	public Account deposit(int accountNumber, int amount) throws Exception {
		Account account1=accountRepository.searchAccount(accountNumber);
		if(account1==null)
			throw new InvalidAccountNumberException();
		account1.setAmount(account1.getAmount()+amount);
		return account1;
	}

	
	
}
