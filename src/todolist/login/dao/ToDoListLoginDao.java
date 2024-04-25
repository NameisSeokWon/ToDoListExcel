package todolist.login.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import todolist.database.ToDoListDbConn;

public class ToDoListLoginDao {

	public boolean login(String id, String password) {
		ToDoListDbConn tdldc = new ToDoListDbConn();
		Connection cn = tdldc.connection();
		boolean login_success;

		try {
			String sql = "select * from weavusinfo where id=? and password=?";
			PreparedStatement ps = cn.prepareStatement(sql);
			ps.setString(1, id);
			ps.setString(2, password);

			ResultSet rs = ps.executeQuery();

			login_success = rs.next();
		} catch (SQLException e ) {
			throw new RuntimeException(e);
		}
		return login_success;
	}

}
