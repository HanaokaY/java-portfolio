package exercises5.servlet;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import exercises5.service.InsertEmp;

/**
 * Servlet implementation class InsertController
 */
@WebServlet("/insert")
public class InsertController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// リクエスト処理
		request.setAttribute("message", "従業員情報を入力してください");
		// 転送処理
		ServletContext context = getServletContext();
		RequestDispatcher rd = context.getRequestDispatcher("/insert.jsp");
		rd.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// リクエスト処理
		request.setCharacterEncoding("UTF-8");
		String searchBtn = request.getParameter("btn"); // 押されたボタン情報
		String jsp;
		try {
			// 従業員登録の場合
			if(searchBtn != null && searchBtn.equals("InsertEmp")) {
				InsertEmp insert = new InsertEmp();
				insert.execute(request);
				jsp = "/insert.jsp";
			}else {
				request.setAttribute("errorMessage", "不正アクセスです");
				jsp = "/error.jsp";
			}
		} catch (NumberFormatException e) {
			e.printStackTrace();
			request.setAttribute("errorMessage", "年齢には数値を入力してください");
			jsp = "/error.jsp";
		} catch (SQLException e) {
			e.printStackTrace();
			request.setAttribute("errorMessage", "JDBCのエラーです");
			jsp = "/error.jsp";
		} catch (Exception e) {
			e.printStackTrace();
			request.setAttribute("errorMessage", "エラーが発生しました");
			jsp = "/error.jsp";
		}
		// 転送処理
		ServletContext context = getServletContext();
		RequestDispatcher rd = context.getRequestDispatcher(jsp);
		rd.forward(request, response);
	}
}