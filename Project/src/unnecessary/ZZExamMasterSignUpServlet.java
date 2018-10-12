package unnecessary;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.UserDao;
import model.Exam;
import model.User;

/**
 * Servlet implementation class ExamMasterServlet
 */
@WebServlet("/ExamMasterSignUpServlet")
public class ZZExamMasterSignUpServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public ZZExamMasterSignUpServlet() {
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

		// リクエストスコープにユーザ一覧情報をセット
		request.setAttribute("user", new UserDao().searchByUserId(request.getParameter("userId")));

		//exam_master_signup.jspにフォワード
		request.getRequestDispatcher("/WEB-INF/jsp/exam_master_signup.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// リクエストパラメータの文字コードを指定
        request.setCharacterEncoding("UTF-8");

		// リクエストパラメータの入力項目を取得
        int userId = Integer.parseInt(request.getParameter("inputUserId"));
        int year = Integer.parseInt(request.getParameter("inputYear"));
        int term = Integer.parseInt(request.getParameter("inputTerm"));
        int grade = Integer.parseInt(request.getParameter("inputTerm"));
        int japanese = Integer.parseInt(request.getParameter("inputJapanese"));
        int math = Integer.parseInt(request.getParameter("inputMath"));
        int english = Integer.parseInt(request.getParameter("inputEnglish"));
        int science = Integer.parseInt(request.getParameter("inputScience"));
        int social = Integer.parseInt(request.getParameter("inputSocial"));
		String comment = request.getParameter("inputComment");

		// scoreテーブルに入力情報を挿入
		Exam exam = new Exam(userId, year, grade, term, japanese, math, english, science, social, comment);
		//new ExamDao().insertScore(exam);

		// リクエストスコープにユーザ一覧情報をセット
		request.getSession().setAttribute("signMsg", "登録が完了しました。");

		// テスト結果マスタの一覧にリダイレクト
		response.sendRedirect("ExamMasterServlet");
	}

}
