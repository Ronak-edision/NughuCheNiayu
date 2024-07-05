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

/**
 * Servlet implementation class ModifyServlet
 */
@MultipartConfig(fileSizeThreshold = 1024 * 1024 * 2, // 2MB
		maxFileSize = 1024 * 1024 * 10, // 10MB
		maxRequestSize = 1024 * 1024 * 50)
@WebServlet(asyncSupported = true, urlPatterns = StringUtils.SERVLET_URL_MODIFY_PROPERTY)
public class ModifyPropertyServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private final DBController dbController;

	public ModifyPropertyServlet() {
		this.dbController = new DBController();
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		System.out.println("in the modify property");
		String updateId = request.getParameter(StringUtils.UPDATE_ID);
		System.out.println("in the modify property update is: " + updateId);

		String deleteId = request.getParameter(StringUtils.DELETE_ID);

		if (updateId != null && !updateId.isEmpty()) {
			doPut(request, response);
		}
		if (deleteId != null && !deleteId.isEmpty()) {
			doDelete(request, response);
		}

	}

	@Override
	protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		System.out.println("put property triggered");

		try {
			// Retrieve updated property information from the request parameters
			int sellerID = Integer.parseInt(req.getParameter("propertyId"));
			String propertyBrief = req.getParameter("property_brief");
			String propertyDescription = req.getParameter("property_description");
			int price = Integer.parseInt(req.getParameter("price"));
			int no_of_room = Integer.parseInt(req.getParameter("no_of_room"));

			String propertyAddress = req.getParameter("property_address");
			int propertySize = Integer.parseInt(req.getParameter("property_size"));
			String imageURL = req.getParameter("imageURL");

			Part imagePart = req.getPart("property_photo");
			// Perform any necessary data validation or processing here
			UserModel user = new UserModel();

			// Additional validations
			boolean isValid = true;
			StringBuilder errorMessage = new StringBuilder();

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

			if (price <= 0) {
				isValid = false;
				errorMessage.append("Price must be a positive integer. ");
			}

			if (propertySize <= 0) {
				isValid = false;
				errorMessage.append("Property Size must be a positive integer. ");
			}

			if (no_of_room <= 0) {
				isValid = false;
				errorMessage.append("Number of Rooms must be a positive integer. ");
			}

			if (!isValid) {
				// Set error message and forward back to add property page
				req.setAttribute(StringUtils.MESSAGE_ERROR, errorMessage.toString());
				req.getRequestDispatcher(StringUtils.SERVLET_URL_PROPERTY_PROFILE).forward(req, resp);
				return;
			}
			if (imagePart == null) {
				// Update the property information in the database
				int updateResult = dbController.updatePropertyInfo(sellerID, propertyBrief, propertyDescription, price,
						propertyAddress, propertySize, imageURL, no_of_room);
			
				if (updateResult == 1) {
					// If update successful, set success message and redirect
					req.setAttribute(StringUtils.MESSAGE_SUCCESS, "Property updated successfully");
					resp.sendRedirect(req.getContextPath() + StringUtils.URL_INDEX); // Redirect to property profile page
				} else {
					// If update failed, set error message and redirect
					req.setAttribute(StringUtils.MESSAGE_ERROR, "Failed to update property");
					resp.sendRedirect(req.getContextPath() + StringUtils.SERVLET_URL_PROPERTY_PROFILE); // Redirect to
																										// property profile
																										// page
				}
			} else {
				
				String stringImageUpdate = user.getImageUrl(imagePart);
				
				int updateResult = dbController.updatePropertyInfo(sellerID, propertyBrief, propertyDescription, price,
						propertyAddress, propertySize, stringImageUpdate, no_of_room);
				if (updateResult == 1) 
				{
					
					String savePath = StringUtils.IMAGE_DIR_PROPERTY;

					// If update successful, set success message and redirect
			    	imagePart.write(savePath + stringImageUpdate);
		    		System.out.println("image paths inside if "+savePath + stringImageUpdate);	
					
					req.setAttribute(StringUtils.MESSAGE_SUCCESS, "Property updated successfully");
					resp.sendRedirect(req.getContextPath() + StringUtils.URL_INDEX); // Redirect to property profile page
				} else {
					// If update failed, set error message and redirect
					req.setAttribute(StringUtils.MESSAGE_ERROR, "Failed to update property");
					resp.sendRedirect(req.getContextPath() + StringUtils.SERVLET_URL_PROPERTY_PROFILE); // Redirect to
																										// property profile
																										// page
				}
			}
			
		} catch (Exception e) {
			e.printStackTrace();
			handleException(req, resp, "An error occurred during property update.");
		}
	}

	@Override
	protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		System.out.println("delete triggered");
		if (dbController.deletePropertyInfo(req.getParameter(StringUtils.DELETE_ID)) == 1) {
			req.setAttribute(StringUtils.MESSAGE_SUCCESS, StringUtils.MESSAGE_SUCCESS_DELETE);
			resp.sendRedirect(req.getContextPath() + StringUtils.URL_INDEX);
		} else {
			req.setAttribute(StringUtils.MESSAGE_ERROR, StringUtils.MESSAGE_ERROR_DELETE);
			resp.sendRedirect(req.getContextPath() + StringUtils.URL_INDEX);
		}
	}

	private void handleException(HttpServletRequest request, HttpServletResponse response, String errorMessage)
			throws ServletException, IOException {
		request.setAttribute(StringUtils.MESSAGE_ERROR, errorMessage);
		request.getRequestDispatcher(StringUtils.SERVLET_URL_PROPERTY_PROFILE).forward(request, response);
	}
}