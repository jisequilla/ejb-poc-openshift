package com.minsait.devops.poc.greetings;

import java.util.logging.Logger;

import javax.ejb.Stateless;

import com.minsait.devops.poc.ejb.stateless.CalculatorBean;

@Stateless(name = "greetings")
public class GreetingsBean implements Greetings {

	private final static Logger LOGGER = Logger.getLogger(CalculatorBean.class.toString());

	@Override
	public String echo(String s) {

		LOGGER.info("Hello " + s);

		return "こんにちは " + s + "(KONICHIWA  " + s + ")";
	}

}