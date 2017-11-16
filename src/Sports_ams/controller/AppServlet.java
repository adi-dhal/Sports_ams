package Sports_ams.controller;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.simple.JSONObject;

import Sports_ams.dao.CategoryDao;
import Sports_ams.dao.Product_dao;
import Sports_ams.model.Product;

@WebServlet("/AppServlet")
public class AppServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
    public AppServlet() {
        super();
        // TODO Auto-generated constructor stub
    }
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	@SuppressWarnings("unchecked")
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		JSONObject json = new JSONObject();
		String type = request.getParameter("type");
		
		if(type.equals("populate")) {

			int category_id = Integer.valueOf(request.getParameter("category_id"));
			int product_id = Integer.valueOf(request.getParameter("product_id"));
			
			CategoryDao cdao = CategoryDao.getInstance();
			Product_dao pdao = Product_dao.getInstance();
			
			String table_name = "";
			try {
				table_name = cdao.getName(category_id);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			try {
				json = pdao.getTuple(table_name,product_id);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
		else if(type.equals("update")) {
			Product product = new Product();
			String table_name = request.getParameter("table_name");
			int count = Integer.valueOf(request.getParameter("count"));
			product.setId(Integer.valueOf(request.getParameter("attribute_val_1")));
			for(int i=2;i<=count;i++) {
				product.setAttribute_name(request.getParameter("attribute_name_"+Integer.toString(i)), i-1);
				product.setAttribute_val(request.getParameter("attribute_val_"+Integer.toString(i)), i-1);
			}
			Product_dao pdao = Product_dao.getInstance();
			try {
				pdao.updateTable(table_name,product,count);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			json.put("type", "success");
		}
		else if (type.equals("issue")) {
			Product_dao pdao = Product_dao.getInstance();
			String issuer = request.getParameter("issuer");
			String code = request.getParameter("code");
			try {
				pdao.issuer(issuer,code);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			};
		}
		response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        response.getWriter().write(json.toString());
		
		
	}
}
