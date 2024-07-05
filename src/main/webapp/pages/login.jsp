<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>




<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Login User</title>
<link rel='stylesheet'
	href='https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.2.1/css/all.min.css'>
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/stylesheets/register.css">

</head>
<body>

	<form id="myForm" action="<%=request.getContextPath()%>/login"
		method="post">
		<h2>Login User</h2>
		<div class="login-container">
			<div class="info-box">
				<p>For existing User</p>

				<div class="icon">&#128276;</div>

			</div>
		</div>
		<br> <br>
		<div class="form-group username">
			<label for="userName"> userName</label> <input type="text"
				id="userName" placeholder="Enter your userName" name="userName">
		</div>

		<div class="form-group password">
			<label for="password">password</label> <input type="password"
				id="password" placeholder="Enter your password" name="password">
			<i id="pass-toggle-btn" class="fa-solid fa-eye"></i>
		</div>


		<div class="form-group submit-btn">
			<input type="submit" value="Submit">
		</div>


		<p class="signup-text">
			Don't have an account? <a href="#">Sign Up</a>
		</p>
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

	<div id="thank-you-content" style="display: none; color: #ffffff">
		<h1>Thank you for filling out the form correctly 💙</h1>
	</div>

	

</body>
</body>
</html>