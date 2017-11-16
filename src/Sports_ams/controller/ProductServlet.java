package Sports_ams.controller;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.simple.JSONObject;

import Sports_ams.dao.Product_dao;
import Sports_ams.model.Product;
/**
 * Servlet implementation class ProductServlet
 */
@WebServlet("/ProductServlet")
public class ProductServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ProductServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	@SuppressWarnings("unchecked")
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String type = request.getParameter("type");
		String table_name = request.getParameter("table_name");
		Product_dao pdao = Product_dao.getInstance();
		JSONObject json = new JSONObject();
		Product product = new Product();
		if (type.equals("insert"))
		{
			int no_attributes = Integer.valueOf(request.getParameter("no_attributes"));
			for(int cnt = 1;cnt <=no_attributes;cnt++)
			{
				String param_name = "attribute_name_" + Integer.toString(cnt);
				String param_val = "attribute_val_" + Integer.toString(cnt);
				product.setAttribute_name(request.getParameter(param_name), cnt);
				product.setAttribute_val(request.getParameter(param_val), cnt);
			}
			try {
				pdao.insertProduct(table_name, product, no_attributes);
			} catch (SQLException e) {
				e.printStackTrace();
			}
			json.put("type","success");
		}
		else if (type.equals("enquiry"))
		{
			try {
				json = pdao.viewTable(table_name);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
		else if (type.equals("describe"))
		{
			List<String> attributes = new ArrayList<String>();
			attributes = pdao.getAttributes(table_name);
	        json.put("type", "attributes");
	        json.put("no_attributes", Integer.toString(attributes.size()));
	        for (int cnt = 0;cnt <attributes.size();cnt++)
	        {
	        	json.put("attributes_"+Integer.toString(cnt+1),attributes.get(cnt));
	        }

		}
		else if (type.equals("delete")) {
			int id = Integer.valueOf(request.getParameter("id"));
			try {
				pdao.deleteEntry(table_name,id);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        response.getWriter().write(json.toString());
	}

}
