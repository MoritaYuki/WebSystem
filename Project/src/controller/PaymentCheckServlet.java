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
import dao.CommonDao;
import dao.CourseDao;
import model.Application;
import model.Course;
import model.User;

/**
 * Servlet implementation class PaymentCheckServlet
 */
@WebServlet("/PaymentCheckServlet")
public class PaymentCheckServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public PaymentCheckServlet() {
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

		// 申込番号に紐づいた講座情報を取得、合計金額とともにリクエストへ保存する
		String applicationNo = request.getParameter("applicationNo");
		Application application = new ApplicationDao().findApplicationByApplicationNo(applicationNo);
		List<Course> applicationDetailList = new  CourseDao().findCourseByApplicationNo(applicationNo);
		int totalPrice = 0;
		for(Course course: applicationDetailList) {
			totalPrice += course.getPrice();
		}
		session.setAttribute("application", application);
		session.setAttribute("applicationDetailList", applicationDetailList);
		session.setAttribute("totalPrice", totalPrice);
		request.setAttribute("totalDef", application.getPayAmount()-totalPrice);

		// 講習情報を取得
		session.setAttribute("sCourseList", new Course().getsCourseList());

		// 講座詳細のjspにフォワード
		request.getRequestDispatcher("/WEB-INF/jsp/payment_check.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		// 過不足計算と本登録で処理を分岐
		if(request.getParameter("cal") != null) {
			HttpSession session = request.getSession();
			Application application = (Application)session.getAttribute("application");
			int totalPrice = (int)session.getAttribute("totalPrice");

			String payment = request.getParameter("payment");
			request.setAttribute("payment", request.getParameter("payment"));

			if(!new CommonDao().strCheck(payment)) {
				int calPayment = Integer.parseInt(payment);
				int totalDef = (application.getPayAmount()+calPayment)-totalPrice;
				request.setAttribute("totalDef", totalDef);
			}

			request.getRequestDispatcher("/WEB-INF/jsp/payment_check.jsp").forward(request, response);
		}else if(request.getParameter("sign") != null) {
				// 本登録の処理を実装しようね！！！！！！
		}
	}

}
