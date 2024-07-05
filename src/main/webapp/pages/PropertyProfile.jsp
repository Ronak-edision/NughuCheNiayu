<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@page import="utils.StringUtils"%>
<%@ page import="java.util.logging.Logger"%>

<%
Logger logger = Logger.getLogger("MyJSPLogger");
String contextPath = request.getContextPath();
%>

<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>Property Profile</title>
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/stylesheets/navbar.css">
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/stylesheets/addProperty.css">
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/stylesheets/footer.css">
<link rel="stylesheet"
	href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.0.0-beta3/css/all.min.css">

</head>
<body>
	<header>
		<jsp:include page="navbar.jsp" />
	</header>
	<main>
		<div class="property-container">
			<h1>Property Profile</h1>
			<c:forEach items="${propertyProfile}" var="property">
				<form id="propertyProfileForm" method="post"
					action="<%=contextPath + StringUtils.SERVLET_URL_MODIFY_PROPERTY%>"
					enctype="multipart/form-data">
					<div class="form-group">
						<label>Property Image:</label> <img
							src="${pageContext.request.contextPath}/resources/images/property/${property.propertyimageUrlFromPart}"
							alt="Property Image" style="width: 400px; height: auto;">
					</div>

					<div class="form-group">
						<label for="propertyBrief">Property Brief:</label> <input
							type="text" id="propertyBrief" name="property_brief"
							value="${property.property_brief}">
					</div>
					<div class="form-group">
						<label for="propertyDetail">Property Detail:</label>
						<textarea id="propertyDetail" name="property_description">${property.property_description}</textarea>
					</div>
					<div class="form-group">
						<label for="propertyPrice">Price:</label> <input type="number"
							id="propertyPrice" name="price" value="${property.price}">
					</div>
					<div class="form-group">
						<label for="propertyLocation">Location:</label> <input type="text"
							id="propertyLocation" name="property_address"
							value="${property.property_address}">
					</div>
					<div class="form-group">
						<label for="propertySize">Area of the property:</label> <input
							type="number" id="propertySize" name="property_size"
							value="${property.property_size}">

						<div class="form-group">
							<label for="no_of_room">no of rooms:</label> <input type="number"
								id="no_of_room" name="no_of_room"
								value="${property.property_size}">
						</div>


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

						<div class="form-group hiddenValue">
							<input type="hidden" name="propertyId"
								value="${property.seller_id}" /> <input type="hidden"
								name="imageURL" value="${property.propertyimageUrlFromPart}" />
							<input type="hidden" name="updateId"
								value="${property.seller_id}">

						</div>
						<div class="form-group submit-btn">
							<input type="submit" value="Update">
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
				<form id="deleteForm-${property.seller_id}" method="post"
					action="<%=contextPath + StringUtils.SERVLET_URL_MODIFY_PROPERTY %>">
					<input type="hidden" name="<%=StringUtils.DELETE_ID %>"
						value="${property.seller_id}" />
					<button type="button"
						onclick="confirmDelete('${property.seller_id}')">Delete</button>
				</form>
			</c:forEach>
		</div>
	</main>
	<jsp:include page="footer.jsp" />
	<script>
		function confirmDelete(sellerId) {
			if (confirm("Are you sure you want to delete this property with ID: "
					+ sellerId + " ?")) {
				document.getElementById("deleteForm-" + sellerId).submit();
			}
		}
	</script>

</body>
</html>
