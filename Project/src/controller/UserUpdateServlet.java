package controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.UserDao;
import model.User;

/**
 * Servlet implementation class UserSignUPServlet
 */
@WebServlet("/UserUpdateServlet")
public class UserUpdateServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public UserUpdateServlet() {
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

		// リクエストパラメータの文字コードを指定
        request.setCharacterEncoding("UTF-8");

		// 取得したユーザIDからユーザ情報を取得
		String userId = request.getParameter("userId");
		request.setAttribute("user", new UserDao().searchByUserId(userId));

		// 性別ラジオボタンの値
		String[] sexlist = {"男","女"};
    	request.setAttribute("sexlist", sexlist);

		// user_signup.jspにフォワード
		request.getRequestDispatcher("/WEB-INF/jsp/user_update.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		UserDao userDao = new UserDao();

		// リクエストパラメータの文字コードを指定
        request.setCharacterEncoding("UTF-8");

		// リクエストパラメータの入力項目を配列に取得
		String[] userData = {request.getParameter("inputUserId"),
							request.getParameter("inputLoginId"),
							request.getParameter("inputPassword"),
							request.getParameter("inputGrade"),
							request.getParameter("inputUserNamePhonetic"),
							request.getParameter("inputUserName"),
							request.getParameter("inputSex"),
							request.getParameter("inputBirthday"),
							request.getParameter("inputContactInfo"),
							request.getParameter("inputAddress")};

		String passwordRe = request.getParameter("inputPasswordRe");

		User user = new User(Integer.parseInt(userData[0]), userData[1], userData[2], Integer.parseInt(userData[3]),
							userData[4], userData[5], userData[6], User.getFormatBirthday(userData[7]), userData[8], userData[9]);

		// 入力フォームに不備がある場合は再度入力フォームに戻る
		if(userDao.updateFormCheck(userData, passwordRe)) {

			//エラーメッセージをリクエストスコープに保管
        	request.setAttribute("errMsg", "入力内容が正しくありません");

        	// 性別ラジオボタンの値
    		String[] sexlist = {"男","女"};
        	request.setAttribute("sexlist", sexlist);

        	//値を保存して、user_update.jspにフォワード
        	request.setAttribute("user", user);
        	request.getRequestDispatcher("/WEB-INF/jsp/user_update.jsp").forward(request, response);
            return;
		}

        // userUpdateメソッドを使って、DB上に入力された情報を登録
        userDao.userUpdate(userData);

        // 登録が成功した場合はユーザ一覧へリダイレクト
        request.getSession().setAttribute("signMsg", "アカウント情報を更新しました");
        response.sendRedirect("UserListServlet");
	}
}
