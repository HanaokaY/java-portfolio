package exercises5.service;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;

import exercises5.dao.EmpDao;
import exercises5.model.EmpBean;

public class IdSearch {
	public void execute(HttpServletRequest request) throws Exception {
		EmpDao daoTest = null;
		String empId = request.getParameter("paramId");
		try {
			if (empId != null && !empId.isEmpty()) {
				daoTest = new EmpDao();
				EmpBean empBean = daoTest.getEmpDataById(Integer.parseInt(empId));
				if (empBean != null) {
					ArrayList<EmpBean> empList = new ArrayList<EmpBean>();
					empList.add(empBean);
					request.setAttribute("empList", empList);
				} else {
					request.setAttribute("message", "該当者はいません");
				}
			} else {
				request.setAttribute("message", "検索IDを入力してください");
			}
		} finally {
			if (daoTest != null) {
				daoTest.close();
			}
		}
	}
}