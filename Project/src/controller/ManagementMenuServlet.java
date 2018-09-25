package controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.User;

/**
 * Servlet implementation class MyPageServlet
 */
@WebServlet("/ManagementMenuServlet")
public class ManagementMenuServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public ManagementMenuServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// セッションを取得
		User user = (User) request.getSession().getAttribute("loginInfo");
		request.setAttribute("loginInfo", user);

		//management_menu.jspにフォワード
		request.getRequestDispatcher("/WEB-INF/jsp/management_menu.jsp").forward(request, response);
	}
}
