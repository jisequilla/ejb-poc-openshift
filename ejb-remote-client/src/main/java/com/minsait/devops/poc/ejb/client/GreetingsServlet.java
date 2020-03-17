package com.minsait.devops.poc.ejb.client;

import java.io.IOException;

import javax.ejb.EJB;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(urlPatterns = "greetings")
public class GreetingsServlet extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = -7032354007199174062L;

	@EJB RemoteEJBClient ejb;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) {

		response.setContentType("html");
		write(response, "<h1>Example Servlet to show how EJB can invoke an EJB in another application</h1>");

		try {
			String name = request.getParameter("name") != null ? request.getParameter("name") : "コロナウイルス";
			String greetings = ejb.testRemoteEJB(name);
			write(response, greetings + " <br/>");
		} catch (Exception n) {
			write(response, "Failed to invoke Remote EJB<br/>");
			write(response, n.getMessage());

		}

	}

	private static void write(HttpServletResponse writer, String message) {

		try {
			writer.getWriter().write(message + "\n");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
