package controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.CommonDao;
import dao.ExamDao;

/**
 * Servlet implementation class ExamMasterServlet
 */
@WebServlet("/ExamMasterServlet")
public class ExamMasterServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public ExamMasterServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		// 登録完了メッセージを取得、セッションスコープを削除
		HttpSession session = request.getSession();
		request.setAttribute("signMsg", session.getAttribute("signMsg"));
		session.removeAttribute("signMsg");

		// eGradeNoを受け取って、セッションに保存
		String eGradeNo = request.getParameter("eGradeNo");
		if(new CommonDao().strCheck(eGradeNo)) {
			session.setAttribute("eGradeNo", 1);
		}else {
			session.setAttribute("eGradeNo", Integer.parseInt(eGradeNo));
		}

		// セッションに講座リストがなければ、全講座リストを取得
		if(session.getAttribute("examList") == null) {
			session.setAttribute("examList", new ExamDao().findAll());
		}

		// セッションに学期がなければ、全学期のリストを取得
		if (session.getAttribute("eTermNo") == null) {
			session.setAttribute("eTermNo", "全");
		}

		//course_master.jspにフォワード
		request.getRequestDispatcher("/WEB-INF/jsp/exam_master.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		HttpSession session = request.getSession();

		// リクエストパラメータの文字コードを指定
        request.setCharacterEncoding("UTF-8");

		// リクエストパラメータの入力項目を取得
		String year = request.getParameter("inputYear");
		String eTermNo = request.getParameter("inputETermNo");
		String grade = request.getParameter("grade");

		// セッションに検索講座一覧情報をセット
		session.setAttribute("examList", new ExamDao().search(year, eTermNo, grade));
		if(eTermNo == null) {
			session.setAttribute("eTermNo", "全");
		}else {
			session.setAttribute("eTermNo", eTermNo);
		}
		session.setAttribute("eTermNo", eTermNo);
		session.setAttribute("grade", grade);


		// ユーザ一覧のjspにフォワード
		request.getRequestDispatcher("/WEB-INF/jsp/exam_master.jsp").forward(request, response);
	}

}
