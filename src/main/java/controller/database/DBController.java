/** Author: Ronak Krishna Shrestha
 * email: np01ai4s2300014@islingtoncollege.edu.np
 * Project Name: NughuCheNiyau
 * Islington College, KamalPokhari
 * LondonMet ID: 22085771
 * Section: AI-3 
 * */

package controller.database;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import model.LoginModel;
import model.PasswordEncryptionWithAes;
import model.UserModel;
import model.PropertyModel;
import utils.StringUtils;
import java.sql.ResultSet;



public class DBController {
	
	/**
	 * Establishes a database connection.
	 * 
	 * @return A Connection object representing the database connection.
	 * @throws SQLException            If a database access error occurs.
	 * @throws ClassNotFoundException If the JDBC driver class is not found.
	 */
	public Connection getConnection() throws SQLException, ClassNotFoundException {
		Class.forName(StringUtils.DRIVER_NAME);
		return DriverManager.getConnection(StringUtils.LOCALHOST_URL, StringUtils.LOCALHOST_USERNAME,
				StringUtils.LOCALHOST_PASSWORD);
	}

	/**
	 * This method registers a new user by inserting their details into the database.
	 * 
	 * @param user The UserModel object containing user details to be registered.
	 * @return An integer value indicating the registration status:
	 *         1: User registered successfully.
	 *         0: Registration failed.
	 *        -1: Error occurred during registration (e.g., SQL or ClassNotFound exceptions).
	 */
	public int registerUser(UserModel user) {
		try {
			PreparedStatement stmt = getConnection().prepareStatement(StringUtils.QUERY_REGISTER_USER);

			stmt.setString(1, user.getUserName());
			stmt.setString(2, user.getEmail());
			stmt.setString(3, user.getPhoneNumber());
			stmt.setString(4, user.getAddress());
			stmt.setString(5, user.getGender());
			stmt.setDate(6, Date.valueOf(user.getDoB()));
			stmt.setString(7, PasswordEncryptionWithAes.encrypt(user.getUserName(), user.getPassword()));
			stmt.setString(8, user.getRole());
			stmt.setString(9, user.getImageUrlFromPart());
			stmt.setString(10, user.getNationality());

			// Statement Run
			int result = stmt.executeUpdate();

			if (result > 0) {
				System.out.println("yahooo");
				return 1;
			} else {
				System.out.println("bad bad");
				return 0;
			}

		} catch (ClassNotFoundException | SQLException ex) {
			ex.printStackTrace();
			System.out.println("bad");
			return -1;
		}
	}

	/**
	 * This method attempts to validate a student login by checking the username and
	 * password against a database.
	 * 
	 * @param username The username provided by the user attempting to log in.
	 * @param password The password provided by the user attempting to log in.
	 * @return An integer value indicating the login status: - 1: Login successful -
	 *         0: Username or password mismatch - -1: Username not found in the
	 *         database - -2: Internal error (e.g., SQL or ClassNotFound exceptions)
	 * @throws SQLException           if a database access error occurs.
	 * @throws ClassNotFoundException if the JDBC driver class is not found.
	 */
	public int getStudentLoginInfo(LoginModel loginModel) {
	    // Try-catch block to handle potential SQL or ClassNotFound exceptions
	    try {
	        // Prepare a statement using the predefined query for login check
	        PreparedStatement st = getConnection().prepareStatement(StringUtils.QUERY_LOGIN_USER_CHECK);

	        // Set the username in the first parameter of the prepared statement
	        st.setString(1, loginModel.getUsername());

	        // Execute the query and store the result set
	        ResultSet result = st.executeQuery();

	        // Check if there's a record returned from the query
	        if (result.next()) {
	            // Get the username from the database
	            String userDb = result.getString(StringUtils.USER_NAME);
	            System.out.println("userDb in getlogin in next: " + userDb);

	            // Get the password from the database
	            String encryptedPwd = result.getString(StringUtils.PASSWORD);
	            System.out.println("en_passowrd in getlogin in next: " + encryptedPwd);

	            // Decrypt the password using the username as the key
	            String decryptedPwd = PasswordEncryptionWithAes.decrypt(encryptedPwd, loginModel.getUsername());
	            System.out.println("de_passowrd in getlogin in next: " + decryptedPwd);

	            // Log the provided credentials for comparison
	            System.out.println("password from loginModel: " + loginModel.getPassword());
	            System.out.println("username from loginModel: " + loginModel.getUsername());

	            // Check if the username and password match the credentials from the database
	            if (userDb.equals(loginModel.getUsername()) && decryptedPwd.equals(loginModel.getPassword())) {
	                // Login successful, return 1
	                return 1;
	            } else {
	                // Username or password mismatch, return 0
	                return 0;
	            }
	        } else {
	            // Username not found in the database, return -1
	            return -1;
	        }

	        // Catch SQLException and ClassNotFoundException if they occur
	    } catch (SQLException | ClassNotFoundException ex) {
	        // Print the stack trace for debugging purposes
	        ex.printStackTrace();
	        // Return -2 to indicate an internal error
	        return -2;
	    }
	}


	
	/**
	 * This method adds a new property listing into the database.
	 * 
	 * @param property The PropertyModel object containing details of the property to be added.
	 * @return An integer value indicating the status of property addition:
	 *         1: Property added successfully.
	 *         0: Property addition failed.
	 *        -1: Error occurred during property addition (e.g., SQL or ClassNotFound exceptions).
	 */
	public int addProperty(PropertyModel property) {
		try {
			PreparedStatement stmt = getConnection().prepareStatement(StringUtils.QUERY_ADD_PROPERTY);

			stmt.setInt(1, property.getSeller_id());
			stmt.setString(2, property.getProperty_brief());
			stmt.setString(3, property.getProperty_address());
			stmt.setInt(4, property.getPrice());
			stmt.setInt(5, property.getProperty_size());
			stmt.setInt(6, property.getNo_of_room());
			stmt.setString(7, property.getProperty_description());
			stmt.setString(8, property.getPropertyimageUrlFromPart());

			// Statement Run
			int result = stmt.executeUpdate();

			if (result > 0) {
				System.out.println("yahooo");
				return 1;
			} else {
				System.out.println("bad else");
				return 0;
			}

		} catch (ClassNotFoundException | SQLException ex) {
			ex.printStackTrace();
			System.out.println("bad");
			return -1;
		}
	}

	/**
	 * This method retrieves the user ID associated with a given username from the database.
	 * 
	 * @param loginModel The LoginModel object containing the username for which the user ID is to be retrieved.
	 * @return An integer representing the user ID if the username is found:
	 *         - User ID (positive integer): User ID associated with the provided username.
	 *         - -1: Indicates that no user was found with the provided username.
	 *         - -2: Indicates an internal error occurred (e.g., SQL or ClassNotFound exceptions).
	 */
	public int getUserID(LoginModel loginModel) {
		try {
			
			PreparedStatement st = getConnection().prepareStatement(StringUtils.GET_USER_ID_BY_USERNAME);

			System.out.println("user name from dbc " + loginModel.getUsername());
			// Set the username in the prepared statement
			st.setString(1, loginModel.getUsername());

			// Execute the query and store the result set
			ResultSet result = st.executeQuery();

			// Check if a record with the given username is found
			if (result.next()) {
				// Retrieve the user ID from the result set
				int userId = result.getInt(StringUtils.USER_ID);

				// Return the retrieved user ID
				return userId;
			} else {
				// No user found with the given username, return a specific value (e.g., -1)
				return -1;
			}
		} catch (SQLException | ClassNotFoundException ex) {
			// Print the stack trace for debugging purposes
			ex.printStackTrace();
			// Return -2 to indicate an internal error
			return -2;
		}
	}

	/**
	 * This method checks if a given email address exists in the database.
	 * 
	 * @param email The email address to be checked for existence.
	 * @return A boolean value indicating if the email exists in the database:
	 *         - true: The email exists in the database.
	 *         - false: The email does not exist in the database or an error occurred during the check.
	 */
	public Boolean checkEmailIfExists(String email) {
		
		try {
			PreparedStatement st = getConnection().prepareStatement(StringUtils.QUERY_LOGIN_USER_CHECK);
			st.setString(1, email);
			ResultSet result = st.executeQuery();
			if (result.next()) {
				// User name and password match in the database
				String userDb = result.getString(StringUtils.USER_NAME);
				String emailDb = result.getString(StringUtils.EMAIL);

				if (emailDb.equals(email))
					return true;
				else
					return false;
			}

		} catch (SQLException | ClassNotFoundException ex) {
			ex.printStackTrace(); // Log the exception for debugging
			return false;
		}
		return false;
	}

	
	/**
	 * This method checks if a given phone number exists in the database.
	 * 
	 * @param phone The phone number to be checked for existence.
	 * @return A boolean value indicating if the phone number exists in the database:
	 *         - true: The phone number exists in the database.
	 *         - false: The phone number does not exist in the database or an error occurred during the check.
	 */
	public Boolean checkNumberIfExists(String phone) {
		// database
		// This method should likely query the database using DBController and return
		// true if the phone number exists, false otherwise.
		try {
			PreparedStatement st = getConnection().prepareStatement(StringUtils.QUERY_LOGIN_USER_CHECK);
			st.setString(1, phone);
			ResultSet result = st.executeQuery();
			if (result.next()) {
				// User name and password match in the database
				String userDb = result.getString(StringUtils.USER_NAME);
				String emailDb = result.getString(StringUtils.EMAIL);

				if (userDb.equals(phone))
					return true;
				else
					return false;
			}

		} catch (SQLException | ClassNotFoundException ex) {
			ex.printStackTrace(); // Log the exception for debugging
			return false;
		}
		return false;
	}

	/**
	 * This method checks if a given username exists in the database.
	 * 
	 * @param username The username to be checked for existence.
	 * @return A boolean value indicating if the username exists in the database:
	 *         - true: The username exists in the database.
	 *         - false: The username does not exist in the database or an error occurred during the check.
	 */
	public Boolean checkUsernameIfExists(String username) {
		// database
		// This method should likely query the database using DBController and return
		// true if the username exists, false otherwise.
		try {
			PreparedStatement st = getConnection().prepareStatement(StringUtils.QUERY_LOGIN_USER_CHECK);
			st.setString(1, username);
			ResultSet result = st.executeQuery();
			if (result.next()) {
				// User name and password match in the database
				String userDb = result.getString(StringUtils.USER_NAME);

				if (userDb.equals(username))
					return true;
				else
					return false;
			}

		} catch (SQLException | ClassNotFoundException ex) {
			ex.printStackTrace(); // Log the exception for debugging
			return false;
		}
		return false;
	}
	
	/**
	 * This method retrieves the profile photo of a user from the database based on the user ID.
	 * 
	 * @param userID The ID of the user whose profile photo is to be retrieved.
	 * @return A string representing the user's profile photo (image string) if found in the database.
	 *         If no profile photo is found or an error occurs, a corresponding message string is returned.
	 */
	public String getUserProfileInfo(int userID) {
		System.out.println("the user id in getUserProfile is " + userID);
		try (Connection con = getConnection()) {
			PreparedStatement st = con.prepareStatement(StringUtils.GET_ALL_STUDENT_INFO_BY_ID);
			// Set the userID in the first parameter of the prepared statement
			st.setInt(1, userID);
			ResultSet result = st.executeQuery();

			if (result.next()) {

				String userPhoto = result.getString("image_str");
				return userPhoto;// success
			}

			else {
				return "Some error has occured";
			}

		} catch (SQLException | ClassNotFoundException ex) {
			ex.printStackTrace();
			return "Check SQL Statements";

		}
	}
	
	/**
	 * This method retrieves detailed user information from the database based on the user ID.
	 * 
	 * @param userID The ID of the user whose information is to be retrieved.
	 * @return A list of string arrays representing the user's detailed information if found in the database.
	 *         Each string array contains: [userName, role, userImage, email, phone_number, address, gender, DoB, nationality, decryptedPwd]
	 *         If no user information is found or an error occurs, an empty list is returned.
	 */
	public List<String[]> getUserInfo(int userID) {
		System.out.println("the user id in getUserProfile is " + userID);

		List<String[]> userProfileList = new ArrayList<>();

		try (Connection con = getConnection()) {
			PreparedStatement st = con.prepareStatement(StringUtils.GET_ALL_STUDENT_INFO_BY_ID);
			st.setInt(1, userID);
			ResultSet result = st.executeQuery();

			if (result.next()) {
				String userName = result.getString("user_name");
				String role = result.getString("role");
				String userImage = result.getString("image_str");
				String email = result.getString("email");
				String phone_number = result.getString("phone_number");
				String address = result.getString("address");
				String gender = result.getString("gender");
				String DoB = result.getString("DoB");
				String nationality = result.getString("nationality");

				String encryptedPwd = result.getString(StringUtils.PASSWORD);

				String decryptedPwd = PasswordEncryptionWithAes.decrypt(encryptedPwd, userName);
				// Create a string array to hold the user profile information
				String[] userProfile = { userName, role, userImage, email, phone_number, address, gender, DoB,
						nationality, decryptedPwd };
				userProfileList.add(userProfile);
			}

		} catch (SQLException | ClassNotFoundException ex) {
			ex.printStackTrace();
			// Handle or log the exception

			// You can return an empty list or handle the error accordingly
		}

		return userProfileList;
	}

	
	/**
	 * This method retrieves user profile information from the database based on the current user's ID.
	 * 
	 * @param currentUser The ID of the current user whose profile information is to be retrieved.
	 * @return An ArrayList of UserModel objects representing user profiles retrieved from the database.
	 *         Each UserModel object contains details such as username, email, phone number, address, gender,
	 *         date of birth, role, image URL, and nationality.
	 *         Returns null if an error occurs during database interaction.
	 */
	public ArrayList<UserModel> getDBUserProfileInfo(int currentUser) {
		try {
			PreparedStatement stmt = getConnection().prepareStatement(StringUtils.QUERY_GET_USER_BY_USERID);
			System.out.print("db controller userPrfile userID: "+currentUser);
			stmt.setInt(1, currentUser);

			ResultSet result = stmt.executeQuery();

			ArrayList<UserModel> users = new ArrayList<UserModel>();

			while (result.next()) {
				UserModel user = new UserModel();
				user.setUserName(result.getString("user_name")); // Assuming column name for username
				user.setEmail(result.getString("email"));
				user.setPhoneNumber(result.getString("phone_number"));
				user.setAddress(result.getString("address"));
				user.setGender(result.getString("gender"));
				user.setDoB(result.getDate("DoB").toLocalDate()); // Assuming column name for date of birth
				user.setPassword(
						PasswordEncryptionWithAes.decrypt(result.getString("user_name"), result.getString("password"))); // Assuming
																														
				user.setRole(result.getString("role"));
				user.setImageUrlFromDB(result.getString("image_str")); // Assuming column name for image URL
				user.setNationality(result.getString("nationality")); // Assuming column name for nationality

				users.add(user);
				System.out.println("user from db: " + user);
			}
			return users;
		} catch (SQLException | ClassNotFoundException ex) {
			ex.printStackTrace();
			return null;
		}
	}

	/**
	 * This method retrieves all property information from the database.
	 * 
	 * @return An ArrayList of PropertyModel objects representing all properties retrieved from the database.
	 *         Each PropertyModel object contains details such as seller ID, property brief, description,
	 *         address, price, size, number of rooms, and property photo URL.
	 *         Returns null if an error occurs during database interaction.
	 */

	public ArrayList<PropertyModel> getAllPropertyInfo() {
		try {
			PreparedStatement stmt = getConnection().prepareStatement(StringUtils.QUERY_GET_ALL_PROPERTY);
			ResultSet result = stmt.executeQuery();

			ArrayList<PropertyModel> properties = new ArrayList<PropertyModel>();

			while (result.next()) {
				PropertyModel property = new PropertyModel();
				property.setSeller_id(result.getInt("seller_id"));
				property.setProperty_brief(result.getString("property_brief"));
				property.setProperty_description(result.getString("description"));
				property.setProperty_address(result.getString("property_address"));
				property.setPrice(result.getInt("price"));
				property.setProperty_size(result.getInt("property_size"));
				property.setNo_of_room(result.getInt("no_of_room"));
				property.setPropertyImageUrlFromDB(result.getString("property_photo"));

				properties.add(property);
				System.out.println("proproty from db: " + properties);
			}
			return properties;
		} catch (SQLException | ClassNotFoundException ex) {
			ex.printStackTrace();
			return null;
		}
	}

	/**
	 * This method retrieves property information from the database based on a search keyword.
	 * 
	 * @param keyword The keyword used for searching properties.
	 * @return An ArrayList of PropertyModel objects representing properties retrieved from the database
	 *         based on the search keyword.
	 *         Each PropertyModel object contains details such as seller ID, property brief, description,
	 *         address, price, size, number of rooms, and property photo URL.
	 *         Returns null if an error occurs during database interaction.
	 */
	public ArrayList<PropertyModel> getSearchedPropertyInfo(String a) {
		try {
			PreparedStatement stmt = getConnection().prepareStatement(StringUtils.QUERY_GET_SEARCHED_PROPERTY);

			stmt.setString(1, a);

			ResultSet result = stmt.executeQuery();

			ArrayList<PropertyModel> properties = new ArrayList<PropertyModel>();

			while (result.next()) {
				PropertyModel property = new PropertyModel();
				property.setSeller_id(result.getInt("seller_id"));
				property.setProperty_brief(result.getString("property_brief"));
				property.setProperty_description(result.getString("description"));
				property.setProperty_address(result.getString("property_address"));
				property.setPrice(result.getInt("price"));
				property.setProperty_size(result.getInt("property_size"));
				property.setNo_of_room(result.getInt("no_of_room"));
				property.setPropertyImageUrlFromDB(result.getString("property_photo"));

				properties.add(property);
				System.out.println("proproty from db: " + properties);
			}
			return properties;
		} catch (SQLException | ClassNotFoundException ex) {
			ex.printStackTrace();
			return null;
		}
	}

	/**
	 * Deletes a user's information from the database based on the provided username.
	 * 
	 * @param username The username of the user whose information is to be deleted.
	 * @return An integer indicating the status of the deletion operation:
	 *         1: User information deleted successfully.
	 *         0: Failed to delete user information.
	 *        -1: Error occurred during database operation.
	 */
	public int deleteUserInfo(String username) {
		try (Connection con = getConnection()) {
			PreparedStatement st = con.prepareStatement(StringUtils.QUERY_DELETE_USER);
			st.setString(1, username);
			System.out.println("in the delete dbc username is " + username);

			return st.executeUpdate();
		} catch (SQLException | ClassNotFoundException ex) {
			ex.printStackTrace(); // Log the exception for debugging
			return -1;
		}
	}
	
	/**
	 * Deletes property information from the database based on the provided seller ID.
	 * 
	 * @param sellerID The ID of the seller whose property information is to be deleted.
	 * @return An integer indicating the status of the deletion operation:
	 *         1: Property information deleted successfully.
	 *         0: Failed to delete property information.
	 *        -1: Error occurred during database operation.
	 */
	public int deletePropertyInfo(String sellerID) {
		try (Connection con = getConnection()) {
			PreparedStatement st = con.prepareStatement(StringUtils.QUERY_DELETE_PROPERTY);
			st.setString(1, sellerID);
			System.out.println("in the delete dbc property is " + sellerID);

			return st.executeUpdate();
		} catch (SQLException | ClassNotFoundException ex) {
			ex.printStackTrace(); // Log the exception for debugging
			return -1;
		}
	}

	/**
	 * Updates user information in the database based on the provided parameters.
	 * 
	 * @param userName        The new username.
	 * @param email           The new email address.
	 * @param address         The new address.
	 * @param phoneNumber     The new phone number.
	 * @param nationality     The new nationality.
	 * @param gender          The new gender.
	 * @param dob             The new date of birth.
	 * @param password        The new password.
	 * @param role            The new role.
	 * @param imageURL        The new image URL.
	 * @return An integer indicating the status of the update operation:
	 *         1: User information updated successfully.
	 *         0: Failed to update user information.
	 *        -1: Error occurred during database operation.
	 */
	public int updateUserInfo(String userName, String email, String address, String phoneNumber, String nationality,
			String gender, String dob, String password, String role, String imageURL) {
		try {
			PreparedStatement stmt = getConnection().prepareStatement(StringUtils.QUERY_UPDATE_USER);

			stmt.setString(1, email); // email
			stmt.setString(2, phoneNumber); // phone_number
			stmt.setString(3, address); // address
			stmt.setString(4, gender); // gender
			stmt.setString(5, dob); // DoB
			stmt.setString(6, password); // password
			stmt.setString(7, role); // role (assuming default value)
			stmt.setString(8, imageURL); // image_str (assuming default value)
			stmt.setString(9, nationality); // nationality
			stmt.setString(10, userName); // WHERE clause: user_name

			// Execute the update query
			int result = stmt.executeUpdate();

			if (result > 0) {
				System.out.println("User information updated successfully");
				return 1;
			} else {
				System.out.println("Failed to update user information");
				return 0;
			}

		} catch (ClassNotFoundException | SQLException ex) {
			ex.printStackTrace();
			System.out.println("bad");
			return -1;
		}
	}
	public int updateUserNoPInfo(String userName, String email, String address, String phoneNumber, String nationality,
			String gender, String dob, String role, String imageURL) {
		try {
			PreparedStatement stmt = getConnection().prepareStatement(StringUtils.QUERY_NoP_UPDATE_USER);

			stmt.setString(1, email); // email
			stmt.setString(2, phoneNumber); // phone_number
			stmt.setString(3, address); // address
			stmt.setString(4, gender); // gender
			stmt.setString(5, dob); // DoB
			stmt.setString(6, role); // role (assuming default value)
			stmt.setString(7, imageURL); // image_str (assuming default value)
			stmt.setString(8, nationality); // nationality
			stmt.setString(9, userName); // WHERE clause: user_name

			// Execute the update query
			int result = stmt.executeUpdate();

			if (result > 0) {
				System.out.println("User information updated successfully");
				return 1;
			} else {
				System.out.println("Failed to update user information");
				return 0;
			}

		} catch (ClassNotFoundException | SQLException ex) {
			ex.printStackTrace();
			System.out.println("bitch");
			return -1;
		}
	}

	/**
	 * Updates the image URL of a user in the database based on the provided parameters.
	 * 
	 * @param imageURL The new image URL.
	 * @param username The username of the user whose image URL is to be updated.
	 * @return An integer indicating the status of the update operation:
	 *         1: User photo URL updated successfully.
	 *         0: Failed to update user photo URL.
	 *        -1: Error occurred during database operation.
	 */
	public int UserphotoUpdate(String imageURL, String username) {
		try {
			System.out.println("in the db uderphoto update ");
			System.out.println("in the db uderphoto update, image url: "+imageURL);
			System.out.println("in the db uderphoto update username: "+username);

			
			PreparedStatement stmt = getConnection().prepareStatement(StringUtils.QUERY_UPDATE_USER_PHOTO);

			stmt.setString(1, imageURL); // image_str (assuming default value)
			stmt.setString(2, username); // image_str (assuming default value)

			// Statement Run
			int result = stmt.executeUpdate();

			if (result > 0) {
				System.out.println("in the true of db uderphoto update ");

				System.out.println("yahooo");
				return 1;
			} else {
				System.out.println("bad else");
				return 0;
			}

		} catch (ClassNotFoundException | SQLException ex) {
			ex.printStackTrace();
			System.out.println("bad");
			return -1;
		}
	}

	/**
	 * Updates property information in the database based on the provided parameters.
	 * 
	 * @param sellerID           The ID of the seller whose property information is to be updated.
	 * @param propertyBrief      The new property brief.
	 * @param propertyDescription The new property description.
	 * @param price              The new price.
	 * @param propertyAddress    The new property address.
	 * @param propertySize       The new property size.
	 * @param imageURL           The new image URL.
	 * @param no_of_room         The new number of rooms.
	 * @return An integer indicating the status of the update operation:
	 *         1: Property information updated successfully.
	 *         0: Failed to update property information.
	 *        -1: Error occurred during database operation.
	 */
	public int updatePropertyInfo(int sellerID, String propertyBrief, String propertyDescription, int price,
			String propertyAddress, int propertySize, String imageURL, int no_of_room) {
		System.out.println("in updatePropertyInfo in dfc");
		try {
			// Prepare the SQL query to update property information
			PreparedStatement stmt = getConnection().prepareStatement(StringUtils.QUERY_UPDATE_PROPERTY);
			System.out.println("in updatePropertyInfo in dfc try");

			// Bind the parameters to the SQL query
			stmt.setString(1, propertyBrief); // property_brief
			stmt.setString(2, propertyAddress); // property_address
			stmt.setInt(3, price); // price

			stmt.setInt(4, propertySize); // property_size
			stmt.setInt(5, no_of_room); // property_size

			stmt.setString(6, propertyDescription); // property_description
			stmt.setString(7, imageURL);

			stmt.setInt(8, sellerID); // WHERE clause: seller_id

			// Execute the update query
			int result = stmt.executeUpdate();

			if (result > 0) {
				System.out.println("Property information updated successfully");
				return 1;
			} else {
				System.out.println("Failed to update property information");
				return 0;
			}

		} catch (ClassNotFoundException | SQLException ex) {
			ex.printStackTrace();
			System.out.println("Error updating property information");
			return -1;
		}
	}
}