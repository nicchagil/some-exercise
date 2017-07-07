package com.nicchagil.myjwtexercise;


import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.nicchagil.myjwtexercise.entity.Header;
import com.nicchagil.myjwtexercise.entity.Payload;
import com.nicchagil.myjwtexercise.util.MyJWTUtils;

/**
 * Servlet implementation class LoginServlet
 */
public class LoginServlet extends HttpServlet {
	
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public LoginServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		/* 假设通过验证 */
		boolean loginResult = true;
		
		if (!loginResult) {
			response.getWriter().append("No permissions");
		}
		
		/* 封装头部对象、负载对象 */
		Header header = new Header("jwt", "DES");
		Payload payload = new Payload("123456");
		
		String token = MyJWTUtils.generateToken(header, payload);
		
		response.getWriter().append(token);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}
	
	public static void main(String[] args) throws ServletException, IOException {
		new LoginServlet().doGet(null, null);
	}

}
