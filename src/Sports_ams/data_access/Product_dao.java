package Sports_ams.data_access;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import Sports_ams.data_model.Category;
import Sports_ams.data_model.Product;

public class Product_dao {
	
	private static String DRIVER = "com.mysql.jdbc.Driver";
	private static String DB_URL = "jdbc:mysql://localhost/dbms_project_ams";
	private static String Username = "root";
	private static String Password = "mysql";
	private Connection connection =null;
	private PreparedStatement statement = null;
	private ResultSet resultset = null;
	
	
	 static Product_dao instance = null;
	
	private Product_dao() {}
	
	public static Product_dao getInstance()
	{
		if (instance == null)
		{
			instance = new Product_dao();
		}
		return instance;
	}
	public void insertProduct(String table_name,Product product,int no_attribute) {
		try {
			Class.forName(DRIVER);
			connection = DriverManager.getConnection(DB_URL,Username,Password);
			String query = "INSERT INTO "+table_name;
			String str_name= "("+product.getAttribute_name(1);
			String str_val ="("+"'"+product.getAttribute_val(1)+"'";
			for (int i = 2;i <= no_attribute;i++)
			{
				str_name = str_name +","+product.getAttribute_name(i);
				str_val = str_val + ","+"'"+product.getAttribute_val(i)+"'";
			}
			query = query +str_name+")"+" VALUES"+str_val+")";
			statement = connection.prepareStatement(query);
			statement.executeUpdate();
			statement.close();
			connection.close();
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
		 
	}
	public List<Product> getView(String table_name)
	{
		List<Product> view = new ArrayList<Product>();
		List<String> attributes = new ArrayList<String>();
		Product_dao pdao = Product_dao.getInstance();
		attributes = pdao.getAttributes(table_name);
		
		return view;
	}
	public List<String> getAttributes(String table_name)
	{
		List<String> attributes = new ArrayList();
		try {
			Class.forName(DRIVER);
			connection = DriverManager.getConnection(DB_URL,Username,Password);
			String query = "DESCRIBE "+table_name;
			statement = connection.prepareStatement(query);
			resultset = statement.executeQuery();
			int cnt = 0;
			while(resultset.next())
			{
				String attribute_name = resultset.getString("Field");
				attributes.add(cnt,attribute_name);
				cnt ++;
			}

			}catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
		
		return attributes;
	}

}
