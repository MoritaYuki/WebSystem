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
import dao.CourseDao;
import model.Application;
import model.Course;
import model.User;

/**
 * Servlet implementation class PaymentHistoryDetailServlet
 */
@WebServlet("/ApplicationDetailServlet")
public class ApplicationDetailServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public ApplicationDetailServlet() {
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

		// 申込番号に紐づいた講座情報と申込情報を取得、合計金額とともにリクエストへ保存する
		String applicationNo = request.getParameter("applicationNo");
		Application application = new ApplicationDao().findApplicationByApplicationNo(applicationNo);
		List<Course> applicationDetailList = new  CourseDao().findCourseByApplicationNo(applicationNo);
		int totalPrice = 0;
		for(Course course: applicationDetailList) {
			totalPrice += course.getPrice();
		}
		request.setAttribute("application", application);
		request.setAttribute("applicationDetailList", applicationDetailList);
		request.setAttribute("totalPrice", totalPrice);

		// 講習と入金フラッグの情報を取得
		session.setAttribute("sCourseList", new Course().getsCourseList());
		session.setAttribute("payFgList", new Application().getPayFgList());

		// 申込・入金履歴詳細のjspにフォワード
		request.getRequestDispatcher("/WEB-INF/jsp/application_detail.jsp").forward(request, response);
	}
}
