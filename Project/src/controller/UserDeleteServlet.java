package controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.ExamDao;
import dao.UserDao;
import model.User;

/**
 * Servlet implementation class CourseMasterDeleteServlet
 */
@WebServlet("/UserDeleteServlet")
public class UserDeleteServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public UserDeleteServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		UserDao userDao = new UserDao();
		ExamDao examDao = new ExamDao();

		// ログインセッションがない場合、ログイン画面にリダイレクトさせる
		HttpSession session = request.getSession();
		User user = (User) session.getAttribute("loginInfo");
		if (user == null) {
			response.sendRedirect("LoginServlet");
			return;
		}

		// リクエストパラメータの文字コードを指定
		request.setCharacterEncoding("UTF-8");
		String userId = request.getParameter("userId");
		request.setAttribute("userId", userId);

		// ユーザ情報削除を確認後の処理
		// リクエストスコープの"delFg"に値がある場合のみユーザ情報を削除する
		if (request.getParameter("delFg") != null) {
			// ユーザ情報を削除して、関連するテスト結果マスタも一括で削除する
			userDao.userDelete(userId);
			examDao.examDelete(userId);

			// 新たなリストを取得後、ユーザ一覧画面に遷移ユーザ一覧画面へ遷移
			request.setAttribute("userList", new UserDao().findAll());
			response.sendRedirect("UserListServlet");
			return;
		}

		// 取得したユーザIDを用いてインスタンスを取得
		user = userDao.searchByUserId(userId);
		request.setAttribute("user", user);

		// userDelete.jspにフォワード
		RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/user_delete.jsp");
		dispatcher.forward(request, response);
	}
}
