package Sports_ams.data_access;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import Sports_ams.data_model.Category;

public class Category_dao {

	private static String DRIVER = "com.mysql.jdbc.Driver";
	private static String DB_URL = "jdbc:mysql://localhost/dbms_project_ams";
	private static String Username = "root";
	private static String Password = "mysql";
	private Connection connection =null;
	private PreparedStatement statement = null;
	private ResultSet resultset = null;

	static Category_dao instance = null;
	
	private Category_dao() {}
	
	public static Category_dao getInstance()
	{
		if (instance == null)
		{
			instance = new Category_dao();
		}
		return instance;
	}
	
	public void addCategpory(String table_name, List<String> attributes, int no_attributes)
	{
		try {
			Class.forName(DRIVER);
			connection = DriverManager.getConnection(DB_URL,Username,Password);
			statement = connection.prepareStatement("INSERT INTO category(category_name) values(?)");
			statement.setString(1,table_name);
			statement.executeUpdate();
			statement.close();
			String query ="CREATE TABLE "+table_name+"(id INT NOT NULL AUTO_INCREMENT";
			for(int i=0;i<no_attributes;i++)
			{
				query = query+ ","+ attributes.get(i)+" VARCHAR(25)";
			}
			query += ")";
			statement = connection.prepareStatement(query);
			statement.execute();
			statement.close();
			connection.close();
		
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
		
	}
	
	public List<Category> getCategories()
	{
		List<Category> categories = new ArrayList<Category>();
		
		try {
			Class.forName(DRIVER);
			connection = DriverManager.getConnection(DB_URL,Username,Password);
			statement = connection.prepareStatement("Select * from category");
			resultset = statement.executeQuery();
			int cnt = 0;
			while(resultset.next())
			{
				int category_id = resultset.getInt("category_id");
				String category_name = resultset.getString("category_name");
				Category category = new Category(category_id,category_name);
				categories.add(cnt,category);
				cnt ++;
			}
			resultset.close();
			statement.close();
			connection.close();
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
		return categories;
	}


}
