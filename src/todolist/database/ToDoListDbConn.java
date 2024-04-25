package todolist.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ToDoListDbConn {

	public Connection connection() {
		String url = "jdbc:mysql://localhost:3306/test";
		String username = "root";
		String password = "";
		Connection connection = null;
		

	try {
		Class.forName("com.mysql.cj.jdbc.Driver");
		connection = DriverManager.getConnection(url, username, password);

	} catch (ClassNotFoundException e) {
		System.out.println("DB接続に");
	} catch (SQLException e) {
		System.err.println("MYSQL 接続に失敗しました");
	}
	return connection;
	}
}
