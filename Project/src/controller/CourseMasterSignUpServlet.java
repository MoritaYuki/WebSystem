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
 * Servlet implementation class CourseMasterSignUpServlet
 */
@WebServlet("/CourseMasterSignUpServlet")
public class CourseMasterSignUpServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public CourseMasterSignUpServlet() {
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

		// 講習ラジオボタンの値
		request.setAttribute("sCourseList", new Course().getsCourseList());

		//course_master_signup.jspにフォワード
		request.getRequestDispatcher("/WEB-INF/jsp/course_master_signup.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		CourseDao courseDao = new CourseDao();

		// リクエストパラメータの文字コードを指定
        request.setCharacterEncoding("UTF-8");

		// リクエストパラメータの入力項目を配列に取得
		String [] courseData = {request.getParameter("inputCsGradeNo"),
								request.getParameter("inputCourseName"),
								request.getParameter("inputTeacher"),
								request.getParameter("inputCsTermNo"),
								request.getParameter("inputPrice"),
								request.getParameter("inputCourseDetail")
								};

		String [] lavelList = {"csGradeNo",
							   "courseName",
							   "teacher",
							   "csTermNo",
							   "price",
							   "courseDetail"
							   };

		String rootCourseName = "エラー回避用";

		// 入力フォームに不備がある場合は再度入力フォームに戻る
		if(courseDao.formCheck(courseData, rootCourseName)) {

			//エラーメッセージをリクエストスコープに保管
        	request.setAttribute("errMsg", "入力内容が正しくありません");

        	for(int i=0; i<lavelList.length; i++) {
        		if(lavelList[i].equals("csGradeNo") && courseData[i] != null) {
        			request.setAttribute(lavelList[i], Integer.parseInt(courseData[i]));
        			continue;
        		}
        		request.setAttribute(lavelList[i], courseData[i]);
        	}

        	// 講習ラジオボタンの値
    		request.setAttribute("sCourseList", new Course().getsCourseList());

        	//signUp.jspにフォワード
        	request.getRequestDispatcher("/WEB-INF/jsp/course_master_signup.jsp").forward(request, response);
            return;
		}

        // signupメソッドを使って、DB上に入力された情報を登録
        courseDao.signup(courseData);

        // 登録が成功した場合は検索条件を保存して講座マスタ一覧へリダイレクト
        request.getSession().setAttribute("signMsg", "講座マスタ情報を登録しました");
        String cGradeNo = (String)session.getAttribute("cGradeNo");
		if(cGradeNo.equals("全")) {
			cGradeNo = null;
		}
		String courseName = (String)session.getAttribute("courseName");
		String teacher = (String)session.getAttribute("teacher");
		request.getSession().setAttribute("courseList", new CourseDao().search(cGradeNo, courseName, teacher));
        response.sendRedirect("CourseMasterServlet");
	}

}
