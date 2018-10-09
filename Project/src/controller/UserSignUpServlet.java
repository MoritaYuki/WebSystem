package controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.CommonDao;
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

		// 性別ラジオボタンの値
		String[] sexlist = {"男","女"};
    	request.setAttribute("sexlist", sexlist);

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
        String passwordRe = request.getParameter("inputPasswordRe");

		String [] userData = {request.getParameter("inputLoginId"),
							   request.getParameter("inputPassword"),
							   request.getParameter("inputGrade"),
							   request.getParameter("inputUserNamePhonetic"),
							   request.getParameter("inputUserName"),
							   request.getParameter("inputSex"),
							   request.getParameter("inputBirthday"),
							   request.getParameter("inputContactInfo"),
							   request.getParameter("inputAddress")
							   };

		String [] lavelList = {"loginId",
							   "password",
							   "grade",
							   "userNamePhonetic",
							   "userName",
							   "sex",
							   "birthday",
							   "contactInfo",
							   "address"
							   };

		// 入力フォームに不備がある場合は再度入力フォームに戻る
		if(userDao.formCheck(userData, passwordRe)) {

			//エラーメッセージをリクエストスコープに保管
        	request.setAttribute("errMsg", "入力内容が正しくありません");

        	//フォームの入力内容をリクエストスコープに保存
        	request.setAttribute("passwordRe", passwordRe);

        	for(int i=0; i<lavelList.length; i++) {
        		if(lavelList[i].equals("grade") && !new CommonDao().strCheck(userData[i])) {
        			request.setAttribute(lavelList[i], Integer.parseInt(userData[i]));
        			continue;
        		}
        		request.setAttribute(lavelList[i], userData[i]);
        	}

        	// 性別ラジオボタンの値
        	String[] sexlist = {"男","女"};
        	request.setAttribute("sexlist", sexlist);

        	//signUp.jspにフォワード
        	request.getRequestDispatcher("/WEB-INF/jsp/user_signup.jsp").forward(request, response);
            return;
		}

        // signupメソッドを使って、DB上に入力された情報を登録
        int insertNum = userDao.signup(userData);
        // 確認用
        System.out.println(insertNum);

        // 登録が成功した場合はユーザ一覧へリダイレクト
        request.getSession().setAttribute("signMsg", "アカウント情報を登録しました");
        response.sendRedirect("CourseSignUpServlet");
	}
}
