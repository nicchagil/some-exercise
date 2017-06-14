

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class MyServlet
 */
public class MyServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private String userName = null; // 类变量，测试此类单例还是多例

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		/*
		 * 访问地址：
		 * http://localhost:8080/ServletFilterIsSingleton/MyServlet?un=Nick-Huang或
		 * http://localhost:8080/ServletFilterIsSingleton/MyServlet
		 */
		
		/* 如果un入参不为空，赋值给userName */
		String un = request.getParameter("un");
		if (un != null && un.trim().length() > 0) {
			userName = un;
		}
		
		response.getWriter().append("userName : ").append(userName);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
