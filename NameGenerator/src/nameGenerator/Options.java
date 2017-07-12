package nameGenerator;

import java.io.File;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class Options {

	private DataBase db;
	private ResultSet result;
	private String[] typeNames = {"People", "Games", "Animals"};
	private ArrayList<String> nameTab = new ArrayList<String>();
	private String gender="";
	private int max,min,dbSize;
	
	public void go(int id) {
		
		try {
			if(db==null){
			db = new DataBase(setPath(id));
			result = db.query("SELECT * FROM names");}
		}catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	private String setPath(int id) {
		String dbName = "assets/peopleNames.accdb";
		if(id==2) {
			dbName = "assets/Animals.accdb";}
		
		File file = new File(dbName);
		String path = file.getAbsolutePath();
		path = path.replace("\\", "/");
		return path;
	}
	
	public String[] getTypeNames(){
		return typeNames;
	}
	
	public void setMax(int max){
		this.max = max;
	}
	
	public void setMin(int min){
		this.min = min;
	}
	
	public int getMax(){
		return max;
	}
	
	public int getMin(){
		return min;
	}
	
	public void setGender(String gender){
		this.gender = gender;
	}
	
	public String getGender(){
		return gender;
	}
	
	public int getdbSize(){
		return dbSize;
	}
	
	public String getTabValue(int id){
		return nameTab.get(id);
	}
	
	public int getTabSize(){
		return nameTab.size();
	}
	
	public Object getDbValue(int id) {
		try {
			result.absolute(id);
			return result.getString(2);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public void saveTab(){
		nameTab.clear();
		try {
			while(result.next()){
				String name = result.getString(2);
				if(name.length()<getMin() || name.length()>getMax()){
					continue;
				}
				if(gender.equals("female") && name.charAt(name.length()-1)=='A'){
					nameTab.add(name);
				}else if(gender.equals("male") && name.charAt(name.length()-1)!='A'){
					nameTab.add(name);
				}else if(gender.equals("")){
					nameTab.add(name);
				}
			}
			result.beforeFirst();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void closeDb(){
		db.closeDB();
		db=null;
		result = null;
	}
	
}
