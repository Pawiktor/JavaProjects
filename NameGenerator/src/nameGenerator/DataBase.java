package nameGenerator;

import java.sql.*;

public class DataBase {

	private Connection con;
	private Statement st;
	private ResultSet result;
	public DataBase(String urlDataBase) throws SQLException {
		con = DriverManager.getConnection("jdbc:ucanaccess://" + urlDataBase);
	}
	
	public ResultSet query(String SQL) throws SQLException{
		st = con.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_UPDATABLE);
		result = st.executeQuery(SQL);
		return result;
	}
	
	public void closeDB(){
		try {
			con.close();
			st.close();
			result.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	
}
