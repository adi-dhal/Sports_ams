package Sports_ams.Controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.simple.JSONObject;

import Sports_ams.data_access.Category_dao;
import Sports_ams.data_access.Product_dao;
import Sports_ams.data_model.Category;
import Sports_ams.data_model.Product;

@WebServlet("/Product_servlet")
public class Product_servlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
   
    public Product_servlet() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String type = request.getParameter("type");
		String table_name = request.getParameter("table_name");
		Product_dao pdao = Product_dao.getInstance();
		JSONObject json = new JSONObject();
		Product product = new Product();
		if (type == "insert")
		{
			int no_attributes = Integer.valueOf(request.getParameter("no_attributes"));
			for(int cnt = 1;cnt <=no_attributes;cnt++)
			{
				String param_name = "attribute_name_" + Integer.toString(cnt);
				String param_val = "attribute_val_" + Integer.toString(cnt);
				product.setAttribute_name(request.getParameter(param_name), cnt);
				product.setAttribute_val(request.getParameter(param_val), cnt);
			}
			pdao.insertProduct(table_name, product, no_attributes);
			json.put("type","success");
		}
		else if (type == "view")
		{
			
		}
		else if (type == "enquiry")
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
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        response.getWriter().write(json.toString());
	}

}
