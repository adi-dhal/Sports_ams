package Sports_ams.controller;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.simple.JSONObject;

import Sports_ams.dao.CategoryDao;
import Sports_ams.model.Category;

/**
 * Servlet implementation class CategoryServlet
 */
@WebServlet("/CategoryServlet")
public class CategoryServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CategoryServlet() {
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
		CategoryDao cdao = CategoryDao.getInstance();
		JSONObject json = new JSONObject();
		
		if (type.equals("create"))
		{
			int no_attributes = Integer.valueOf(request.getParameter("no_attributes"));
			ArrayList<String> attributes = new ArrayList<String>();
			for(int cnt = 1;cnt <=no_attributes;cnt++)
			{
				String param = "attribute_name_" + Integer.toString(cnt);
				attributes.add(cnt-1,request.getParameter(param));
			}
			try {
				cdao.createCategory(table_name, attributes, no_attributes);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			json.put("type","success");
		}
		else if (type.equals("enquiry"))
		{
			ArrayList<Category> categories = new ArrayList<Category>();
			try {
				categories = cdao.findAllCategories();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
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
