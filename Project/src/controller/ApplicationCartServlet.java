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

import dao.CourseDao;
import model.Course;
import model.User;

/**
 * Servlet implementation class ApplicationCartServlet
 */
@WebServlet("/ApplicationCartServlet")
public class ApplicationCartServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public ApplicationCartServlet() {
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

		// 仮申込一覧を取得
		@SuppressWarnings("unchecked")
		List<Course> cartList = (List<Course>)session.getAttribute("cartList");

		// 取得できなければ新規に作成
		if(cartList == null) {
			cartList = new ArrayList<Course>();
		}

		Course course = new CourseDao().findByCourseId(request.getParameter("courseId"));
		int totalprice = 0;
		String errMsg = null;

		// 同じ講座が仮申込された場合はエラーメッセージを保存する。
		for(Course c: cartList) {
			totalprice += c.getPrice();
			if(course.getCourseId() == c.getCourseId()) {
				errMsg = "この講座は既に仮申込済みです。";
				request.setAttribute("errMsg", errMsg);
			}
		}

		// 同じ講座が申し込まれていない場合は、一覧に追加する。
		if(errMsg == null) {
			cartList.add(course);
			totalprice += course.getPrice();
			session.setAttribute("cartList", cartList);
		}

		// 合計金額をリクエストに保存
		request.setAttribute("totalprice", totalprice);

		// 講座詳細のjspにフォワード
		request.getRequestDispatcher("/WEB-INF/jsp/application_cart.jsp").forward(request, response);
	}
}
