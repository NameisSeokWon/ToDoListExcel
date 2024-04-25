package todolist.signup.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import todolist.database.ToDoListDbConn;

public class ToDoListSignUpDao {
	
	public boolean toDoListSignUp(String id, String password, String namekanji, String namekatakana, String nameenglish, String phone, String address, String gender, String email, String birth, String employment) {
		ToDoListDbConn tdldc = new ToDoListDbConn();
		Connection cn = tdldc.connection();
		
		try {
			String sql = "INSERT INTO weavusinfo (id, password, namekanji, namekatakana, nameenglish, phone, address, gender, email, birth, employment) VALUE (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
			PreparedStatement ps = cn.prepareStatement(sql);
			ps.setString(1, id);
			ps.setString(2, password);
			ps.setString(3, namekanji);
			ps.setString(4, namekatakana);
			ps.setString(5, nameenglish);
			ps.setString(6, phone);
			ps.setString(7, address);
			ps.setString(8, gender);
			ps.setString(9, email);
			ps.setString(10, birth);
			ps.setString(11, employment);
			
			
			int affectedRows = ps.executeUpdate();
			return affectedRows == 1;
			
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}		
	}
}
