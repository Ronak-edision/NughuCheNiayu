<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>



<%@ page import="java.util.logging.Logger"%>

<%@page import="utils.StringUtils"%>



<%
// Get the session and request objects
Logger logger = Logger.getLogger("MyJSPLogger");

String contextPath = request.getContextPath();
%>

<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Register User</title>
<link rel='stylesheet'
	href='https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.2.1/css/all.min.css'>
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/stylesheets/register.css">



</head>
<body>
	<form id="myForm" action="<%=request.getContextPath()%>/register"
		method="post" enctype="multipart/form-data">

		<h2>Register User</h2>

		<div class="form-group username">
			<label for="fullname"> UserName</label> <input type="text"
				id="userName" placeholder="Enter your userName" autocomplete="off"
				name="userName">
		</div>
		<div class="form-group email">
			<label for="email">Email Address</label> <input type="text"
				id="email" placeholder="Enter your email address" name="email">
		</div>
		<div class="form-group address">
			<label for="Nationality">address</label> <input type="text"
				id="address" placeholder="Enter your address" name="address">
		</div>
		<div class="form-group phonenumber">
			<label for="address">Phone Number</label> <input type="text"
				id="phonenumber" placeholder="Enter your phone number"
				name="phoneNumber">
		</div>
		<div class="form-group nationality">
			<label for="address">Nationality</label> <input type="text"
				id="address" placeholder="Enter your address" name="nationality">
		</div>

		<div class="form-group date">
			<label for="date">Birth Date</label> <input type="date" id="date"
				placeholder="Select your date" name="DoB">
		</div>
		<div class="form-group gender">
			<label for="gender">Gender</label> <select id="gender" name="gender">
				<option value="" selected disabled>Select your gender</option>
				<option value="Male">Male</option>
				<option value="Female">Female</option>
				<option value="Other">Other</option>
			</select>
		</div>


		<div class="form-group password">
			<label for="password">Password</label> <input type="password"
				id="passwordInput" placeholder="Enter your password"
				autocomplete="off" name="password"> <i id="pass-toggle-btn"
				class="fa-solid fa-eye"
				onclick="togglePasswordVisibility('passwordInput','pass-toggle-btn')"></i>
		</div>


		<div class="form-group password">
			<label for="re-password">Retype Password</label> <input
				type="text" id="re-password"
				placeholder="Enter your password again" name="repassword"> <i
				id="pass-toggle-btn" class="fa-solid fa-eye onclick="
				onclick="togglePasswordVisibility('rePasswordInput', 'repass-toggle-btn')"></i>

		</div>

		<div class="role">
			<p>Select Role:</p>
			<input type="radio" id="user" name="role" value="user"> <label
				for="user">User</label> <input type="radio" id="seller" name="role"
				value="seller"> <label for="seller">Seller</label> <input
				type="radio" id="admin" name="role" value="admin"> <label
				for="admin">Admin</label>
		</div>

		<div class="row">
			<div class="col">
				<label for="image">Profile Picture</label> <input type="file"
					id="image" name="image">
			</div>
		</div>

		<div class="form-group submit-btn">
			<input type="submit" value="Submit">
		</div>

		<%-- <% 
            // Check if errorMessage attribute is set (due to servlet error handling)
            if (request.getAttribute("errorMessage") != null) {
                // Display the error message
                out.print("<p style='color: red;'>" + request.getAttribute("errorMessage") + "</p>");

                // Log the error (optional)
                Logger.getLogger(getClass().getName()).severe("Error occurred during registration: " + request.getAttribute("errorMessage"));
            }
        %> --%>
		<%
		// Check if errorMessage attribute is set (due to servlet error handling)
		if (request.getAttribute("errorMessage") != null) {
			// Split error message by full stop (.)
			String errorMessage = (String) request.getAttribute("errorMessage");
			String[] errorSegments = errorMessage.split("\\.");

			// Display each error segment in a separate <p> tag
			for (String errorSegment : errorSegments) {
				String trimmedSegment = errorSegment.trim();
				if (!trimmedSegment.isEmpty()) {
			out.print("<p style='color: red;'>" + trimmedSegment + ".</p>");
				}
			}
		}
		%>


	</form>


	<script>
		function togglePasswordVisibility(inputId, iconId) {
			const passwordInput = document.getElementById(inputId);
			const eyeIcon = document.getElementById(iconId);

			if (passwordInput.type === 'password') {
				passwordInput.type = 'text';
				eyeIcon.classList.remove('fa-eye');
				eyeIcon.classList.add('fa-eye-slash');
			} else {
				passwordInput.type = 'password';
				eyeIcon.classList.remove('fa-eye-slash');
				eyeIcon.classList.add('fa-eye');
			}
		}
	</script>



</body>

</html>
