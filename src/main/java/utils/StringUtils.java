package utils;

public class StringUtils {
	// Start: DB Connection
	public static final String DRIVER_NAME = "com.mysql.cj.jdbc.Driver";
	public static final String LOCALHOST_URL = "jdbc:mysql://localhost:3306/nughucheniyau";
	public static final String LOCALHOST_USERNAME = "root";
	public static final String LOCALHOST_PASSWORD = "";

	public static final String IMAGE_ROOT_PATH = "Users\\hp\\OneDrive\\文档\\eclispse dsa\\NughuCheNiyau\\src\\main\\webapp\\resources\\images\\";
	/*
	 * Users\\hp\\Documents\\eclispse dsa
	 *  Users\hp\OneDrive\文档\eclispse dsa
	 */
	public static final String IMAGE_DIR_PROPERTY = "C:\\" + IMAGE_ROOT_PATH + "property\\";
	public static final String IMAGE_DIR_USER = "C:\\" + IMAGE_ROOT_PATH + "user\\";
	// End: DB Connection

	// Start: Queries
	public static final String QUERY_REGISTER_USER = "INSERT INTO user ("
			+ "user_name, email,  phone_number, address, gender, DoB, password, role, image_str, nationality )"
			+ "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?,?)";

	public static final String QUERY_ADD_PROPERTY = "INSERT INTO property("
			+ "seller_id, property_brief, property_address, price, property_size, no_of_room, description, property_photo "
			+ ")VALUES(?,?,?,?,?,?,?,?)";
	

    public static final String QUERY_UPDATE_USER = "UPDATE user "
            + "SET email = ?, phone_number = ?, address = ?, gender = ?, DoB = ?, password = ?, role = ?, image_str = ?, nationality = ? "
            + "WHERE user_name = ?";
    
    public static final String QUERY_NoP_UPDATE_USER = "UPDATE user "
            + "SET email = ?, phone_number = ?, address = ?, gender = ?, DoB = ?, role = ?, image_str = ?, nationality = ? "
            + "WHERE user_name = ?";

    public static final String QUERY_UPDATE_USER_PHOTO = "UPDATE user "
            + "SET image_str = ? "
            + "WHERE user_name = ?";

    
    public static final String QUERY_UPDATE_PROPERTY = "UPDATE property "
            + "SET property_brief = ?, property_address = ?, price = ?, property_size = ?, no_of_room = ?, description = ?, property_photo = ? "
            + "WHERE seller_id = ?";

	public static final String QUERY_LOGIN_USER_CHECK = "SELECT * FROM user WHERE user_name = ?";

	public static final String GET_ALL_STUDENT_INFO_BY_ID = "SELECT * FROM user WHERE user_ID = ?";
	public static final String GET_USER_ID_BY_USERNAME = "SELECT * FROM user WHERE user_name = ?";
	
	public static final String QUERY_GET_USER_BY_USERID = "SELECT * FROM user WHERE user_ID = ?";

	public static final String QUERY_GET_ALL_PROPERTY = "SELECT * FROM property";
	
	public static final String QUERY_GET_SEARCHED_PROPERTY = "SELECT * FROM property where property_address=?";

	
	public static final String QUERY_DELETE_USER = "DELETE FROM user WHERE user_name = ?";

	
	public static final String QUERY_DELETE_PROPERTY = "DELETE FROM property WHERE seller_id = ?";


	// End: Queries

	// Start: Parameter names
	public static final String USERNAME = "userName";
	public static final String USER_ID = "user_ID";

	public static final String USER_NAME = "user_name"; // This seems redundant or can be removed
	public static final String FULL_NAME = "fullName";
	public static final String DoB = "DoB";
	public static final String GENDER = "gender";
	public static final String EMAIL = "email";
	public static final String ADDRESS = "address";
	public static final String PHONE_NUMBER = "phoneNumber";
	public static final String Nationality = "nationality"; // Consider using lowercase 'nationality'
	public static final String PASSWORD = "password";
	public static final String RETYPE_PASSWORD = "repassword"; // Should match 'name="repassword"' in HTML
	public static final String ROLE = "role";
	public static final String PHOTO = "image";

	public static final String SELLER_ID = "sellerID";
	public static final String PROPERTY_BRIEF = "property_brief";
	public static final String PROPERTY_DESCRIPTION = "property_description";
	public static final String PROPERTY_ADDRESS = "property_address";
	public static final String PRICE = "price";
	public static final String PROPERTY_SIZE = "property_size";
	public static final String NO_OF_ROOM = "no_of_room";
	public static final String PROPERTY_PHOTO = "property_photo";

	// End: Parameter names

	// Register Page Messages
	public static final String MESSAGE_SUCCESS_REGISTER = "Successfully Registered!";
	public static final String MESSAGE_ERROR_REGISTER = "Please correct the form data.";
	public static final String MESSAGE_ERROR_USERNAME = "Username is already registered.";
	public static final String MESSAGE_ERROR_EMAIL = "Email is already registered.";
	public static final String MESSAGE_ERROR_PHONE_NUMBER = "Phone number is already registered.";
	public static final String MESSAGE_ERROR_PASSWORD_UNMATCHED = "Password is not matched.";
	public static final String MESSAGE_ERROR_INCORRECT_DATA = "Please correct all the fields.";

	
	// Login Page Messages
	public static final String MESSAGE_SUCCESS_LOGIN = "Successfully LoggedIn!";
	public static final String MESSAGE_ERROR_LOGIN = "Either username or password is not correct!";
	public static final String MESSAGE_ERROR_CREATE_ACCOUNT = "Account for this username is not registered! Please create a new account.";

	// Other Messages
	public static final String MESSAGE_ERROR_SERVER = "An unexpected server error occurred.";
	public static final String MESSAGE_SUCCESS_DELETE = "Successfully Deleted!";
	public static final String MESSAGE_ERROR_DELETE = "Cannot delete the user!";

	public static final String MESSAGE_SUCCESS = "successMessage";
	public static final String MESSAGE_ERROR = "errorMessage";
	
	public static final String MESSAGE_SUCCESS_UPDATE = "successUpdate";
	public static final String MESSAGE_ERROR_UPDATE = "ErrorUpdate";

	// End: Validation Messages

	// Start: JSP Route
	public static final String PAGE_URL_LOGIN = "/pages/login.jsp";
	public static final String PAGE_URL_REGISTER = "/pages/registration.jsp";
	public static final String URL_INDEX = "/index.jsp";
	public static final String PAGE_URL_FOOTER = "/pages/footer.jsp";
	public static final String PAGE_URL_HEADER = "/pages/header.jsp";
	public static final String PAGE_URL_PROPERTY = "/pages/realEstate.jsp";
	public static final String URL_LOGIN = "/login.jsp";
	public static final String PAGE_URL_PROPERTY_PROFILE = "pages/PropertyProfile.jsp";
	public static final String PAGE_URL_USER_PROFILE = "pages/UserProfile.jsp";
	public static final String PAGE_URL_ADD_PROPERTY = "pages/addProperty.jsp";




	// End: JSP Route

	// Start: Servlet Route
	public static final String SERVLET_URL_LOGIN = "/login";
	public static final String SERVLET_URL_REGISTER = "/register";
	public static final String SERVLET_URL_LOGOUT = "/logout";
	public static final String SERVLET_URL_HOME = "/home";
	public static final String SERVLET_URL_PROPERTY = "/property";
	public static final String SERVLET_URL_PROPERTY_DISPLAY = "/property_display";
	public static final String SERVLET_URL_MODIFY_USER = "/modifyUser";
	public static final String SERVLET_URL_MODIFY_PROPERTY = "/modifyProperty";

	public static final String SERVLET_URL_USER_PROFILE = "/userProfile";
	public static final String SERVLET_URL_PROPERTY_PROFILE="/propertyProfile";




	// End: Servlet Route

	public static final String ONLY_ALPABHET = "[a-zA-Z]+";
	public static final String NUM_ALPABHET = "[a-zA-Z0-9]+";
	public static final String PASS_CODE = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=]).*$";

	// Start: Normal Text
	public static final String USER = "user";
	public static final String SUCCESS = "success";
	public static final String TRUE = "true";
	public static final String JSESSIONID = "JSESSIONID";
	public static final String LOGIN = "Login";
	public static final String LOGOUT = "Logout";
	public static final String User_MODEL = "userModel";
	public static final String User_LISTS = "userLists";
	public static final String PROPERTY_MODEL = "propertyModel";
	public static final String PROPERTY_LISTS = "propertyLists";
	public static final String PROPERTY_LISTS_SEARCHED = "propertyLists";

	public static final String PROPERTY_PROFILE = "propertyProfile";
	public static final String USER_PROFILE = "userProfile";


	public static final String SLASH = "/";
	public static final String DELETE_ID= "deleteId";
	public static final String UPDATE_ID= "updateId";
	// End: Normal Text
}
