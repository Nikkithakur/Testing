package com.capgemini.service;

import com.capgemini.exceptions.InsufficientInitialAmountException;
import com.capgemini.exceptions.InvalidAccountNumberException;
import com.capgemini.model.Account;

public interface AccountService {

	Account createAccount(int accountNumber, int amount) throws InsufficientInitialAmountException;
	
	Account searchAccount(int accountNumber)throws InvalidAccountNumberException;
	
	Account deposit(int accountNumber,int amount) throws Exception;
	
	Account withdraw(int accountNumber,int amount) throws Exception;
	
}