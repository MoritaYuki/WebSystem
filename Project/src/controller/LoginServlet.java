package controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.UserDao;
import model.User;

/**
 * Servlet implementation class LoginServlet
 */
@WebServlet("/LoginServlet")
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

		//ログインセッションがある場合、ユーザ一覧画面にリダイレクトさせる。
		User user = (User) request.getSession().getAttribute("loginInfo");
		if(!(user == null)){
			if (user.getLoginId().equals("admin")) {
				response.sendRedirect("ManagementMenuServlet");
				return;
			}
			response.sendRedirect("MyPageServlet");
			return;
		}

		//login.jspにフォワード
		request.getRequestDispatcher("/WEB-INF/jsp/login.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// リクエストパラメータの文字コードを指定
        request.setCharacterEncoding("UTF-8");

		// リクエストパラメータの入力項目を取得
		String loginId = request.getParameter("loginId");
		String password = request.getParameter("password");

        //DB(sm_db)上に合致するものがあるかどうかを評価
        User user = new UserDao().findByLoginInfo(loginId, password);

        //合致するものがなかった場合
        if(user == null) { //←.equals()は使わない。userがnullの場合はnullインスタンスのメソッドを使用してぬるぽが起こるため
        	//エラーメッセージをリクエストスコープに保管
        	request.setAttribute("errMsg", "ログインIDまたはパスワードが異なります");

        	//login.jspにフォワード
        	request.getRequestDispatcher("/WEB-INF/jsp/login.jsp").forward(request, response);
            return;
        }

        // 合致するものがあった場合はユーザの情報をセッションスコープに保管して
        // 管理者 ⇒ 管理メニュー
        // 一般   ⇒ マイページ
        // へ遷移
        request.getSession().setAttribute("loginInfo", user);
        if (user.getLoginId().equals("admin")) {
			response.sendRedirect("ManagementMenuServlet");
			return;
        }
			response.sendRedirect("MyPageServlet");
	}

}
