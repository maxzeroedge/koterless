package com.palashmax.s3.servlet;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

public class S3JavaServlet extends HttpServlet {

	public void doGet(HttpServletRequest request,
			  HttpServletResponse response) throws ServletException, IOException
	{
		// Setting up the content type of web page
		response.setContentType("text/html");
		// Writing the message on the web page
		PrintWriter out = response.getWriter();
		out.println("<h1>S3 Servlet</h1>");
		out.println("<p>" + "Hello Friends!" + "</p>");
	}
}
