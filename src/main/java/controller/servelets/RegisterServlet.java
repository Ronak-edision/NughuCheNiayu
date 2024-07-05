/** Author: Ronak Krishna Shrestha
 * email: np01ai4s2300014@islingtoncollege.edu.np
 * Project Name: NughuCheNiyau
 * Islington College, KamalPokhari
 * LondonMet ID: 22085771
 * Section: AI-3 
 * */
package controller.servelets;

import java.io.IOException;
import java.time.LocalDate;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import controller.database.DBController;
import model.UserModel;
import utils.StringUtils;
import utils.ValidationUtils;

@WebServlet(asyncSupported = true, urlPatterns = { StringUtils.SERVLET_URL_REGISTER })
@MultipartConfig(fileSizeThreshold = 1024 * 1024 * 2, //
		maxFileSize = 1024 * 1024 * 10, // 10MB
		maxRequestSize = 1024 * 1024 * 50)
public class RegisterServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private final DBController dbController;

	public RegisterServlet() {
		this.dbController = new DBController();
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		System.out.println("in the register servlet");
		String fullName = request.getParameter(StringUtils.USERNAME);
		LocalDate dob = LocalDate.parse(request.getParameter(StringUtils.DoB));
		String gender = request.getParameter(StringUtils.GENDER);
		String email = request.getParameter(StringUtils.EMAIL);
		String address = request.getParameter(StringUtils.ADDRESS);
		String phoneNumber = request.getParameter(StringUtils.PHONE_NUMBER);
		String nationality = request.getParameter(StringUtils.Nationality);
		String username = request.getParameter(StringUtils.USERNAME);
		String password = request.getParameter(StringUtils.PASSWORD);
		String retypePassword = request.getParameter(StringUtils.RETYPE_PASSWORD);
		String role = request.getParameter(StringUtils.ROLE);
		Part imagePart = request.getPart("image");

		// Data Validation
		boolean isValid = true;
		StringBuilder errorMessage = new StringBuilder();

		// Validate password match
		if (!password.equals(retypePassword)) {
			isValid = false;
			errorMessage.append("Password and Retype Password do not match. ");
		}

		// Additional validations
		if (!ValidationUtils.isTextOnly(address)) {
			isValid = false;
			errorMessage.append("Address should only contain alphabetic characters. ");
		}

		if (!ValidationUtils.isTextOnly(nationality)) {
			isValid = false;
			errorMessage.append("Nationality should only contain alphabetic characters. ");
		}

		if (!ValidationUtils.isAlphanumeric(username)) {
			isValid = false;
			errorMessage.append("Username should only contain alphanumeric characters. ");
		}

		if (!ValidationUtils.isEmail(email)) {
			isValid = false;
			errorMessage.append("Invalid email format. ");
			System.out.println("invalid email");
		}

		if (!ValidationUtils.isNumbersOnly(phoneNumber)) {
			isValid = false;
			errorMessage.append("Phone number should only contain numbers. ");
		}
		if (!ValidationUtils.isNull(role)) {
			isValid = false;
			errorMessage.append("role can not be empty ");
		}

		// Check if username or email already exist
		if (dbController.checkUsernameIfExists(username)) {
			isValid = false;
			errorMessage.append("Username already exists. ");
		}

		if (dbController.checkEmailIfExists(email)) {
			isValid = false;
			errorMessage.append("Email already exists. ");
		}

		if (isValid) {
			System.out.println("is valid regsitration");
			UserModel user = new UserModel(fullName, username, email, address, password, retypePassword, nationality,
					dob, gender, role, phoneNumber, imagePart);
			String fileName = user.getImageUrlFromPart();

			if (!ValidationUtils.isPhotoOnly(fileName)) {
				errorMessage.append("photo not valid. Photo should only be in jpg, jpeg or png!!! ");
				request.setAttribute("errorMessage", errorMessage.toString());
				request.getRequestDispatcher(StringUtils.PAGE_URL_REGISTER).forward(request, response);
			} else {
				System.out.println(" regsitration sent in db");

				int result = dbController.registerUser(user);

				if (result == 1) {
					// Upload image (if provided) to server
					System.out.println(" in regsitration result 1 ");

					if (imagePart != null) {
						String savePath = StringUtils.IMAGE_DIR_USER;
						fileName = user.getImageUrlFromPart();
						System.out.println(" in regsitration image not null  ");

						if (!ValidationUtils.isPhotoOnly(fileName)) {
							errorMessage.append("photo not valid. Photo should only be in jpg, jpeg or png!!! ");
							request.setAttribute("errorMessage", errorMessage.toString());
							request.getRequestDispatcher(StringUtils.PAGE_URL_REGISTER).forward(request, response);
						}
						imagePart.write(savePath + fileName);

					}
					request.setAttribute(StringUtils.MESSAGE_SUCCESS, "Registration successful!");
					response.sendRedirect(request.getContextPath() + StringUtils.PAGE_URL_LOGIN + "?success=true");
					return; // Response committed with redirect, exit method
				} else {
					errorMessage.append("Registration failed. Please try again. ");
				}
			}
		} else {
			System.out.println("not valid registration");
			// Set error message in request attribute
			request.setAttribute("errorMessage", errorMessage.toString());
			request.getRequestDispatcher(StringUtils.PAGE_URL_REGISTER).forward(request, response);
		}
	}
}
