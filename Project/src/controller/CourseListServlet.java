package controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.CommonDao;
import dao.CourseDao;
import model.Course;
import model.User;

/**
 * Servlet implementation class CourseListServlet
 */
@WebServlet("/CourseListServlet")
public class CourseListServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public CourseListServlet() {
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

		// termNoを受け取って、セッションに保存
		String cTermNo = request.getParameter("cTermNo");
		if(new CommonDao().strCheck(cTermNo)) {
			session.setAttribute("cTermNo", 1);
		}else {
			session.setAttribute("cTermNo", Integer.parseInt(cTermNo));
		}

		// セッションに講座リストがなければ、全講座リストを取得
		if(session.getAttribute("courseList") == null) {
			session.setAttribute("courseList", new CourseDao().findAll());
		}

		// セッションに学年がなければ、全学年のリストを取得
		if(session.getAttribute("grade") == null) {
			session.setAttribute("grade", "全");
		}

		// 講習情報を取得
		session.setAttribute("sCourseList", new Course().getsCourseList());

		// 講座一覧のjspにフォワード
		request.getRequestDispatcher("/WEB-INF/jsp/course_list.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// リクエストパラメータの文字コードを指定
		request.setCharacterEncoding("UTF-8");

		// リクエストパラメータの入力項目を取得
		String grade = request.getParameter("inputGrade");
		String courseName = request.getParameter("inputCourseName");
		String teacher = request.getParameter("inputTeacher");

		// セッションに検索講座一覧情報をセット
		request.getSession().setAttribute("courseList", new CourseDao().search(grade, courseName, teacher));

		// 各変数の値保持
		if (grade == null) {
			request.getSession().setAttribute("grade", "全");
		} else {
			request.getSession().setAttribute("grade", grade);
		}
		request.getSession().setAttribute("courseName", courseName);
		request.getSession().setAttribute("teacher", teacher);

		// 講座一覧のjspにフォワード
		request.getRequestDispatcher("/WEB-INF/jsp/course_list.jsp").forward(request, response);
	}

}
