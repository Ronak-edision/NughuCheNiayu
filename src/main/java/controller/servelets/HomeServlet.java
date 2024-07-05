/** Author: Ronak Krishna Shrestha
 * email: np01ai4s2300014@islingtoncollege.edu.np
 * Project Name: NughuCheNiyau
 * Islington College, KamalPokhari
 * LondonMet ID: 22085771
 * Section: AI-3 
 * */


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
 * Handles HTTP GET requests for the home page.
 * Retrieves property information from the database and forwards it to the index.jsp page.
 */
@WebServlet(asyncSupported = true, urlPatterns = { StringUtils.SERVLET_URL_HOME })
public class HomeServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	DBController controller = new DBController();

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("home view seevlet called");

	    request.setAttribute("source", "HomeServlet");

        // Retrieve all property information from the database
		ArrayList<PropertyModel> properties = controller.getAllPropertyInfo();
		System.out.println("proporty from db: "+properties.size());

		for (PropertyModel property : properties) {
            System.out.println("Property ID: " + property.getSeller_id());
            System.out.println("Brief: " + property.getProperty_brief());
            System.out.println("Description: " + property.getProperty_description());
            System.out.println("Address: " + property.getProperty_address());
            System.out.println("Price: " + property.getPrice());
            System.out.println("Size: " + property.getProperty_size());
            System.out.println("Number of Rooms: " + property.getNo_of_room());
            System.out.println("Image URL: " + property.getPropertyimageUrlFromPart());
        }
        // Set the list of properties as a request attribute for rendering in the JSP
		request.setAttribute(StringUtils.PROPERTY_LISTS, properties);
        // Forward the request to the index.jsp page
		request.getRequestDispatcher(StringUtils.URL_INDEX).forward(request, response);
		}
}