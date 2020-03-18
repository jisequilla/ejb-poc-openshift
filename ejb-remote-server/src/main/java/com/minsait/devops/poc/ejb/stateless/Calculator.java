package com.minsait.devops.poc.ejb.stateless;

import javax.ejb.Remote;

@Remote
public interface Calculator {

	int add(int x, int y);

	int subtract(int x, int y);

	public float calculateInterest(long money);
}

