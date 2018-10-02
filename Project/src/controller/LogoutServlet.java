package controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class LogoutServlet
 */
@WebServlet("/LogoutServlet")
public class LogoutServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public LogoutServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		// セッションスコープの情報を削除
		request.getSession().removeAttribute("loginInfo");
		request.getSession().removeAttribute("grade");
		request.getSession().removeAttribute("courseName");
		request.getSession().removeAttribute("teacher");
		request.getSession().removeAttribute("cTermNo");
		request.getSession().removeAttribute("sCourseList");
		request.getSession().removeAttribute("cartList");

		// ログアウトメッセージを入れて、ログイン画面にリダイレクト
		request.setAttribute("errMsg", "ログアウトしました");

		//login.jspにフォワード
		request.getRequestDispatcher("/WEB-INF/jsp/login.jsp").forward(request, response);
		return;
	}
}
