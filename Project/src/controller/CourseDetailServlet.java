package controller;

import java.io.IOException;

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
 * Servlet implementation class CourseDetailServlet
 */
@WebServlet("/CourseDetailServlet")
public class CourseDetailServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public CourseDetailServlet() {
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

		// 取得した講座idを用いてインスタンスを取得
		Course course = new CourseDao().findByCourseId(request.getParameter("courseId"));
		request.setAttribute("course", course);

		// 講座詳細のjspにフォワード
		request.getRequestDispatcher("/WEB-INF/jsp/course_detail.jsp").forward(request, response);
	}
}
