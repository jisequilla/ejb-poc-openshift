package com.sample.client;

import java.util.Properties;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;

import com.minsait.devops.poc.greetings.Greetings;

public class RemoteEJBClient {

	private Context context = null;

	public static void main(String[] args) throws Exception {
		testRemoteEJB();

	}

	private static void testRemoteEJB() throws NamingException {

		RemoteEJBClient main = new RemoteEJBClient();

		try {
			main.createInitialContext();
			final Greetings ejb = main.lookupRemoteEJB();
			System.out.println(ejb);
			String result = ejb.echo("Piter");
			System.out.println(result);
		} finally {
			main.closeContext();
		}
	}

	private Greetings lookupRemoteEJB() throws NamingException {

		final String appName = "";
		final String moduleName = "ejb-remote-server";
		final String distinctName = "";
		final String beanName = "greetings";

		final String viewClassName = Greetings.class.getName();
		System.out.println("Looking EJB via JNDI ");
		System.out.println("ejb:" + appName + "/" + moduleName + "/" + distinctName + beanName + "!" + viewClassName);

		return (Greetings) context
				.lookup("ejb:" + appName + "/" + moduleName + "/" + distinctName + beanName + "!" + viewClassName);

	}

	public void createInitialContext() throws NamingException {
		Properties prop = new Properties();

		prop.put(Context.INITIAL_CONTEXT_FACTORY, "org.jboss.naming.remote.client.InitialContextFactory");
		prop.put(Context.PROVIDER_URL, "http-remoting://127.0.0.1:8080");
		//prop.put(Context.SECURITY_PRINCIPAL, "ejbuser");
		//prop.put(Context.SECURITY_CREDENTIALS, "ejbpassword");
		prop.put("jboss.naming.client.ejb.context", true);

		context = new InitialContext(prop);
	}

	public void closeContext() throws NamingException {
		if (context != null) {
			context.close();
		}
	}

}