package exercises5.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import exercises5.model.EmpBean;

public class EmpDao {
	private Connection connection;

	// コンストラクター
	// java_web_systemデータベースとの接続を行う
	public EmpDao() throws SQLException {
		String url = "jdbc:mysql://localhost:3306/java_web_system";
		String user = "root";
		String password = "root";
		connection = DriverManager.getConnection(url, user, password);
	}

	// java_web_systemデータベースとの切断するメソッド
	public void close() {
		try {
			if (connection != null) {
				connection.close();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	// 引数に従業員IDを受け取り、従業員検索を行うメソッド
	public EmpBean getEmpDataById(int empId) throws SQLException {
		EmpBean bean = null;
		PreparedStatement pstatement = null;
		ResultSet rs = null;
		try {
			// SQLを保持するPreparedStatementオブジェクトの生成
			String sql = "SELECT * FROM employee WHERE employee_id= ?";
			pstatement = connection.prepareStatement(sql);
			// INパラメーターの設定
			pstatement.setInt(1, empId);
			// SQLの発行し、抽出結果が格納されたResultSetオブジェクトを取得
			rs = pstatement.executeQuery();
			if (rs.next()) {
				// 列名を指定してResultSetオブジェクトから値を取得
				bean = new EmpBean();
				bean.setId(rs.getInt("employee_id"));
				bean.setName(rs.getString("employee_name"));
				bean.setAddress(rs.getString("employee_address"));
				bean.setAge(rs.getInt("employee_age"));
				bean.setMail(rs.getString("employee_mail"));
			}
			// ResultSetオブジェクトの解放
			rs.close();
		} finally {
			// PreparedStatementオブジェクトの解放
			pstatement.close();
		}
		return bean;
	}

	// 引数に指定された年齢を2つ受け取り、from 以上 to 以下の従業員を検索するメソッド
	public ArrayList<EmpBean> getEmpDataByAge(int age1, int age2) throws SQLException {
		ArrayList<EmpBean> empData = null;
		PreparedStatement pstatement = null;
		ResultSet rs = null;
		try {
			// SQLを保持するPreparedStatementオブジェクトの生成
			String sql = "SELECT * FROM employee WHERE employee_age BETWEEN ? AND ?";
			pstatement = connection.prepareStatement(sql);
			// INパラメーターの設定
			pstatement.setInt(1, age1);
			pstatement.setInt(2, age2);
			// SQLの発行し、抽出結果が格納されたResultSetオブジェクトを取得
			rs = pstatement.executeQuery();
			empData = new ArrayList<EmpBean>();
			// 取得した従業員情報は、複数の可能性があるので、
			// while文を用いてResultSetオブジェクトから従業員情報を取得する
			while (rs.next()) {
				EmpBean bean = new EmpBean();
				bean.setId(rs.getInt("employee_id"));
				bean.setName(rs.getString("employee_name"));
				bean.setAddress(rs.getString("employee_address"));
				bean.setAge(rs.getInt("employee_age"));
				bean.setMail(rs.getString("employee_mail"));
				// 取得した値をEmpBeanオブジェクトに格納し、
				// そのオブジェクトをArrayListオブジェクトに追加
				empData.add(bean);
			}
			// ResultSetオブジェクトの解放
			rs.close();
		} finally {
			// PreparedStatementオブジェクトの解放
			pstatement.close();
		}
		return empData;
	}
	// 引数に指定された従業員情報を受け取り、employee表に登録するメソッド
	public int insertEmpData(EmpBean emp) throws SQLException {
		int numRow = 0;
		PreparedStatement pstatement = null;
		try {
			// トランザクション開始
			connection.setAutoCommit(false);
			// SQLを保持するPreparedStatementオブジェクトの生成
			String sql = "INSERT INTO employee (employee_name, employee_address, employee_age, employee_mail) VALUES (?,?,?,?)";
			pstatement = connection.prepareStatement(sql);
			// INパラメーターの設定
			pstatement.setString(1, emp.getName());
			pstatement.setString(2, emp.getAddress());
			pstatement.setInt(3, emp.getAge());
			pstatement.setString(4, emp.getMail());
			// SQLの発行し、employee表に登録された行数を取得
			numRow = pstatement.executeUpdate();
		} finally {
			if(numRow > 0){
				// 登録成功時はコミット
				connection.commit();
			}else{
				// 登録失敗時はロールバック
				connection.rollback();
			}
			// PreparedStatementオブジェクトの解放
			pstatement.close();
		}
		return numRow;
	}
	
	public int deleteEmpData(int emp)throws SQLException{
		int num = 0;
		PreparedStatement pstatement = null;
		
		try {
			connection.setAutoCommit(false);
			String sql = "DELETE FROM employee WHERE employee_id=?";
			pstatement = connection.prepareStatement(sql);
			pstatement.setInt(1, emp);
			num = pstatement.executeUpdate();
		}finally {
			if(num > 0) {
				connection.commit();
			}else {
				connection.rollback();
				pstatement.close();
			}
			
		}
		return num;
	}
}