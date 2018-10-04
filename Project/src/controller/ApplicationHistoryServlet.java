package controller;

import java.io.IOException;
import java.util.List;

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
 * Servlet implementation class PaymentHistoryServlet
 */
@WebServlet("/ApplicationHistoryServlet")
public class ApplicationHistoryServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public ApplicationHistoryServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// ログインセッションがない場合、ログイン画面にリダイレクトさせる
		HttpSession session = request.getSession();
		User user = (User) session.getAttribute("loginInfo");
		if (user == null) {
			response.sendRedirect("LoginServlet");
			return;
		}

		// リクエストスコープに申込・入金一覧情報をセット
		int totalDef = 0;
		List<Application> applicationList = new ApplicationDao().findApplicationByUserId(user.getUserId());
		request.setAttribute("applicationList", applicationList);

		// 過不足金の計算
		for(Application app: applicationList) {
			totalDef += app.getPayAmount();
			totalDef -= app.getAppAmount();
		}
		request.setAttribute("totalDef", totalDef);

		// 入金履歴のjspにフォワード
		request.getRequestDispatcher("/WEB-INF/jsp/application_history.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
