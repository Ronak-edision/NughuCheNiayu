package controller.servelets;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import controller.database.DBController;
import model.PropertyModel;
import utils.StringUtils;

/**
 * Servlet implementation class PropertyProfileServlet
 */
@WebServlet(asyncSupported = true, urlPatterns = { StringUtils.SERVLET_URL_PROPERTY_PROFILE })
public class PropertyProfileServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private final DBController controller = new DBController();

    /**
     * @see HttpServlet#HttpServlet()
     */
    public PropertyProfileServlet() {
        super();
    }

    /**
     * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
     */
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
     */
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Processes requests for both HTTP GET and POST methods.
     * 
     * @param request  servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException      if an I/O error occurs
     */
    private void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            HttpSession userSession = request.getSession();
            int currentUserID = (Integer) userSession.getAttribute("user_ID");

            System.out.println("Property profile servlet called with userID: " + currentUserID);

            ArrayList<PropertyModel> property = controller.getAllPropertyInfo();

            ArrayList<PropertyModel> filteredProperties = new ArrayList<>();

            for (PropertyModel prop : property) {
                if (prop.getSeller_id() == currentUserID) {
                    filteredProperties.add(prop);
                }
            }

            for (PropertyModel properties : filteredProperties) {
                // Access and print the price of each property
                System.out.println("Property Price: " + properties.getPrice());
            }

            request.setAttribute(StringUtils.PROPERTY_PROFILE, filteredProperties);
            request.getRequestDispatcher(StringUtils.PAGE_URL_PROPERTY_PROFILE).forward(request, response);
        } catch (Exception e) {
            e.printStackTrace();
            request.setAttribute("errorMessage", "An error occurred during processing.");
            request.getRequestDispatcher(StringUtils.SERVLET_URL_PROPERTY_PROFILE).forward(request, response);
        }
    }

}
