<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@page import="utils.StringUtils"%>
<%@page import="javax.servlet.http.HttpSession"%>
<%@page import="javax.servlet.http.HttpServletRequest"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page import="java.util.logging.Logger"%>

<%
Logger logger = Logger.getLogger("MyJSPLogger");
HttpSession userSession = request.getSession();
Integer currentSellerID = (Integer) userSession.getAttribute(StringUtils.USER_ID);
String contextPath = request.getContextPath();
logger.info("add property user id: " + currentSellerID);
%>

<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>Add Property</title>
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/stylesheets/navbar.css">
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/stylesheets/addProperty.css">
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/stylesheets/footer.css">
<link rel="stylesheet"
	href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.0.0-beta3/css/all.min.css">
<link rel="preconnect" href="https://fonts.googleapis.com">
<style>
body, html {
	margin: 0;
	padding: 0;
	background-color: #fff; /* Set background color to white */
	font-family: Arial, sans-serif; /* Specify a fallback font */
}
</style>
</head>
<body>
	<header>
		<jsp:include page="navbar.jsp" />
		<%--action tag --%>
	</header>


	<main>
		<div class="add-property-container">
			<h1>Add Property</h1>
			<form id="addPropertyForm"
				action="<%=request.getContextPath() + StringUtils.SERVLET_URL_PROPERTY%>"
				method="post" enctype="multipart/form-data">
				<div class="form-group">
					<label for="propertyBrief">Property Brief:</label> <input
						type="text" id="propertyBrief" name="property_brief"
						placeholder="Enter property brief">
				</div>
				<div class="form-group">
					<label for="propertyDetail">Property Detail:</label>
					<textarea id="propertyDetail" name="property_description"
						placeholder="Enter property description" rows="4"></textarea>
				</div>
				<div class="form-group">
					<label for="propertyPrice">Price:</label> <input type="text"
						id="propertyPrice" name="price" placeholder="Enter property price">
				</div>
				<div class="form-group">
					<label for="propertyLocation">Location:</label> <input type="text"
						id="propertyLocation" name="property_address"
						placeholder="Enter property location">
				</div>
				<div class="form-group">
					<label for="PropertySize">Area of the property:</label> <input
						type="number" id="PropertySize" name="property_size"
						placeholder="Area of the property">
				</div>
				<div class="form-group">
					<label for="numOfRooms">Number of Rooms:</label> <input
						type="number" id="numOfRooms" name="no_of_room"
						placeholder="Enter number of rooms">
				</div>

				<!-- Hidden field to include currentSellerID -->
				<input type="hidden" name="sellerID" value="<%=currentSellerID%>">

				<div class="form-group">
					<label for="propertyPhoto">Property Picture:</label>
					<div class="upload-container">
						<input type="file" id="propertyPhoto" name="property_photo"
							accept="image/*"> <label for="propertyPhoto"
							class="upload-button"> <i class="fas fa-upload"></i>
							Choose File
						</label>
					</div>
				</div>


				<div class="form-group">
					<button type="submit">Submit</button>
				</div>
			</form>
		</div>
	</main>

	<jsp:include page="footer.jsp" />
	<%--action tag --%>


</body>
</html>
