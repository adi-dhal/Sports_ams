package Sports_ams.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import Sports_ams.model.Category;

public class CategoryDao {

	
	public static CategoryDao instance = null;
	private CategoryDao() {}
	public static CategoryDao getInstance() {
		
		if(instance == null) {
			instance = new CategoryDao();
		}
		return instance;
	}
	
	private static final String FIND_ALL_CATEGORIES = "SELECT * FROM category";
	private static String DRIVER = "com.mysql.jdbc.Driver";
	private static String DB_URL = "jdbc:mysql://localhost/DBMS";
	private static String USERNAME = "root";
	private static String PASSWORD = "root";
	
	private Connection connection = null;
	private PreparedStatement statement = null;
	private ResultSet resultset = null;
	
	public ArrayList<Category> findAllCategories() throws SQLException{
		
		ArrayList<Category> categories = new ArrayList<Category>();
		
		try {
			Class.forName(DRIVER);
			connection = DriverManager.getConnection(DB_URL, USERNAME, PASSWORD);;
			statement = connection.prepareStatement(FIND_ALL_CATEGORIES);
			resultset = statement.executeQuery();
			while(resultset.next()) {
				int category_id = resultset.getInt("category_id");
				String category_name = resultset.getString("category_name");
				Category category = new Category(category_id,category_name);
				categories.add(category);
			}
			resultset.close();
			statement.close();
			connection.close();
			
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}	
		
		return categories;		
	}
	private static final String INSERT_CATEGORY = "INSERT INTO category (category_name) VALUES (?)";
	public int createCategory(String new_table,List<String> attributes,int num_attr) throws SQLException {
		
		try {
			Class.forName(DRIVER);
			connection = DriverManager.getConnection(DB_URL,USERNAME,PASSWORD); 
			statement = connection.prepareStatement(INSERT_CATEGORY);
			statement.setString(1, new_table);
			statement.executeUpdate();
			statement.close();
			
			String tempQuery = "";
			for(int i=0;i<num_attr;i++) {
				tempQuery = tempQuery + attributes.get(i) + " VARCHAR(30) NOT NULL,";
			}
			String query = "CREATE TABLE " + new_table +" ( id INT NOT NULL AUTO_INCREMENT ," + tempQuery+ " PRIMARY KEY (`id`))";
			statement = connection.prepareStatement(query);
			statement.execute();
			statement.close();
			connection.close();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return -1;
	}
	public String getName(int category_id) throws SQLException {
		String table_name = "";
		String query = "SELECT category_name FROM category WHERE category_id = "+Integer.toString(category_id);
		
		try {
			Class.forName(DRIVER);
			connection = DriverManager.getConnection(DB_URL,USERNAME,PASSWORD);
			statement = connection.prepareStatement(query);
			resultset = statement.executeQuery();
			while(resultset.next()) {
				table_name = resultset.getString("category_name");
			}
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return table_name;
		
	}
}
