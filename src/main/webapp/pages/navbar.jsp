<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>


<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<%@page import="utils.StringUtils"%>
<%@page import="javax.servlet.http.HttpSession"%>
<%@page import="javax.servlet.http.HttpServletRequest"%>
<%@ page import="java.util.logging.Logger"%>


<%
// Get the session and request objects
Logger logger = Logger.getLogger("MyJSPLogger");
HttpSession userSession = request.getSession();
String currentUser = (String) userSession.getAttribute(StringUtils.USERNAME);
String contextPath = request.getContextPath();
String currentUserRole = (String) userSession.getAttribute(StringUtils.ROLE);
String currentProfile = (String) userSession.getAttribute("image_str");
logger.info("Current User: " + currentUser);
logger.info("current Profile: " + currentProfile);
logger.info("current user role: " + currentUserRole);
%>

<!DOCTYPE html>
<html>
<head>

<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>Your Page Title</title>
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/stylesheets/navbar.css">
<style>
/* Style for the circular profile photo */
.profile-photo {
	width: 40px; /* Adjust size as needed */
	height: 40px; /* Adjust size as needed */
	border-radius: 50%; /* Make it round */
	margin-right: 10px; /* Add space between photo and text */
}
</style>
</head>
<body>

	<header>
		<div class="left-head">
			<div class="logo">
				<img
					src="${pageContext.request.contextPath}/resources/images/others/website_logo_1.jpg"
					alt="">
			</div>

			<nav id="navbar">
				<div class="close">
					<img
						src="${pageContext.request.contextPath}/images/others/close.png"
						alt="">
				</div>
				<div class="menu-bar">
					<ul>
						<li><a href="${pageContext.request.contextPath}/">Home</a></li>
						<li><a href="<%=request.getContextPath()%>/property_display">Property</a></li>
						<li><a
							href="${pageContext.request.contextPath}/pages/contact.jsp">Contact</a></li>
						<%
						if (currentUser != null && !currentUser.isEmpty()) {
						%>

						<li><a href="<%=request.getContextPath()%>/userProfile">User
								Profile</a></li>
						<%
						}
						%>

						<%
						if ("seller".equals(currentUserRole)) {
						%>

						<li><a href="<%=request.getContextPath()%>/property">Add
								Property</a></li>

						<li><a href="<%=request.getContextPath()%>/propertyProfile">Property
								Profile</a></li>
						<%
						}
						%>
					</ul>
				</div>
			</nav>
		</div>

		<div class="right-head">
			<%
			if (currentUser != null && !currentUser.isEmpty()) {
			%>
			<!-- Display user profile and logout link -->
			<img class="profile-photo"
				src="${pageContext.request.contextPath}/resources/images/user/<%  out.println(currentProfile);%>"
				alt="Profile Photo"> <span>Welcome,<%
 out.println(currentUser);
 %></span>
			<form action="<%=request.getContextPath()%>/logout" method="post">
				<input type="submit" value="Log Out" />
			</form>
			<%
			} else {
			%>
			<!-- Display login and create account links -->
			<a href="${pageContext.request.contextPath}/pages/login.jsp">Log
				In</a> <a
				href="${pageContext.request.contextPath}/pages/registration.jsp">Create
				Account</a>
			<%
			}
			%>


		</div>

		<div class="menu">
			<img src="${pageContext.request.contextPath}/image/menu.png" alt="">
		</div>
	</header>

</body>
</html>
