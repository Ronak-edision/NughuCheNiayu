/** Author: Ronak Krishna Shrestha
 * email: np01ai4s2300014@islingtoncollege.edu.np
 * Project Name: NughuCheNiyau
 * Islington College, KamalPokhari
 * LondonMet ID: 22085771
 * Section: AI-3 
 * */

package controller.servelets;

import java.io.IOException;
import utils.ValidationUtils;

import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import controller.database.DBController;
import utils.StringUtils;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.http.Part;
import model.PropertyModel;

/**
 * Servlet implementation class Property
 */
@WebServlet(asyncSupported = true, urlPatterns = { StringUtils.SERVLET_URL_PROPERTY })
@MultipartConfig(fileSizeThreshold = 1024 * 1024 * 2, maxFileSize = 1024 * 1024 * 10, // 10MB
		maxRequestSize = 1024 * 1024 * 50)

public class PropertyServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private final DBController dbController;

	public PropertyServlet() {
		this.dbController = new DBController();
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		System.out.println("in the add property servlet");
		int sellerID;
		int price;
		int propertySize;
		int noOfRoom;

		try {
			sellerID = Integer.parseInt(request.getParameter(StringUtils.SELLER_ID));
			price = Integer.parseInt(request.getParameter(StringUtils.PRICE));
			propertySize = Integer.parseInt(request.getParameter(StringUtils.PROPERTY_SIZE));
			noOfRoom = Integer.parseInt(request.getParameter(StringUtils.NO_OF_ROOM));
		} catch (NumberFormatException e) {
			// Handle invalid number format
			request.setAttribute(StringUtils.MESSAGE_ERROR, "Invalid number format in request parameters.");
			request.getRequestDispatcher(StringUtils.PAGE_URL_PROPERTY).forward(request, response);
			return;
		}

		String propertyBrief = request.getParameter(StringUtils.PROPERTY_BRIEF);
		String propertyDescription = request.getParameter(StringUtils.PROPERTY_DESCRIPTION);
		String propertyAddress = request.getParameter(StringUtils.PROPERTY_ADDRESS);
		Part propertyImagePart = request.getPart(StringUtils.PROPERTY_PHOTO);

		// Data Validation
		boolean isValid = true;
		StringBuilder errorMessage = new StringBuilder();

		// Additional validations
		if (!ValidationUtils.isTextOnly(propertyBrief)) {
			isValid = false;
			errorMessage.append("Property Brief should only contain alphabetic characters. ");
		}

		if (!ValidationUtils.isTextOnly(propertyDescription)) {
			isValid = false;
			errorMessage.append("Property Description should only contain alphabetic characters. ");
		}

		if (!ValidationUtils.hasNoSpecialCharacters(propertyAddress)) {
			isValid = false;
			errorMessage.append("Property Address should not contain special characters. ");
		}

		

		if (propertySize <= 0) {
			isValid = false;
			errorMessage.append("Property Size must be a positive integer. ");
		}

		if (noOfRoom <= 0) {
			isValid = false;
			errorMessage.append("Number of Rooms must be a positive integer. ");
		}

		if (!isValid) {
			System.out.println("in the invalid add property");
			request.setAttribute(StringUtils.MESSAGE_ERROR, errorMessage.toString());
			request.getRequestDispatcher(StringUtils.PAGE_URL_ADD_PROPERTY).forward(request, response);
			return;
		}
		System.out.println("outside the invalid");
		// Process valid data
		PropertyModel property = new PropertyModel(sellerID, propertyBrief, propertyDescription, propertyAddress, price,
				propertySize, noOfRoom, propertyImagePart);

		String fileName = property.getPropertyimageUrlFromPart();

		if (!ValidationUtils.isPhotoOnly(fileName)) {
			errorMessage.append("photo not valid. Photo should only be in jpg, jpeg or png!!! ");
			request.setAttribute("errorMessage", errorMessage.toString());
			request.getRequestDispatcher(StringUtils.PAGE_URL_ADD_PROPERTY).forward(request, response);
		} else {
			int result = dbController.addProperty(property);

			if (result == 1) {
				// Upload image in tomcat server
				String savePath = StringUtils.IMAGE_DIR_PROPERTY;
				fileName = property.getPropertyimageUrlFromPart();
				System.out.println("Image path is: " + savePath + fileName);

				if (!fileName.isEmpty() && fileName != null) {
					propertyImagePart.write(savePath + fileName);
					System.out.println("Image path inside if: " + savePath + fileName);
				}

				request.setAttribute(StringUtils.MESSAGE_SUCCESS, StringUtils.MESSAGE_SUCCESS_REGISTER);
				response.sendRedirect(request.getContextPath() + StringUtils.SERVLET_URL_HOME + "?success=true");
			} else {
				request.setAttribute(StringUtils.MESSAGE_ERROR, StringUtils.MESSAGE_ERROR_REGISTER);
				response.sendRedirect(request.getContextPath() + StringUtils.PAGE_URL_ADD_PROPERTY);
			}
		}
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		response.sendRedirect(request.getContextPath() + "/pages/addProperty.jsp");
	}

}
