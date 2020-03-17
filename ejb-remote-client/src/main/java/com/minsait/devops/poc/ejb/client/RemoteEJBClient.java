package com.minsait.devops.poc.ejb.client;

import java.util.Properties;
import java.util.logging.Logger;

import javax.ejb.Stateless;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;

import com.minsait.devops.poc.greetings.Greetings;

@Stateless
public class RemoteEJBClient {

	private final static Logger LOGGER = Logger.getLogger(RemoteEJBClient.class.toString());

	public String testRemoteEJB(String name) throws NamingException {

		Context context = null;
		String result = null;

//		try {
			context = createInitialContext();
			final Greetings ejb = this.lookupRemoteEJB(context);
			result = ejb.echo(name);
			LOGGER.info(result);
//		} finally {
//			this.closeContext(context);
//		}
		return result;
	}

	private Greetings lookupRemoteEJB(Context context) throws NamingException {

		final String appName = "";
		final String moduleName = "ejb-remote-server";
		final String distinctName = "";
		final String beanName = "greetings";

		final String viewClassName = Greetings.class.getName();
		LOGGER.info("Looking EJB via JNDI ");
		LOGGER.info("ejb:" + appName + "/" + moduleName + "/" + distinctName + beanName + "!" + viewClassName);

		return (Greetings) context
				.lookup("ejb:" + appName + "/" + moduleName + "/" + distinctName + beanName + "!" + viewClassName);

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