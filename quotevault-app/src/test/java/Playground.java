import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Calendar;

import org.junit.Ignore;
import org.junit.Test;

public class Playground {

//	@Test
//	public void testDatabaseCnx() throws SQLException {
//		String user = "admin";
//		String password = "admin";
//		String url = "jdbc:h2:~/quotevault";
//		Connection con = DriverManager.getConnection(url, user, password);
//		Date date = new Date(Calendar.getInstance().getTimeInMillis());
//		String query = "INSERT INTO Quotes VALUES(?, ?, ?, ?,?)";
//		PreparedStatement ps = con.prepareStatement(query);
//		ps.setInt(1, 2);
//		ps.setString(2, "MB");
//		ps.setString(3, "Some quote");
//		ps.setString(4, "My mind");
//		ps.setDate(5, date);
//		ps.executeUpdate();
//	}
//
//	@Test
//	public void selectAllFromDatabase() throws SQLException {
//		String user = "admin";
//		String password = "admin";
//		String url = "jdbc:h2:~/quotevault";
//		Connection con = DriverManager.getConnection(url, user, password);
//		String query = "SELECT * FROM QUOTES";
//		Statement stt = con.createStatement();
//		ResultSet rs = stt.executeQuery(query);
//		while (rs.next()) {
//			System.out.println(rs.getInt(1) + "-> Author: " + rs.getString(2) + " | Body: " + rs.getString(3) + " | Source: " + rs.getString(4));
//		}
//
//	}
}