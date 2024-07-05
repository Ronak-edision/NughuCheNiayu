<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@page import="utils.StringUtils"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@page import="javax.servlet.http.HttpSession"%>
<%@page import="javax.servlet.http.HttpServletRequest"%>
<%@ page import="java.util.logging.Logger"%>

<%
// Get the session and request objects
Logger logger = Logger.getLogger("MyJSPLogger");
HttpSession userSession = request.getSession();

String userName = (String) userSession.getAttribute("userName");
String imageResult = (String) userSession.getAttribute("image_str");
String userRole = (String) userSession.getAttribute("role");
String userEmail = (String) userSession.getAttribute("email");
String userPhoneNumber = (String) userSession.getAttribute("phone_number");
String userAddress = (String) userSession.getAttribute("address");
String userGender = (String) userSession.getAttribute("gender");
String userDoB = (String) userSession.getAttribute("DoB");
String userNationality = (String) userSession.getAttribute("nationality");
String userPassword = (String) userSession.getAttribute("password");

String contextPath = request.getContextPath();
%>

<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>User Profile</title>
<link rel='stylesheet'
	href='https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.2.1/css/all.min.css'>
<%--  <link rel="stylesheet"
	href="${pageContext.request.contextPath}/stylesheets/register.css">
 --%>
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/stylesheets/userProfile.css">

<link rel="stylesheet"
	href="${pageContext.request.contextPath}/stylesheets/footer.css">

<style>
.form-container {
	margin-bottom: 20px;
	display: flex;
	flex-direction: column;
	align-items: center; /* Center forms horizontally within container */
}

.form-container form {
	width: 100%;
	max-width: 400px; /* Adjust as needed */
	/* User Profile Form Specific Styles */ . user-profile-form { display :
	flex;
	justify-content: center;
	align-items: center;
	min-height: 100vh;
	background: #fff;
	padding: 0 10px;
}
</style>

</head>
<body>
	<header>
		<jsp:include page="navbar.jsp" />
	</header>

	<main>

		<div class="form-container">
			<div class="user-profile-form">
				<c:forEach items="${userProfile}" var="user">


					<form id="UpdateForm-${userName}" method="post"
						action="<%=contextPath + StringUtils.SERVLET_URL_MODIFY_USER %>" enctype="multipart/form-data">
						<h2>User Detail</h2>

						<div class="row">
							<div class="col">
								<label for="image">Current Profile Picture</label> <img
									src="${pageContext.request.contextPath}/resources/images/user/${user.imageUrlFromPart}"
									alt="Current Profile Picture"
									style="width: 200px; height: 200px;">
							</div>

						</div>

						<div class="form-group username">
							<label for="fullname"> UserName</label> <input type="text"
								id="userName" name="userName" value="${user.userName} ">
						</div>

						<div class="form-group email">
							<label for="email">Email Address</label> <input type="text"
								id="email" name="email" value="${user.email }">
						</div>

						<div class="form-group address">
							<label for="address">Address</label> <input type="text"
								id="address" name="address" value="${user.address }">
						</div>

						<div class="form-group phonenumber">
							<label for="phonenumber">Phone Number</label> <input type="text"
								id="phonenumber" name="phoneNumber" value="${user.phoneNumber }">
						</div>

						<div class="form-group nationality">
							<label for="nationality">Nationality</label> <input type="text"
								id="nationality" name="nationality" value="${user.nationality }">
						</div>

						<div class="form-group date">
							<label for="date">Birth Date</label> <input type="date" id="date"
								name="DoB" value="${user.doB }">
						</div>

						<div class="form-group gender">
							<label for="gender">Gender</label> <select id="gender"
								name="gender">
								<option value="" disabled>Select your gender</option>
								<option value="Male"
									<%="Male".equals(userGender) ? "selected" : ""%>>Male</option>
								<option value="Female"
									<%="Female".equals(userGender) ? "selected" : ""%>>Female</option>
								<option value="Other"
									<%="Other".equals(userGender) ? "selected" : ""%>>Other</option>
							</select>
						</div>

						<div class="form-group password">
							<label for="password">Old Password</label> <input type="text"
								id="password" name="oldPassword"
								placeholder="enter old password" autocomplete="off"> <i
								id="pass-toggle-btn" class="fa-solid fa-eye"></i>
						</div>

						<div class="form-group password">
							<label for="re-password">New Password</label> <input
								type="text" id="re-password" name="newPassword"
								placeholder="enter new password"> <i
								id="pass-toggle-btn" class="fa-solid fa-eye"></i>
						</div>





						<%
						logger.info("User Name in form: " + userName);
						%>

						<div class="form-group hiddenValue">
							<input type="hidden" name="Role" value="<%=userRole%>" /> <input
								type="hidden" name="imageURL" value="<%=imageResult%>" /> <input
								type="hidden" name="updateId" value="<%=userName%>" /> <input
								type="hidden" name="password" value="<%=userPassword%>" />


						</div>

						<div class="form-group submit-btn">
							<input type="submit" name="update" value="UpdateDate">
						</div>

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
  
				</c:forEach>
			</div>




			<div class="user-profile-form">

				<form id="UpdateImage-${userName}" method="post"
					action="<%=contextPath + StringUtils.SERVLET_URL_MODIFY_USER %>"
					enctype="multipart/form-data">

					

					<div class="form-group updateIMage">
						<label for="image">Update Profile Picture</label> <input
							type="file" id="image" name="image">
					</div>

					<div class="form-group hiddenValue">
						<input type="hidden" name="ImageUpdateUser" value="<%=userName%>" />
					</div>
					-
					<div class="form-group submit-btn">
						<input type="submit" name="update" value="ImageUpdate">
					</div>
				</form>

			</div>
			<div class="user-profile-form">

				<form id="deleteForm-${userName}" method="post"
					action="<%=contextPath + StringUtils.SERVLET_URL_MODIFY_USER %>">
					<div class="form-group submit-btn">
						<input type="hidden" name="<%=StringUtils.DELETE_ID %>"
							value="${userName}" />
						<button type="button" onclick="confirmDelete('${userName}')">Delete</button>
					</div>
				</form>

			</div>
		</div>
	</main>

	<jsp:include page="footer.jsp" />


	<script>
		function confirmDelete(userName) {
			if (confirm("Are you sure you want to delete this user: "
					+ userName + "?")) {
				document.getElementById("deleteForm-" + userName).submit();
			}
		}
	</script>
</html>
