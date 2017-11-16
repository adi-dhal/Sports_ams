package Sports_ams.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import org.json.simple.JSONObject;

import Sports_ams.model.Product;

public class Product_dao {

	public static Product_dao instance = null;
	private Product_dao() {}
	public static Product_dao getInstance() {
		
		if(instance == null) {
			instance = new Product_dao();
		}
		return instance;
	}
	
	private static String DRIVER = "com.mysql.jdbc.Driver";
	private static String DB_URL = "jdbc:mysql://localhost/DBMS";
	private static String USERNAME = "root";
	private static String PASSWORD = "root";
	
	private Connection connection = null;
	private PreparedStatement statement = null;
	private ResultSet resultset = null;
	
	@SuppressWarnings("unchecked")
	public JSONObject viewTable(String table_name) throws SQLException{
		String cquery = "Select count(*) as num from "+table_name;
		String query = "SELECT * FROM "+table_name;
		JSONObject json = new JSONObject();
		int no_tuples = 0;
		try {
			Class.forName(DRIVER);
			connection = DriverManager.getConnection(DB_URL,USERNAME,PASSWORD);
			statement = connection.prepareStatement(cquery);
			resultset = statement.executeQuery();
			while(resultset.next()) {
				no_tuples = resultset.getInt("num");
			}
			json.put("count",Integer.toString(no_tuples) );
			resultset.close();
			statement.close();
			
			ArrayList<String> attributes = new ArrayList<String>();
			attributes = getAttributes(table_name);
			json.put("attr_count", Integer.toString(attributes.size()));
			for (int cnt = 0;cnt <attributes.size();cnt++){
				json.put("attributes_"+Integer.toString(cnt+1),attributes.get(cnt));
		    }			
			
			statement = connection.prepareStatement(query);
			resultset = statement.executeQuery();
			int count = 0;
			while(resultset.next()) {
				String tupleString = "";
				for(int i = 0;i<attributes.size();i++) {
					tupleString += resultset.getString(attributes.get(i)) + "-";
				}
				count++;
				json.put("row_"+Integer.toString(count), tupleString);
			}
			resultset.close();
			statement.close();
			connection.close();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return json;
		
	}
	
	public int insertProduct(String tableName,Product product,int numAtt) throws SQLException {
		
		String query = "INSERT INTO " + tableName + " (`";
		String tempQuery1= product.getAttribute_name_1();
		String tempQuery2= "VALUES (" +product.getAttribute_value_1();
		for(int i=2;i<=numAtt;i++) {
			tempQuery1 = tempQuery1 + "`,`" + product.getAttributeName(i);			
		}
		tempQuery1 += "`) ";
		for(int i=2;i<=numAtt;i++) {
			tempQuery2 = tempQuery2 + ","  + product.getAttributeValue(i) ;
		}
		tempQuery2 += ") ";
		query = query + tempQuery1 + tempQuery2;
		try {
			Class.forName(DRIVER);
			connection = DriverManager.getConnection(DB_URL,USERNAME,PASSWORD);
			statement = connection.prepareStatement(query);
			statement.executeUpdate();
			statement.close();
			connection.close();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}		
		return -1;
	}
	public ArrayList<String> getAttributes(String table_name)
	{
		ArrayList<String> attributes = new ArrayList<String>();
		try {
			Class.forName(DRIVER);
			connection = DriverManager.getConnection(DB_URL,USERNAME,PASSWORD);
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
	@SuppressWarnings("unchecked")
	public JSONObject getTuple(String table_name, int product_id) throws SQLException {
		String query = "SELECT * FROM "+table_name+" WHERE id = "+Integer.toString(product_id); 
		JSONObject json = new JSONObject();
		ArrayList<String> attributes = new ArrayList<String>();
		attributes = getAttributes(table_name); 
		json.put("count", Integer.toString(attributes.size()));
		json.put("category", table_name);
		for (int cnt = 0;cnt <attributes.size();cnt++){
			json.put("attribute_name_"+Integer.toString(cnt+1),attributes.get(cnt));
	    }
		
		try {
			Class.forName(DRIVER);
			connection = DriverManager.getConnection(DB_URL,USERNAME,PASSWORD);
			statement = connection.prepareStatement(query);
			resultset = statement.executeQuery();
			while(resultset.next()) {
				for(int i=0;i<attributes.size();i++) {
					json.put("attribute_val_"+Integer.toString(i+1), resultset.getString(attributes.get(i)));
				}
			}
			
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return json;
	}
	public void updateTable(String table_name, Product product,int count) throws SQLException {
		String query = "UPDATE "+table_name+" SET ";
		String temp = "";
		for(int i=1;i<count;i++) {
			temp += "`"+product.getAttributeName(i)+"`="+"\""+product.getAttributeValue(i)+"\""+",";
		}
		temp = temp.substring(0, temp.length()-1);
		query += temp + " WHERE id="+Integer.toString(product.getId());
		
		try {
			Class.forName(DRIVER);
			connection = DriverManager.getConnection(DB_URL,USERNAME,PASSWORD);
			statement = connection.prepareStatement(query);
			statement.executeUpdate();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	public void deleteEntry(String table_name, int id) throws SQLException {
		String query = "DELETE FROM "+table_name+" WHERE id = "+Integer.toString(id);
		try {
			Class.forName(DRIVER);
			connection = DriverManager.getConnection(DB_URL,USERNAME,PASSWORD);
			statement = connection.prepareStatement(query);
			statement.executeUpdate();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	public void issuer(String issuer,String code) throws SQLException {
		String query = "INSERT INTO issue (`issuer`,`code`) VALUES (?,?)";
		
		try {
			Class.forName(DRIVER);
			connection = DriverManager.getConnection(DB_URL,USERNAME,PASSWORD); 
			statement = connection.prepareStatement(query);
			statement.setString(1, issuer);
			statement.setString(2, code);
			statement.executeUpdate();
			statement.close();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
}
