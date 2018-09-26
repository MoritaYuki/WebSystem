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
 * Servlet implementation class UserSignUPServlet
 */
@WebServlet("/UserSignUpServlet")
public class UserSignUpServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public UserSignUpServlet() {
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

		// user_signup.jspにフォワード
		request.getRequestDispatcher("/WEB-INF/jsp/user_signup.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		UserDao userDao = new UserDao();

		// リクエストパラメータの文字コードを指定
        request.setCharacterEncoding("UTF-8");

		// リクエストパラメータの入力項目を配列に取得
		String [] userData = {request.getParameter("inputLoginId"),
							   request.getParameter("inputPassword"),
							   request.getParameter("inputPasswordRe"),
							   request.getParameter("inputGrade"),
							   request.getParameter("inputUserNamePhonetic"),
							   request.getParameter("inputUserName"),
							   request.getParameter("inputSex"),
							   request.getParameter("inputBirthday"),
							   request.getParameter("inputAddress")
							   };

		String [] lavelList = {"loginId",
							   "password",
							   "passwordRe",
							   "grade",
							   "userNamePhonetic",
							   "userName",
							   "sex",
							   "birthday",
							   "address"
							   };

		// 入力フォームに不備がある場合は再度入力フォームに戻る
		if(userDao.formCheck(userData)) {

			//エラーメッセージをリクエストスコープに保管
        	request.setAttribute("errMsg", "入力内容が正しくありません");

        	//フォームの入力内容をリクエストスコープに保存
        	for(int i=0, j=0; i<lavelList.length || j<userData.length; i++,j++) {
        		request.setAttribute(lavelList[i], userData[j]);
        	}

        	//signUp.jspにフォワード
        	request.getRequestDispatcher("/WEB-INF/jsp/user_signup.jsp").forward(request, response);
            return;
		}

        // signupメソッドを使って、DB上に入力された情報を登録
        int insertNum = userDao.signup(userData);
        // 確認用
        System.out.println(insertNum);

        // 登録が成功した場合はユーザ一覧へリダイレクト
        response.sendRedirect("UserListServlet");
	}
}
