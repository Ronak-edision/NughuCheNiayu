package controller.servelets;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import controller.database.DBController;
import model.PropertyModel;
import utils.StringUtils;

/**
 * Servlet implementation class HomeServlet
 */
@WebServlet(asyncSupported = true, urlPatterns = { StringUtils.SERVLET_URL_PROPERTY_DISPLAY })
public class PropertyDisplayServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	DBController controller = new DBController();

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("proporty view seevlet called");

		ArrayList<PropertyModel> property = controller.getAllPropertyInfo();
		System.out.println("proporty from db: "+property.size());

		request.setAttribute(StringUtils.PROPERTY_LISTS, property);
		request.getRequestDispatcher(StringUtils.PAGE_URL_PROPERTY).forward(request, response);
		
	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	    System.out.println("search on servlet");
		String searchTerm = request.getParameter("searchTerm");
	    System.out.println("search on servlet seacrh Term is: "+searchTerm);

		ArrayList<PropertyModel> property = controller.getSearchedPropertyInfo(searchTerm);
		request.setAttribute(StringUtils.PROPERTY_LISTS, property);
		request.getRequestDispatcher(StringUtils.PAGE_URL_PROPERTY).forward(request, response);
	
	/*
	 if (propertyList.isEmpty()) {
        // Set an error message if no properties are found
        request.setAttribute("errorMessage", "No properties found for the search term: " + searchTerm);
    } else {
        // Set the property list if properties are found
        request.setAttribute(StringUtils.PROPERTY_LISTS, propertyList);
    }

    // Forward to the appropriate JSP page
    request.getRequestDispatcher(StringUtils.PAGE_URL_PROPERTY).forward(request, response);*/
	
	}
	
}