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
import javax.servlet.http.HttpSession;

import model.UserModel;
import utils.StringUtils;
import controller.database.DBController;


/**
 * Servlet implementation class UserProfileServlet
 */
@WebServlet(asyncSupported = true, urlPatterns = { StringUtils.SERVLET_URL_USER_PROFILE })
public class UserProfileServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private final DBController controller = new DBController();

    public UserProfileServlet() {
        // Constructor
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    private void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            HttpSession userSession = request.getSession();
            int currentUserID = (Integer) userSession.getAttribute("user_ID");
            System.out.println("In the UserProfileServlet with user ID: " + currentUserID);

            ArrayList<UserModel> users = controller.getDBUserProfileInfo(currentUserID);

            for (UserModel user : users) {
                System.out.println("User ID: " + currentUserID);
                System.out.println("Full Name: " + user.getFullName());
                System.out.println("Username: " + user.getUserName());
                System.out.println("Email: " + user.getEmail());
                System.out.println("Address: " + user.getAddress());
                System.out.println("Password: " + user.getPassword());
                System.out.println("Nationality: " + user.getNationality());
                System.out.println("Date of Birth: " + user.getDoB());
                System.out.println("Gender: " + user.getGender());
                System.out.println("Role: " + user.getRole());
                System.out.println("Phone Number: " + user.getPhoneNumber());
                System.out.println("Image URL: " + user.getImageUrlFromPart());
                System.out.println("----------------------------------");
            }

            request.setAttribute(StringUtils.USER_PROFILE, users);
            request.getRequestDispatcher(StringUtils.PAGE_URL_USER_PROFILE).forward(request, response);
        } catch (Exception e) {
            e.printStackTrace();
            request.setAttribute("errorMessage", "An error occurred during processing.");
            request.getRequestDispatcher(StringUtils.SERVLET_URL_USER_PROFILE).forward(request, response);
        }
    }
}
