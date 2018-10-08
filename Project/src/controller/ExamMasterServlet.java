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

import dao.CommonDao;
import dao.ExamDao;
import dao.UserDao;
import model.Exam;

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

		CommonDao commonDao = new CommonDao();

		// 登録完了メッセージを取得、セッションスコープを削除
		HttpSession session = request.getSession();
		request.setAttribute("signMsg", session.getAttribute("signMsg"));
		session.removeAttribute("signMsg");

		// 表示するテスト結果の学年を変更するための代入
		// eGradeNoを受け取って、セッションに保存
		String eGradeNo = request.getParameter("eGradeNo");
		if(commonDao.strCheck(eGradeNo)) {
			session.setAttribute("eGradeNo", 1);
		}else {
			session.setAttribute("eGradeNo", Integer.parseInt(eGradeNo));
		}

		// 最初にgetアクセスしてきたときの表示を作るための代入
		// セッションにテスト結果リストがなければ、全テスト結果リストを取得
		if(session.getAttribute("examList") == null) {
			session.setAttribute("examList", new ExamDao().findAll());
		}

		// セッションに学期がなければ、全学期のリストを取得
		String eTermNo = request.getParameter("eTermNo");
		if(commonDao.strCheck(eTermNo)) {
			session.setAttribute("eTermNo", 1);
		}else {
			session.setAttribute("eTermNo", Integer.parseInt(eTermNo));
		}

		// セッションに年度がなければ、現在年度を取得
		if(session.getAttribute("year") == null) {
			session.setAttribute("year", new Exam().getYearNow());
		}

		// jspFgがnullなら0を、それ以外のときはその値を保存する
		String jspFg = request.getParameter("jspFg");
		if(commonDao.strCheck(jspFg)) {
			session.setAttribute("jspFg", 0);
		}
		if(!commonDao.strCheck(jspFg)) {
			session.setAttribute("jspFg", Integer.parseInt(jspFg));
		}
		//exam_master.jspにフォワード
		request.getRequestDispatcher("/WEB-INF/jsp/exam_master.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// リクエストパラメータの文字コードを指定
        request.setCharacterEncoding("UTF-8");

		CommonDao commonDao = new CommonDao();
		ExamDao examDao = new ExamDao();
		HttpSession session = request.getSession();

		// リクエストパラメータの入力項目を取得
		String year = request.getParameter("inputYear");
		String eTermNo = request.getParameter("eTermNo");
		String eGradeNo = request.getParameter("eGradeNo");

		// 各フォームにて空欄で検索された際に値を保持する
		if (!commonDao.strCheck(eTermNo)) {
			session.setAttribute("eTermNo", eTermNo);
		}
		if (!commonDao.strCheck(year)) {
			session.setAttribute("year", year);
		}

		if(request.getParameter("search") != null) {
			// セッションに検索テスト一覧情報をセット(未実装)
			session.setAttribute("examList", new ExamDao().search(year, eTermNo, eGradeNo));
		}else if(request.getParameter("update") != null) {
			// 国数英理社の5科目の得点を取得し、userIdと紐づけてデータベースを更新する。
			// userIdの最大値を取得し、最大値まで上記の作業を回す。
			List<Exam> examList = new ArrayList<Exam>();
			int maxUserId = new UserDao().getMaxUserId();
			for(int i=2; i <= maxUserId; i++) {
				// ユーザIDが取得できないのは更新しない生徒であるため飛ばす
				String userId =request.getParameter("userId" + i);
				if(userId == null) {
					continue;
				}
				String[] examData = {request.getParameter("japanese" + i),
									request.getParameter("math" + i),
									request.getParameter("english" + i),
									request.getParameter("science" + i),
									request.getParameter("social" + i),
									userId};
				if(examDao.formCheck(examData)) {
					// エラーメッセージを保存して、テスト結果一覧のjspにフォワード
					request.setAttribute("errMsg", "空欄もしくは正しくない数値の入力があります。");
					// テスト結果一覧のjspにフォワード
					request.getRequestDispatcher("/WEB-INF/jsp/exam_master.jsp").forward(request, response);
					return;

				}

				int intYear = (int)session.getAttribute("year");
				int term = (int)session.getAttribute("eTermNo");
				int japanese = Integer.parseInt(request.getParameter("japanese" + i));
				int math = Integer.parseInt(request.getParameter("math" + i));
				int english = Integer.parseInt(request.getParameter("english" + i));
				int science = Integer.parseInt(request.getParameter("science" + i));
				int social = Integer.parseInt(request.getParameter("social" + i));

				Exam exam = new Exam(Integer.parseInt(userId), intYear, term, japanese, math, english, science, social);
				examList.add(exam);
			}
			// 取得したexamListをセッションに保存し、不備がなければ更新する。
			examDao.updateScore(examList);
			session.setAttribute("examList", new ExamDao().findAll());
		}
		// テスト結果一覧のjspにフォワード
		request.getRequestDispatcher("/WEB-INF/jsp/exam_master.jsp").forward(request, response);
	}
}
