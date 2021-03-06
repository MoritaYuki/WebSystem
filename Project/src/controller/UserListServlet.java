package controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.UserDao;
import model.User;

/**
 * Servlet implementation class UserListServlet
 */
@WebServlet("/UserListServlet")
public class UserListServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public UserListServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// ログインセッションがない場合、ログイン画面にリダイレクトさせる
		User user = (User)request.getSession().getAttribute("loginInfo");
		if(user == null) {
			response.sendRedirect("LoginServlet");
			return;
		}

		// 登録完了メッセージを取得、セッションスコープを削除
		HttpSession session = request.getSession();
		request.setAttribute("signMsg", session.getAttribute("signMsg"));
		session.removeAttribute("signMsg");

		// リクエストスコープにユーザ一覧情報をセット
		request.setAttribute("userList", new UserDao().findAll());

		// リクエストに学年がなければ、全学年のリストを取得
		if (request.getParameter("uGradeNo") == null) {
			request.setAttribute("uGradeNo", "全");
		}

		// ユーザ一覧のjspにフォワード
		request.getRequestDispatcher("/WEB-INF/jsp/user_list.jsp").forward(request, response);
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
		String uGradeNo = request.getParameter("inputUGradeNo");
		String address = request.getParameter("inputAddress");

		// 検索条件の表示のため、リクエストスコープに検索条件を保存
		request.setAttribute("loginId", loginId);
		request.setAttribute("userName", userName);
		if(uGradeNo == null) {
			request.setAttribute("uGradeNo", "全");
		}else {
			request.setAttribute("uGradeNo", uGradeNo);
		}
		request.setAttribute("address", address);

		// 入力情報を元に該当ユーザを検索
		List<User> userList = new UserDao().search(loginId, userName, uGradeNo, address);

		// リクエストスコープにユーザ一覧情報をセット
		request.setAttribute("userList", userList);

		// ユーザ一覧のjspにフォワード
		request.getRequestDispatcher("/WEB-INF/jsp/user_list.jsp").forward(request, response);
	}

}
