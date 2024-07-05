/** Author: Ronak Krishna Shrestha
 * email: np01ai4s2300014@islingtoncollege.edu.np
 * Project Name: NughuCheNiyau
 * Islington College, KamalPokhari
 * LondonMet ID: 22085771
 * Section: AI-3 
 * */


package controller.servelets;

import java.io.IOException;

import model.PasswordEncryptionWithAes;
import model.UserModel;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.http.Part;

import controller.database.DBController;
import model.PasswordEncryptionWithAes;
import utils.StringUtils;
import utils.ValidationUtils;
import model.UserModel;

/**
 * Servlet implementation class ModifyServlet
 */
@WebServlet(asyncSupported = true, urlPatterns = StringUtils.SERVLET_URL_MODIFY_USER)
@MultipartConfig(fileSizeThreshold = 1024 * 1024 * 2, // 2MB
maxFileSize = 1024 * 1024 * 10, // 10MB
maxRequestSize = 1024 * 1024 * 50)
public class ModifyUserServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private final DBController dbController;
    StringBuilder errorMessage = new StringBuilder();

	public ModifyUserServlet() {
		this.dbController = new DBController();

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		System.out.println("in the modify user  update inside");
		/*
		 * String url=request.getParameter("update");
		 *  System.out.println(url);
		 */
         
		String updateId = request.getParameter("updateId");
		String deleteId = request.getParameter("deleteId");
		String imagerID = request.getParameter("ImageUpdateUser");

		System.out.println("in the modify user update_id is: " + updateId);
		System.out.println("in the modify user delete_id is: " + deleteId);
		System.out.println("in the modify user image_id is: " + imagerID);

		String imageURL = request.getParameter("imageURL");
		System.out.println("in modify User image in post is: " + imageURL);

		String role = request.getParameter("Role");
		System.out.println("in modify User role in post is: " + role);

		String address = request.getParameter("address");
		System.out.println("in modify User address in post is: " + address);

		
		
		/*
		 * Part imagePart = request.getPart("image");
		 * System.out.println("the image part maybe "+ imagePart);
		 */
		String nat= request.getParameter("nationality");
		System.out.println("the nationality from image update "+ nat);

		
		if (updateId != null && !updateId.isEmpty()) {
			doPut(request, response);
		}
		if (deleteId != null && !deleteId.isEmpty()) {
			doDelete(request, response);
			System.out.println("in the modify user delete");

		}

		/*
		 * if (imagerID != null && !imagerID.isEmpty()) { doPut(request, response); }
		 */
		
		  if (imagerID != null && !imagerID.isEmpty()) 
		  { 
			  
			  doPut(request, response); }
		 
	}

	@Override
	protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		System.out.println("put triggered");

		// Retrieve updated user information from the request parameters
		try {
		String userName = req.getParameter("userName");
		String email = req.getParameter("email");
		String address = req.getParameter("address");
		String phoneNumber = req.getParameter("phoneNumber");
		String nationality = req.getParameter("nationality");
		String gender = req.getParameter("gender");
		String dob = req.getParameter("DoB");

		String oldPassword = req.getParameter("oldPassword");
		String newPassword = req.getParameter("newPassword");
		String currentPassword = req.getParameter("password");
		
		String role = req.getParameter("Role");
		String imageURL = req.getParameter("imageURL");
		
		
		String imageUpdateUser = req.getParameter("ImageUpdateUser");
		String imageUpdate = req.getParameter("ImageUpdate");
		
	    UserModel user = new UserModel();

		
		
		
		System.out.println("imageUpdate is: " + imageUpdate);


		System.out.println("old password: " + oldPassword);
		System.out.println("new password: " + newPassword);
		System.out.println("current password: " + currentPassword);

		// Determine which condition triggered the doPut
		if (req.getParameter("updateId") != null) {
			// This is the updateId condition
			System.out.println("Update triggered");

			// Validate conditions before updating

			updateUserInfoServer(req, resp, userName, email, address, phoneNumber, nationality, gender, dob, oldPassword,
					newPassword, currentPassword, role, imageURL);

		} else if (req.getParameter("ImageUpdateUser") != null) {
			// This is the imagerID condition
			
			
			try {
			    Part imagePart = req.getPart("image");

			    // Check if imagePart is null
			    if (imagePart == null) {
			        System.out.println("Image part is null. Handle this case gracefully.");
			        // Handle the case where imagePart is null (e.g., set default image)
			        // For example:
			        String defaultImageUrl = "default.jpg";
			        updatePhotoInfo(req, resp, imageUpdateUser, defaultImageUrl, null);
			    } else {
			        String stringImageUpdate = user.getImageUrl(imagePart);

			        System.out.println("after part is assigned: " + imagePart);
			        String upDBImage = UserModel.getImageUrl(imagePart);

			        System.out.println("Image update triggered");
			        System.out.println("UserName: " + imageUpdateUser);
			        System.out.println("Image URL: " + upDBImage);

			        updatePhotoInfo(req, resp, imageUpdateUser, upDBImage, stringImageUpdate);
			    }
			} catch (Exception e) {
			    System.out.println("Error processing image update: " + e.getMessage());
			    // Handle any exceptions that occur during image processing

		}}}
		catch (Exception e) {
            e.printStackTrace();
            req.setAttribute("errorMessage", "An error occurred during user modification.");
            req.getRequestDispatcher(StringUtils.SERVLET_URL_USER_PROFILE).forward(req, resp);
        }
	}

	private void updateUserInfoServer(HttpServletRequest req, HttpServletResponse resp, String userName, String email,
			String address, String phoneNumber, String nationality, String gender, String dob, String oldPassword,
			String newPassword, String currentPassword, String role, String imageURL) throws IOException, ServletException {
		Boolean myCondition = true;

		String usePassword;

		boolean PasswordChoice;
		if (newPassword == null || newPassword.isEmpty()) {
			PasswordChoice=true;
			usePassword = currentPassword;
			System.out.println("old password going in database: " + usePassword);

		} else {
			PasswordChoice=false;
			usePassword = newPassword;
			System.out.println("new password going in database: " + usePassword);

		}

		if (!(newPassword == null) && !(newPassword.isEmpty()) && (!oldPassword.equals(currentPassword))) {
			myCondition = false;
			
			System.out.println("in bad condition");
            errorMessage.append("the old password is wrong ");

		}
		
		// Additional validations
        if (!ValidationUtils.isTextOnly(address)) {
        	myCondition = false;
            errorMessage.append("Address should only contain alphabetic characters. ");
        }

        if (!ValidationUtils.isTextOnly(nationality)) {
        	myCondition = false;
            errorMessage.append("Nationality should only contain alphabetic characters. ");
        }

        if (!ValidationUtils.isTextOnly(userName)) {
        	myCondition = false;
            errorMessage.append("Username should only contain atext only. ");
        }

        if (!ValidationUtils.isEmail(email)) {
        	myCondition = false;
            errorMessage.append("Invalid email format. ");
        }

        if (!ValidationUtils.isNumbersOnly(phoneNumber)) {
        	myCondition = false;
            errorMessage.append("Phone number should only contain numbers. ");
        }

        

        
		 
		if (myCondition == true) {
			String Passwordforupdate;
			int updateResult;
			if (PasswordChoice==false)
			{
			System.out.println("the password for encrption in true mycondition is: "+usePassword);
			 Passwordforupdate = PasswordEncryptionWithAes.encrypt(userName, usePassword);
				System.out.println("the username encryption of the  passowrd in update:"+userName);

				System.out.println("the encryption of the  passowrd :"+Passwordforupdate);

				String decryptedPwd = PasswordEncryptionWithAes.decrypt(Passwordforupdate, userName);
				System.out.println("the decryption of the encryptyed passowrd :"+decryptedPwd);
			 
			 updateResult = dbController.updateUserInfo(userName, email, address, phoneNumber, nationality, gender, dob,
						Passwordforupdate, role, imageURL);
			}
			else {
				System.out.println("the no update passoword: "+usePassword);

				Passwordforupdate =  usePassword;
				 updateResult = dbController.updateUserNoPInfo(userName, email, address, phoneNumber, nationality, gender, dob,
							 role, imageURL);
			}
			
			

			
			if (updateResult == 1) {
				System.out.println("in the sucessful update before redirection");
				// If update successful, set success message and redirect
				
				// req.setAttribute(StringUtils.MESSAGE_SUCCESS,StringUtils.MESSAGE_SUCCESS_UPDATE); 
				 



				req.setAttribute(StringUtils.MESSAGE_SUCCESS, StringUtils.MESSAGE_SUCCESS_UPDATE);
		        try {
					req.getRequestDispatcher(StringUtils.URL_INDEX).forward(req, resp);
				} catch (ServletException | IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

			}
			else {
				// If update failed, set error message and redirect
				/*
				 * req.setAttribute(StringUtils.MESSAGE_ERROR,
				 * StringUtils.MESSAGE_ERROR_UPDATE); resp.sendRedirect(req.getContextPath() +
				 * StringUtils.URL_INDEX);
				 */
		        req.setAttribute(StringUtils.MESSAGE_ERROR, StringUtils.MESSAGE_ERROR_UPDATE);

			}
	       // resp.sendRedirect(req.getContextPath() + StringUtils.URL_INDEX);

		}
		System.out.println("in the error write part");
		req.setAttribute("errorMessage", errorMessage.toString());
        req.getRequestDispatcher(StringUtils.SERVLET_URL_USER_PROFILE).forward(req, resp);
	}

	private void updatePhotoInfo(HttpServletRequest req, HttpServletResponse resp, String userName, String imageUpdate , String stringimageurl)
			throws IOException {

		// Update user info in the database
	    UserModel user = new UserModel();

		System.out.println("in the update info method");

	    
	    Part imagePart;
		try {
			imagePart = req.getPart("image");
			System.out.println("in the update info method in try catch");
			int updateResult = dbController.UserphotoUpdate(stringimageurl, userName);

			if (updateResult == 1) {
				// If update successful, set success message and redirect
				String savePath = StringUtils.IMAGE_DIR_USER;
				/* String fileName = user.getImageUrlFromPart(); */
	    		System.out.println("image paths is"+savePath + stringimageurl);			

			    if(!stringimageurl.isEmpty() && stringimageurl != null && ValidationUtils.isPhotoOnly(stringimageurl)) {
		    		System.out.println("image paths inside if before path"+savePath + stringimageurl);			

			    	imagePart.write(savePath + stringimageurl);
		    		System.out.println("image paths inside if "+savePath + stringimageurl);			
		    		}
			    else {
			        errorMessage.append("photo not valid. Photo should only be in jpg, jpeg or png!!! ");
                    req.setAttribute("errorMessage", errorMessage.toString());
                    req.getRequestDispatcher(StringUtils.SERVLET_URL_USER_PROFILE).forward(req, resp);
			    }
				
				
				req.setAttribute(StringUtils.MESSAGE_SUCCESS, StringUtils.MESSAGE_SUCCESS_UPDATE);
				resp.sendRedirect(req.getContextPath() + StringUtils.URL_INDEX);
			} else {
				// If update failed, set error message and redirect
				req.setAttribute(StringUtils.MESSAGE_ERROR, StringUtils.MESSAGE_ERROR_UPDATE);
				resp.sendRedirect(req.getContextPath() + StringUtils.SERVLET_URL_USER_PROFILE);
			}
		
		
		} catch (IOException | ServletException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}


		
	}

	@Override
	protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		System.out.println("delete triggered");
		if (dbController.deleteUserInfo(req.getParameter(StringUtils.DELETE_ID)) == 1) {
			req.setAttribute(StringUtils.MESSAGE_SUCCESS, StringUtils.MESSAGE_SUCCESS_DELETE);
			req.getRequestDispatcher(StringUtils.SERVLET_URL_LOGOUT).forward(req, resp);
		} else {
			req.setAttribute(StringUtils.MESSAGE_ERROR, StringUtils.MESSAGE_ERROR_DELETE);
			resp.sendRedirect(req.getContextPath() + StringUtils.URL_INDEX);
		}
	}

}