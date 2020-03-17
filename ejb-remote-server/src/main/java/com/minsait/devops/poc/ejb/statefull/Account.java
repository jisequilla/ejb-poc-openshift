package com.minsait.devops.poc.ejb.statefull;

import javax.ejb.Remote;

import com.minsait.devops.poc.ejb.statefull.exception.InsufficientFundsException;

@Remote
public interface Account {

	public void deposit(long amount);

	public void withdraw(long amount) throws InsufficientFundsException;

	public long getMoney();

	public void createAccount(long amount);

}
