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
 * Servlet implementation class CourseMasterSignUpServlet
 */
@WebServlet("/CourseMasterUpdateServlet")
public class CourseMasterUpdateServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public CourseMasterUpdateServlet() {
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

		// 取得した講座IDと一致する講座情報をリクエストスコープに保存
		request.setAttribute("course", new CourseDao().findByCourseId(request.getParameter("courseId")));

		// 講習ラジオボタンの値
		request.setAttribute("sCourseList", new Course().getsCourseList());

		// ユーザ情報更新のjspにフォワード
		RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/course_master_update.jsp");
		dispatcher.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		CourseDao courseDao = new CourseDao();


//		post部分の作成をしましょう！！！！


		// リクエストパラメータの文字コードを指定
        request.setCharacterEncoding("UTF-8");

		// リクエストパラメータの入力項目を配列に取得
		String [] courseData = {request.getParameter("inputGrade"),
								request.getParameter("inputCourseName"),
								request.getParameter("inputTeacher"),
								request.getParameter("inputTerm"),
								request.getParameter("inputPrice"),
								request.getParameter("inputCourseDetail")
								};

		String [] lavelList = {"grade",
							   "courseName",
							   "teacher",
							   "term",
							   "price",
							   "courseDetail"
							   };

		// 入力フォームに不備がある場合は再度入力フォームに戻る
		if(courseDao.formCheck(courseData)) {

			//エラーメッセージをリクエストスコープに保管
        	request.setAttribute("errMsg", "入力内容が正しくありません");

        	for(int i=0; i<lavelList.length; i++) {
        		if(lavelList[i].equals("grade") && courseData[i] != null) {
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
        int insertNum = courseDao.signup(courseData);
        // 確認用
        System.out.println(insertNum);

        // 登録が成功した場合は講座マスタ一覧へリダイレクト
        request.getSession().setAttribute("signMsg", "講座マスタ情報を登録しました");
        response.sendRedirect("CourseMasterServlet");
	}

}
