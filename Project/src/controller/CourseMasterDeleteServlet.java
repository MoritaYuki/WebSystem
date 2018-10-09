package controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.CourseDao;
import model.Course;
import model.User;

/**
 * Servlet implementation class CourseMasterDeleteServlet
 */
@WebServlet("/CourseMasterDeleteServlet")
public class CourseMasterDeleteServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public CourseMasterDeleteServlet() {
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

		// リクエストパラメータの文字コードを指定
		request.setCharacterEncoding("UTF-8");
		String courseId = request.getParameter("courseId");
		request.setAttribute("courseId", courseId);

		// 講座情報削除を確認後の処理
		// リクエストスコープの"delFg"に値がある場合のみ講座情報を削除する
		if (request.getParameter("delFg") != null) {
			// 講座情報を削除して、新なリストを取得後、講座マスタ一覧画面に遷移
			new CourseDao().courseDelete(courseId);
			String cGradeNo = (String)session.getAttribute("cGradeNo");
			if(cGradeNo.equals("全")) {
				cGradeNo = null;
			}
			String courseName = (String)session.getAttribute("courseName");
			String teacher = (String)session.getAttribute("teacher");
			request.getSession().setAttribute("courseList", new CourseDao().search(cGradeNo, courseName, teacher));
			// 講座マスタ一覧画面へ遷移
			response.sendRedirect("CourseMasterServlet");
			return;
		}

		// 取得した講座idを用いてインスタンスを取得
		Course course = new CourseDao().findByCourseId(courseId);
		request.setAttribute("course", course);

		// 講習情報を取得
		session.setAttribute("sCourseList", new Course().getsCourseList());

		// courseDelete.jspにフォワード
		RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/course_master_delete.jsp");
		dispatcher.forward(request, response);
	}
}
