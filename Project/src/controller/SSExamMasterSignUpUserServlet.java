package controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.UserDao;
import model.User;

/**
 * Servlet implementation class ExamMasterServlet
 */
@WebServlet("/ExamMasterSignUpUserServlet")
public class SSExamMasterSignUpUserServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public SSExamMasterSignUpUserServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		// ログインセッションがない場合、ログイン画面にリダイレクトさせる
		User user = (User) request.getSession().getAttribute("loginInfo");
		if (user == null) {
			response.sendRedirect("LoginServlet");
			return;
		}

		// リクエストスコープにユーザ一覧情報をセット
		request.setAttribute("userList", new UserDao().findAll());

		//exam_master_signup.jspにフォワード
		request.getRequestDispatcher("/WEB-INF/jsp/exam_master_signup_user.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// リクエストパラメータの文字コードを指定
        request.setCharacterEncoding("UTF-8");

		// リクエストパラメータの入力項目を取得
		String loginId = request.getParameter("inputLoginId");
		String userName = request.getParameter("inputUserName");
		String grade = request.getParameter("inputGrade");
		String address = null;

		// 入力情報を元に該当ユーザを検索
		List<User> userList = new UserDao().search(loginId, userName, grade, address);

		// リクエストスコープにユーザ一覧情報をセット
		request.setAttribute("userList", userList);

		// ユーザ一覧のjspにフォワード
		request.getRequestDispatcher("/WEB-INF/jsp/exam_master_signup_user.jsp").forward(request, response);
	}

}
