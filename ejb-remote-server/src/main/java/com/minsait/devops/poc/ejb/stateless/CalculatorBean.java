package com.minsait.devops.poc.ejb.stateless;

import java.util.logging.Logger;

import javax.annotation.Resource;
import javax.ejb.SessionContext;
import javax.ejb.Stateless;

@Stateless(name = "calculator")
public class CalculatorBean implements Calculator {

	private final static Logger LOGGER = Logger.getLogger(CalculatorBean.class.toString());

	@Resource
	private SessionContext context;

	@Override
	public int add(int x, int y) {
		LOGGER.info("Adding x" + x + "to y" + y);
		return x + y;
	}

	@Override
	public int subtract(int x, int y) {
		LOGGER.info("Substracting x" + x + "to y" + y);
		return x - y;
	}

}
