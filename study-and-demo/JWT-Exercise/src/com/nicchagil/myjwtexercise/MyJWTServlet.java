package com.nicchagil.myjwtexercise;


import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.nicchagil.myjwtexercise.util.MyJWTUtils;
import com.nicchagil.myjwtexercise.util.RequestUtils;

/**
 * Servlet implementation class MyJWTServlet
 */
public class MyJWTServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

    /**
     * Default constructor. 
     */
    public MyJWTServlet() {
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String authorization = RequestUtils.getHeader(request, "Authorization");
		boolean rs = MyJWTUtils.verifySignature(authorization);
		
		response.getWriter().append("rs : ").append(rs + "");
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
