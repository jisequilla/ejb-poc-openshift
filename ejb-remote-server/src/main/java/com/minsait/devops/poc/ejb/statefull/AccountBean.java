package com.minsait.devops.poc.ejb.statefull;

import java.util.logging.Logger;

import javax.ejb.Stateful;

import com.minsait.devops.poc.ejb.statefull.exception.InsufficientFundsException;

@Stateful(name = "account")
public class AccountBean implements Account {

	private final static Logger LOGGER = Logger.getLogger(AccountBean.class.toString());

	long money;

	@Override
	public long getMoney() {
		return money;

	}

	public void createAccount(long amount) {
		this.money = amount;

	}

	@Override
	public void deposit(long amount) {

		this.money += amount;

		LOGGER.info("Money deposit. total is " + money);

	}

	@Override
	public void withdraw(long amount) throws InsufficientFundsException {

		long newAmount = money - amount;
		if (newAmount < 0) {
			throw new InsufficientFundsException("Unsufficient funds for account! ");
		}

		money = newAmount;
		LOGGER.info("Money withdrawal. total is " + money);

	}
}
