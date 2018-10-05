package controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.ApplicationDao;
import model.Application;
import model.User;

/**
 * Servlet implementation class ApplicationListServlet
 */
@WebServlet("/ApplicationListServlet")
public class ApplicationListServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public ApplicationListServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		HttpSession session = request.getSession();
		// ログインセッションがない場合、ログイン画面にリダイレクトさせる
		User user = (User) session.getAttribute("loginInfo");
		if (user == null) {
			response.sendRedirect("LoginServlet");
			return;
		}

		// 入金登録についてメッセージがあれば保存する
		String sm = request.getParameter("sm");
		if(sm != null) {
			switch(sm) {
				case "0":
					request.setAttribute("signMsg", "入金登録が完了しました。");
					break;
				case "1":
					request.setAttribute("signMsg", "入金情報は更新されていません");
					break;
			}
		}

		// 申込一覧情報を取得
		request.setAttribute("applicationList", new ApplicationDao().findAllApplication());
		// セッションに学年がなければ、全学年を取得
		if (session.getAttribute("grade") == null) {
			session.setAttribute("grade", "全");
		}

		// 入金フラッグの情報を取得
		session.setAttribute("payFgList", new Application().getPayFgList());

		// 申込一覧のjspにフォワード
		request.getRequestDispatcher("/WEB-INF/jsp/application_list.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();

		// リクエストパラメータの文字コードを指定
		request.setCharacterEncoding("UTF-8");

		// リクエストパラメータの入力項目を取得
		String grade = request.getParameter("inputGrade");
		String userName = request.getParameter("inputUserName");
		String loginId = request.getParameter("inputLoginId");
		String start = request.getParameter("inputStart");
		String end = request.getParameter("inputEnd");
		String applicationNo = request.getParameter("inputApplicationNo");

		// 入金フラッグの情報を取得
		session.setAttribute("payFgList", new Application().getPayFgList());

		// セッションに検索講座一覧情報をセット
		session.setAttribute("courseList", new ApplicationDao().search(grade, userName, loginId, start, end, applicationNo));

		// 各変数の値保持
		request.setAttribute("grade", grade);
		request.setAttribute("userName", userName);
		request.setAttribute("loginId", loginId);
		request.setAttribute("start", start);
		request.setAttribute("end", end);
		request.setAttribute("applicationNo", applicationNo);

		// 申込一覧のjspにフォワード
		request.getRequestDispatcher("/WEB-INF/jsp/application_list.jsp").forward(request, response);
	}

}
