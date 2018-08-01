package com.capgemini.test;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.capgemini.exceptions.InsufficientBalanceException;
import com.capgemini.exceptions.InsufficientInitialAmountException;
import com.capgemini.exceptions.InvalidAccountNumberException;
import com.capgemini.model.Account;
import com.capgemini.repository.AccountRepository;
import com.capgemini.service.AccountService;
import com.capgemini.service.AccountServiceImpl;
import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;
public class AccountTest {

	AccountService accountService;
	
	@Mock
	AccountRepository accountRepository;
	@Before
	public void setUp() throws Exception {
		MockitoAnnotations.initMocks(this);
		
		accountService = new AccountServiceImpl(accountRepository);
	}

	/*
	 * create account
	 * 1.when the amount is less than 500 then system should throw exception
	 * 2.when the valid info is passed account should be created successfully
	 */
	
	@Test(expected=com.capgemini.exceptions.InsufficientInitialAmountException.class)
	public void whenTheAmountIsLessThan500SystemShouldThrowException() throws InsufficientInitialAmountException
	{
		accountService.createAccount(101, 400);
	}
	
	@Test
	public void whenTheValidInfoIsPassedAccountShouldBeCreatedSuccessfully() throws InsufficientInitialAmountException
	{
		Account account =new Account();
		account.setAccountNumber(101);
		account.setAmount(5000);
		when(accountRepository.save(account)).thenReturn(true);
		assertEquals(account, accountService.createAccount(101, 5000));
	}
	
	/*
	 * search account
	 * 1.when the account number is invalid then system should throw exception
	 * 2.when the valid account number is passed it should return current object
	 */

	@Test(expected=InvalidAccountNumberException.class)
	public void invalidAccountExceptionToBeThrown() throws InvalidAccountNumberException
	{
	
	Account account =new Account();
	account.setAccountNumber(102);
	account.setAmount(6000);
	when(accountRepository.searchAccount(102)).thenReturn(account);
	accountService.searchAccount(103);
	}
	
	
	@Test
	public void validAccountNumber() throws InvalidAccountNumberException
	{
	Account account =new Account();
	account.setAccountNumber(103);
	account.setAmount(7000);
	when(accountRepository.searchAccount(103)).thenReturn(account);
	assertEquals(account,accountService.searchAccount(103));
	}
	
	/*
	 WithdrawalAmount
	 1.exception thrown so that balance is not less than 500
	 2.Invalid account number exception has to be thrown if not found
	 3.withdraw success
	 */
	
	@Test(expected=InsufficientBalanceException.class)
	public void withdrawAmount()throws Exception
	{
		Account account =new Account();
		account.setAccountNumber(103);
		account.setAmount(7000);
		when(accountRepository.searchAccount(103)).thenReturn(account);
		assertEquals(400,accountService.withdraw(103,6600).getAmount());
	}
	
	@Test(expected=InvalidAccountNumberException.class)
	public void withdrawAmount1()throws Exception
	{
		Account account =new Account();
		account.setAccountNumber(103);
		account.setAmount(7000);
		//when(accountRepository.searchAccount(103)).thenReturn(account);
		assertEquals(400,accountService.withdraw(103,6600).getAmount());
	}
	
	@Test
	public void withdrawSuccess() throws Exception
	{
		Account account =new Account();
		account.setAccountNumber(104);
		account.setAmount(7000);
		when(accountRepository.searchAccount(104)).thenReturn(account);
		assertEquals(600,accountService.withdraw(104,6400).getAmount());
	}
	/*
	DepositAmount
	1.success
	2.Invalid account number exception thrown if account number not found 
	*/
	@Test
	public void depositAmount() throws Exception
	{
		Account account =new Account();
		account.setAccountNumber(103);
		account.setAmount(7000);
		when(accountRepository.searchAccount(103)).thenReturn(account);
		assertEquals(13600,accountService.deposit(103,6600).getAmount());
	}
	
	@Test(expected=InvalidAccountNumberException.class)
	public void depositAmountExcpetionThrown() throws Exception
	{
		Account account =new Account();
		account.setAccountNumber(103);
		account.setAmount(7000);
		when(accountRepository.searchAccount(103)).thenReturn(account);
		assertEquals(13600,accountService.deposit(104,6600).getAmount());
	}
	
}
