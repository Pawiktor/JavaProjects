package pl.dev_app.GameSettings;

import java.io.File;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class DataBase {

	private Connection con;
	private ResultSet rs;
	private Statement st;
	public DataBase() {
		connect();
	}

	private void connect() {
		try {
			File file = new File("");
			String path = file.getAbsolutePath().replaceAll("\\\\", "/") + "/src/main/resources/dataBase/Scores.accdb";
			con = DriverManager.getConnection("jdbc:ucanaccess://" + path);
			st = con.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
			rs = st.executeQuery("SELECT * FROM names order by moves asc " );
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public void insertWinners(String name, int moves) {
		try {
			String sql = ("insert into names (winners,moves) values (?,?)");
			PreparedStatement preparedStmt = con.prepareStatement(sql);
			preparedStmt.setString(1, name);
			preparedStmt.setInt(2, moves);
			preparedStmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public String[][] getWinners() {
		String[][] tab = new String[3][2];
		try {
			for(int i =0; i< tab.length; i++) {
				if(rs.next()) {
				tab[i][0] = rs.getString(1);
				tab[i][1] = String.valueOf(rs.getInt(2));}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return tab;
	}
	
	public void deleteAll() {
		try {
			String query = "DELETE FROM names";
			st.executeUpdate(query);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public void closeDb() {
		try {
			con.close();
			st.close();
			rs.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
