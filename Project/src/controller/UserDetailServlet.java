package controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.ApplicationDao;
import dao.CourseDao;
import dao.ExamDao;
import dao.UserDao;
import model.Application;
import model.Course;
import model.Exam;
import model.User;

/**
 * Servlet implementation class UserDetailServlet
 */
@WebServlet("/UserDetailServlet")
public class UserDetailServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public UserDetailServlet() {
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

		// fgの値によって、アカウント詳細かテスト結果の画面に分岐
		int fg;
		if(request.getParameter("fg") ==null) {
			fg = 0;
		}else {
			fg = Integer.parseInt(request.getParameter("fg"));
		}
		request.setAttribute("fg", fg);

		// examListが空なら表示しなくてもいいのでは？？

		// ユーザIDの取得
		int userId = user.getUserId();
		// 分岐点
		if(fg == 1) {
			// ユーザIDに紐づいたテスト結果リストを取得する
			List<Exam> examList = new ExamDao().findByUserId(String.valueOf(userId));
			request.setAttribute("examList", examList);

			// 成績一覧のjspにフォワード
			request.getRequestDispatcher("/WEB-INF/jsp/exam_result.jsp").forward(request, response);
			return;
		}else {
			// 取得したユーザidを用いてユーザ情報と申込情報を取得
			User userData = new UserDao().searchByUserId(String.valueOf(userId));
			request.setAttribute("userData", userData);

			// ユーザIDに紐づいた申込リストを取得後、入金済みのものだけ受講講座リストに追加していく
			List<Application> applicationList = new ApplicationDao().findApplicationByUserId(userId);
			List<Course> appCourseList = new ArrayList<Course>();
			for(Application application: applicationList) {
				if(application.isPayFg()) {
					List<Course> applicationDetail = new CourseDao().findCourseByApplicationNo(String.valueOf(application.getApplicationNo()));
					appCourseList.addAll(applicationDetail);
				}
			}
			request.setAttribute("appCourseList", appCourseList);

			// 講習情報を取得
			session.setAttribute("sCourseList", new Course().getsCourseList());
			// 性別情報の取得
			String[] sexlist = { "男", "女" };
			request.setAttribute("sexlist", sexlist);

			// アカウント詳細のjspにフォワード
			request.getRequestDispatcher("/WEB-INF/jsp/user_detail.jsp").forward(request, response);
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
