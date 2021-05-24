package exercises5.service;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;

import exercises5.dao.EmpDao;
import exercises5.model.EmpBean;

public class AgeSearch {
	public void execute(HttpServletRequest request) throws Exception {
		EmpDao daoTest = null;
		String empAge1 = request.getParameter("paramAge1");
		String empAge2 = request.getParameter("paramAge2");
		try {
			if (empAge1 != null && empAge2 != null && !empAge1.isEmpty() && !empAge2.isEmpty()) {
				daoTest = new EmpDao();
				int age1 = Integer.parseInt(empAge1);
				int age2 = Integer.parseInt(empAge2);
				ArrayList<EmpBean> empList = daoTest.getEmpDataByAge(age1, age2);
				if (empList.isEmpty()) {
					request.setAttribute("message", "該当者はいません");
				} else {
					request.setAttribute("empList", empList);
				}
			} else {
				request.setAttribute("message", "検索年齢を入力してください");
			}
		} finally {
			if (daoTest != null) {
				daoTest.close();
			}
		}
	}
}