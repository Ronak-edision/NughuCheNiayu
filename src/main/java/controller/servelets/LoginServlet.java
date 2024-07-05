/** Author: Ronak Krishna Shrestha
 * email: np01ai4s2300014@islingtoncollege.edu.np
 * Project Name: NughuCheNiyau
 * Islington College, KamalPokhari
 * LondonMet ID: 22085771
 * Section: AI-3 
 * */

package controller.servelets;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import controller.database.DBController;
import model.LoginModel;
import utils.StringUtils;

@WebServlet(urlPatterns = StringUtils.SERVLET_URL_LOGIN, asyncSupported = true)
public class LoginServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;
	private final DBController dbController;

	public LoginServlet() {
		this.dbController = new DBController();
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String userName = request.getParameter(StringUtils.USERNAME);
		String password = request.getParameter(StringUtils.PASSWORD);

		System.out.println("userName is " + userName);
		System.out.println("password is " + password);

		LoginModel loginModel = new LoginModel(userName, password);

		try {
			int loginResult = dbController.getStudentLoginInfo(loginModel);
			int loginID = dbController.getUserID(loginModel);
			System.out.println("the userID from loginServlet " + loginID);

			if (loginResult == 1) {
				HttpSession userSession = request.getSession(true);
				System.out.println("Session ID: " + userSession.getId());

				// Get user profile information
				List<String[]> userProfileList = dbController.getUserInfo(loginID);
				if (!userProfileList.isEmpty() && userProfileList.size() == 1) {
					String[] userProfile = userProfileList.get(0);
					String userRole = userProfile[1];
					String imageResult = dbController.getUserProfileInfo(loginID);

					// Set session attributes
					userSession.setAttribute(StringUtils.USERNAME, userName);
					userSession.setAttribute("image_str", imageResult);
					userSession.setAttribute("user_ID", loginID);
					userSession.setAttribute("role", userRole);
					userSession.setAttribute("password", password);


					// Set cookie
					Cookie userCookie = new Cookie(StringUtils.USER, userName);
					userCookie.setMaxAge(30 * 60); // Cookie expiry time in seconds
					response.addCookie(userCookie);

					// Redirect to home page after successful login
					response.sendRedirect(request.getContextPath() + StringUtils.URL_INDEX);
					return; // Stop further processing after redirection
				}
			}

			// Handle different login results with appropriate error messages
			String errorMessage = "";
			if (loginResult == 0) {
				errorMessage = "Invalid username or password. Please try again.";
			} else if (loginResult == -1) {
				errorMessage = "User not found. Please register an account.";
			} else {
				errorMessage = "Internal server error. Please try again later.";
			}

			// Set error message and forward back to login page
			request.setAttribute("errorMessage", errorMessage);
			request.setAttribute(StringUtils.USERNAME, userName);
			request.getRequestDispatcher(StringUtils.PAGE_URL_LOGIN).forward(request, response);

		} catch (Exception e) {
			// Handle any unexpected exceptions
			e.printStackTrace();
			request.setAttribute("errorMessage", "Internal server error. Please try again later.");
			request.getRequestDispatcher(StringUtils.PAGE_URL_LOGIN).forward(request, response);
		}
	}
}
