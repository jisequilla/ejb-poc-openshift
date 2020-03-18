package com.minsait.devops.poc.ejb.client;

import java.util.Properties;
import java.util.logging.Logger;

import javax.ejb.Stateless;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;

import com.minsait.devops.poc.ejb.statefull.Account;
import com.minsait.devops.poc.ejb.stateless.Calculator;

@Stateless
public class AccountEJBClient {

	private final static Logger LOGGER = Logger.getLogger(AccountEJBClient.class.toString());
	

	public float callRemoteEJBs(Long money) throws Exception {
		
		
		Context context = createInitialContext();
		
		Account account = this.lookupRemoteEJB(context );
		account .createAccount(money);
		LOGGER.info("Create Account with "+money);

		account.deposit(money/2);
		LOGGER.info("Deposit " +(money/2));

//		try {
//			account.withdraw(money/3);
//			LOGGER.info("Withdraw "+(money/3));
//
//		} catch (InsufficientFundsException e) {
//			LOGGER.info("ERROR : " + e.getMessage());
//		}
		money = account.getMoney();
		LOGGER.info("Money left " + money);
		
		Calculator calculator = this.lookupRemoteCalculatorEJB(context);
		float totalMoney = calculator.add(money.intValue(), 10);
		LOGGER.info("Money plus interests " + totalMoney);
		
		return totalMoney;



	}

	
	public Account lookupRemoteEJB(Context context) throws NamingException {

		final String appName = "";
		final String moduleName = "ejb-remote-server";
		final String distinctName = "";
		final String beanName = "account";

		final String viewClassName = Account.class.getName();
		System.out.println("Looking EJB via JNDI ");

		String serviceLookup = "ejb:" + appName + "/" + moduleName + "/" + distinctName + beanName + "!" + viewClassName
				+ "?stateful";

		System.out.println(serviceLookup);

		return (Account) context.lookup(serviceLookup);

	}
	
	public Calculator lookupRemoteCalculatorEJB(Context context) throws NamingException {

		final String appName = "";
		final String moduleName = "ejb-remote-server";
		final String distinctName = "";
		final String beanName = "calculator";

		final String viewClassName = Calculator.class.getName();
		System.out.println("Looking EJB via JNDI ");

		String serviceLookup = "ejb:" + appName + "/" + moduleName + "/" + distinctName + beanName + "!"
				+ viewClassName;

		System.out.println(serviceLookup);

		return (Calculator) context.lookup(serviceLookup);

	}
	
	
	private Context createInitialContext() throws NamingException {
		Properties prop = new Properties();

		prop.put(Context.INITIAL_CONTEXT_FACTORY, "org.jboss.naming.remote.client.InitialContextFactory");
		prop.put(Context.PROVIDER_URL, "http-remoting://ejb-remote-server:8080");
		prop.put(Context.SECURITY_PRINCIPAL, "ejbuser");
		prop.put(Context.SECURITY_CREDENTIALS, "ejbpassword");
		prop.put("jboss.naming.client.ejb.context", true);

		return new InitialContext(prop);
	}

	private void closeContext(Context context) throws NamingException {
		if (context != null) {
			context.close();
		}
	}
}
