package Sports_ams.Controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Sports_ams.data_access.Category_dao;
import Sports_ams.data_model.Category;
import org.json.simple.JSONObject;

@WebServlet("/Category_servlet")
public class Category_servlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
 
    public Category_servlet() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String type = request.getParameter("type");
		String table_name = request.getParameter("table_name");
		Category_dao cdao = Category_dao.getInstance();
		JSONObject json = new JSONObject();
		if (type == "create")
		{
			int no_attributes = Integer.valueOf(request.getParameter("no_attributes"));
			List<String> attributes = new ArrayList<String>();
			for(int cnt = 1;cnt <=no_attributes;cnt++)
			{
				String param = "attribute_name_" + Integer.toString(cnt);
				attributes.add(cnt-1,request.getParameter(param));
			}
			cdao.addCategpory(table_name, attributes, no_attributes);
			json.put("type","success");
		}
		else if (type == "enquiry")
		{
			List<Category> categories = new ArrayList<Category>();
			categories = cdao.getCategories();
	        json.put("type", "category");
	        json.put("no_category", Integer.toString(categories.size()));
	        for (int cnt = 0;cnt <categories.size();cnt++)
	        {
	        	json.put("category_"+Integer.toString(cnt+1),categories.get(cnt).getCategory_name());
	        }
		}
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        response.getWriter().write(json.toString());
	}

}
