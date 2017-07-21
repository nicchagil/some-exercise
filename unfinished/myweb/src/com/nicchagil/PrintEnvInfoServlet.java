package com.nicchagil;

import java.io.IOException;
import java.util.logging.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class PrintEnvInfoServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static Logger logger = Logger.getLogger("PrintEnvInfoServlet");
       
    public PrintEnvInfoServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		StringBuffer sb = new StringBuffer();
		sb.append("request.getProtocol() : " + request.getProtocol()).append("\n");
		sb.append("request.getScheme() : " + request.getScheme()).append("\n");
		sb.append("request.getRemoteAddr() : " + request.getRemoteAddr()).append("\n");
		sb.append("request.getRemoteHost() : " + request.getRemoteHost()).append("\n");
		sb.append("request.getServerPort() : " + request.getServerPort()).append("\n");
		sb.append("request.getRemotePort() : " + request.getRemotePort()).append("\n");
		sb.append("request.getQueryString() : " + request.getQueryString()).append("\n");
		sb.append("request.getRemoteUser() : " + request.getRemoteUser()).append("\n");
		sb.append("request.getMethod() : " + request.getMethod()).append("\n");
		sb.append("request.getLocalAddr() : " + request.getLocalAddr()).append("\n");
		sb.append("request.getLocalName() : " + request.getLocalName()).append("\n");
		sb.append("request.getPathInfo() : " + request.getPathInfo()).append("\n");
		sb.append("request.getRequestURI() : " + request.getRequestURI()).append("\n");
		sb.append("request.getRequestURL() : " + request.getRequestURL()).append("\n");
		sb.append("request.getContextPath() : " + request.getContextPath()).append("\n");
		
		response.getWriter().append("Served at: ").append(request.getContextPath()).append("\n").append(sb);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
