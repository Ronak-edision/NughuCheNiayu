<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>

<%@page import="model.PropertyModel"%>
<%@page import="java.util.ArrayList"%>
<%@page import="utils.StringUtils"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page import="java.util.logging.Logger"%>
<%@page import="javax.servlet.http.HttpSession"%>
<%@page import="javax.servlet.http.HttpServletRequest"%>


<%
String contextPath = request.getContextPath();
Logger logger = Logger.getLogger("MyJSPLogger");
logger.info("in the realestate jsp");
HttpSession userSession = request.getSession();
%>


<!DOCTYPE html>
<html lang="en">

<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>Product Page</title>
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/stylesheets/footer.css">
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/stylesheets/navbar.css">
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/stylesheets/productPage.css">

<!-- Font Awesome -->
<link rel="stylesheet"
	href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.0.0-beta3/css/all.min.css">

<!-- Google Fonts -->
<link rel="preconnect" href="https://fonts.googleapis.com">
<link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
<link
	href="https://fonts.googleapis.com/css2?family=Ubuntu:wght@300;400;500;700&display=swap"
	rel="stylesheet">
<link href="https://fonts.googleapis.com/icon?family=Material+Icons"
	rel="stylesheet">

<style>
/* icons-home style */
.icons-home {
	display: grid;
	grid-template-columns: repeat(3, 1fr); /* 3 columns layout */
	grid-template-rows: repeat(2, auto); /* 2 rows layout */
	gap: 10px; /* Spacing between items */
	margin-top: 20px; /* Adjust as needed */
}

.name-icon {
	display: flex;
	flex-direction: column;
	align-items: center;
	justify-content: center;
	text-align: center;
	height: 100%; /* Ensure each icon occupies full height */
}

.name-icon>span {
	text-transform: capitalize;
	color: var(--h2-text-color);
	margin-top: 5px; /* Spacing between label and icon */
}

.icon {
	display: flex;
	align-items: center;
}

.icon i {
	color: var(--icon-info-color);
	font-size: 20px;
	margin-right: 5px;
}

.search-box {
	position: absolute;
	top: 17%;
	left: 80%;
	transform: translate(-50%, -50%);
	background-color: #2c3441;
	height: 50px;
	border-radius: 40px;
	padding: 10px 18px;
}

.search-box:hover>.icon {
	background-color: #536179;
}

input {
	/* width: 0; */
	border: none;
	outline: none;
	padding: 0 10px;
	background: none;
	font-size: 1.1rem;
	transition: 0.5s ease;
	line-height: 40px;
	color: #fff;
}
</style>

</head>

<body>


	<jsp:include page="../pages/navbar.jsp" />
	<%--action tag --%>

	<div class="search-container">
		<form id="searchForm"
			action="<%=contextPath + StringUtils.SERVLET_URL_PROPERTY_DISPLAY%>"
			method="POST">
			<span class="search-box"> <input type="text" name="searchTerm"
				id="searchTerm" placeholder="Search...">
				<button type="submit" form="searchForm">
					<i class="fas fa-search"></i>
				</button>
			</span>
		</form>
	</div>

	<div>
		<h1>Welcome to our real Estate page</h1>
	</div>
	<!--[End] Search input -->
</body>

<!-- Image Scroller Section -->
<div class="scoller">
	<div class="image-container">
		<img id="carouselImage1"
			src="${pageContext.request.contextPath}/resources/images/others/my_house.jpg"
			alt="Image 1">
	</div>
	<div class="image-container">
		<img id="carouselImage2"
			src="${pageContext.request.contextPath}/resources/images/others/my_house2.jpg"
			alt="Image 2">
	</div>
</div>
<br>
<br>
<h1>Real Estates</h1>

<div class="container">



	<div class="cards">

		<c:forEach var="property" items="${propertyLists}">
			<%
			logger.info("in the jstl loop");
			%>

			<section class="card">
							<figure>
								<div class="img-overlay hot-home">
									<img
										src="${pageContext.request.contextPath}/resources/images/property/${property.propertyimageUrlFromPart}"
										alt="Property Image">
									<div class="overlay">
										<a href="#">view property</a>
									</div>
									<div class="cont">
										<div class="icons-img">
											<button>
												<i class="fas fa-heart"></i>
											</button>
											<button>
												<i class="fas fa-share"></i>
											</button>
										</div>
									</div>
								</div>
								<figcaption>${property.property_brief}</figcaption>
							</figure>
							<section class="price"
								style="display: flex; flex-direction: column; justify-content: center; align-items: center; text-align: center;">
								<h1>
									<span style="font-size: 24px;">PRICE</span>
								</h1>
								<span>${property.price}</span>
							</section>
							
							<div class="card-content">
								<p>${property.property_description}.</p>

								<section class="icons-home">
									<div class="name-icon">
										<span>ADDRESS</span>
										<div class="icon">
											<i class="fas fa-map-marker-alt"></i> <span>${property.property_address}</span>
										</div>
									</div>

									<div class="name-icon">
										<span>AREA</span>
										<div class="icon">
											<i class="fas fa-ruler-combined"></i> <span>${property.property_size}</span>
										</div>
									</div>

									<div class="name-icon">
										<span>No of Room</span>
										<div class="icon">
											<i class="fas fa-door-closed"></i> <span>${property.no_of_room}</span>
										</div>
									</div>

									<div class="name-icon">
										<span>No of Bathrooms</span>
										<div class="icon">
											<i class="fas fa-shower"></i> <span>${property.no_of_room}</span>
										</div>
									</div>
									<%-- 			<div class="name-icon">
										<span>No of Room</span>
										<div class="icon">
											<i class="fas fa-bed"></i> <span>${property.no_of_room}</span>
										</div>
									</div>
									<div class="name-icon">
										<span>No of Room</span>
										<div class="icon">
											<i class="fas fa-bed"></i> <span>${property.no_of_room}</span>
										</div>
									</div> --%>
								</section>

							</div>
						</section>
		</c:forEach>




<!-- 
		card two
		<section class="card">
			<figure>
				<div class="img-overlay hot-home">
					<img
						src="https://media-cldnry.s-nbcnews.com/image/upload/t_fit-560w,f_auto,q_auto:best/newscms/2018_30/1355945/home-exterior-today-180726-tease.jpg"
						alt="Trulli">
					<div class="overlay">
						<a href="#">view property</a>
					</div>
					<div class="cont">
						<div class="icons-img">
							<button>
								<i class="fas fa-heart"></i>
							</button>
							<button>
								<i class="fas fa-share"></i>
							</button>
						</div>
					</div>
				</div>
				<figcaption>villa in alexandria</figcaption>
			</figure>
			<div class="card-content">

				<p>enjoy serenity of Deering Bay whole day from this spectacular
					North and..</p>

				<section class="icons-home">
					<div class="name-icon">
						<span>bedrooms</span>
						<div class="icon">
							<i class="fas fa-bed"></i> <span>3</span>
						</div>
					</div>
					<div class="name-icon">
						<span>bathrooms</span>
						<div class="icon">
							<i class="fas fa-sink"></i> <span>3.5</span>
						</div>
					</div>
					<div class="name-icon">
						<span>area</span>
						<div class="icon">
							<i class="fas fa-vector-square"></i> <span>3500</span>
						</div>
					</div>
					<div class="name-icon">
						<span>bedrooms</span>
						<div class="icon">
							<i class="fas fa-bed"></i> <span>3</span>
						</div>
					</div>
					<div class="name-icon">
						<span>bathrooms</span>
						<div class="icon">
							<i class="fas fa-sink"></i> <span>3.5</span>
						</div>
					</div>
					<div class="name-icon">
						<span>area</span>
						<div class="icon">
							<i class="fas fa-vector-square"></i> <span>3500</span>
						</div>
					</div>
				</section>

			</div>
		</section>

		card three
		<section class="card">
			<figure>
				<div class="img-overlay">
					<img
						src="https://media-cldnry.s-nbcnews.com/image/upload/t_fit-560w,f_auto,q_auto:best/newscms/2018_30/1355945/home-exterior-today-180726-tease.jpg"
						alt="Trulli">
					<div class="overlay">
						<a href="#">view property</a>
					</div>
					<div class="cont">
						<div class="icons-img">
							<button>
								<i class="fas fa-heart"></i>
							</button>
							<button>
								<i class="fas fa-share"></i>
							</button>
						</div>
					</div>

				</div>
				<figcaption>Villa on Cairo</figcaption>
			</figure>
			<div class="card-content">
				<p>The vary best waterfront location in Tahrir square and beside
					many cool places</p>

				<section class="icons-home">
					<div class="name-icon">
						<span>bedrooms</span>
						<div class="icon">
							<i class="fas fa-bed"></i> <span>3</span>
						</div>
					</div>
					<div class="name-icon">
						<span>bathrooms</span>
						<div class="icon">
							<i class="fas fa-sink"></i> <span>2</span>
						</div>
					</div>
					<div class="name-icon">
						<span>area</span>
						<div class="icon">
							<i class="fas fa-vector-square"></i> <span>1800</span>
						</div>
					</div>

					<div class="name-icon">
						<span>PRICE</span>
						<div class="icon">
							<i class="fas fa-money-bill"></i> <span>Rs.300</span>
						</div>
					</div>

				</section>
		</section>
		<section class="card">
			<figure>
				<div class="img-overlay">
					<img
						src="https://media-cldnry.s-nbcnews.com/image/upload/t_fit-560w,f_auto,q_auto:best/newscms/2018_30/1355945/home-exterior-today-180726-tease.jpg"
						alt="Trulli">
					<div class="overlay">
						<a href="#">view property</a>
					</div>
					<div class="cont">
						<div class="icons-img">
							<button>
								<i class="fas fa-heart"></i>
							</button>
							<button>
								<i class="fas fa-share"></i>
							</button>
						</div>
					</div>
				</div>
				<figcaption>home in merrick way</figcaption>
			</figure>
			<div class="card-content">
				<p>Enchantine three bedroom, three bath home with spacious oe
					bedroom, one bath...</p>

				<section class="icons-home">
					<div class="name-icon">
						<span>bedrooms</span>
						<div class="icon">
							<i class="fas fa-bed"></i> <span>3</span>
						</div>
					</div>
					<div class="name-icon">
						<span>bathrooms</span>
						<div class="icon">
							<i class="fas fa-sink"></i> <span>3</span>
						</div>
					</div>
					<div class="name-icon">
						<span>area</span>
						<div class="icon">
							<i class="fas fa-vector-square"></i> <span>4300</span>
						</div>
					</div>
					<div class="name-icon">
						<span>bedrooms</span>
						<div class="icon">
							<i class="fas fa-bed"></i> <span>3</span>
						</div>
					</div>
					<div class="name-icon">
						<span>bathrooms</span>
						<div class="icon">
							<i class="fas fa-sink"></i> <span>3.5</span>
						</div>
					</div>
					<div class="name-icon">
						<span>area</span>
						<div class="icon">
							<i class="fas fa-vector-square"></i> <span>3500</span>
						</div>
					</div>
				</section>

			</div>

		</section>
		<section class="card">
			<figure>
				<div class="img-overlay">
					<img
						src="https://media-cldnry.s-nbcnews.com/image/upload/t_fit-560w,f_auto,q_auto:best/newscms/2018_30/1355945/home-exterior-today-180726-tease.jpg"
						alt="Trulli">
					<div class="overlay">
						<a href="#">view property</a>
					</div>
					<div class="cont">
						<div class="icons-img">
							<button>
								<i class="fas fa-heart"></i>
							</button>
							<button>
								<i class="fas fa-share"></i>
							</button>
						</div>
					</div>
				</div>
				<figcaption>home in merrick way</figcaption>
			</figure>
			<div class="card-content">
				<p>Enchantine three bedroom, three bath home with spacious oe
					bedroom, one bath...</p>

				<section class="icons-home">
					<div class="name-icon">
						<span>bedrooms</span>
						<div class="icon">
							<i class="fas fa-bed"></i> <span>3</span>
						</div>
					</div>
					<div class="name-icon">
						<span>bathrooms</span>
						<div class="icon">
							<i class="fas fa-sink"></i> <span>3</span>
						</div>
					</div>
					<div class="name-icon">
						<span>area</span>
						<div class="icon">
							<i class="fas fa-vector-square"></i> <span>4300</span>
						</div>
					</div>
					<div class="name-icon">
						<span>bedrooms</span>
						<div class="icon">
							<i class="fas fa-bed"></i> <span>3</span>
						</div>
					</div>
					<div class="name-icon">
						<span>bathrooms</span>
						<div class="icon">
							<i class="fas fa-sink"></i> <span>3.5</span>
						</div>
					</div>
					<div class="name-icon">
						<span>area</span>
						<div class="icon">
							<i class="fas fa-vector-square"></i> <span>3500</span>
						</div>
					</div>
				</section>
				<section class="price">
					<span>for sale</span></br> <span>$540,000</span>
				</section>
			</div>

		</section>
		<section class="card">
			<figure>
				<div class="img-overlay">
					<img
						src="https://media-cldnry.s-nbcnews.com/image/upload/t_fit-560w,f_auto,q_auto:best/newscms/2018_30/1355945/home-exterior-today-180726-tease.jpg"
						alt="Trulli">
					<div class="overlay">
						<a href="#">view property</a>
					</div>
					<div class="cont">
						<div class="icons-img">
							<button>
								<i class="fas fa-heart"></i>
							</button>
							<button>
								<i class="fas fa-share"></i>
							</button>
						</div>
					</div>
				</div>
				<figcaption>home in merrick way</figcaption>
			</figure>
			<div class="card-content">
				<p>Enchantine three bedroom, three bath home with spacious oe
					bedroom, one bath...</p>

				<section class="icons-home">
					<div class="name-icon">
						<span>bedrooms</span>
						<div class="icon">
							<i class="fas fa-bed"></i> <span>3</span>
						</div>
					</div>
					<div class="name-icon">
						<span>bathrooms</span>
						<div class="icon">
							<i class="fas fa-sink"></i> <span>3</span>
						</div>
					</div>
					<div class="name-icon">
						<span>area</span>
						<div class="icon">
							<i class="fas fa-vector-square"></i> <span>4300</span>
						</div>
					</div>
					<div class="name-icon">
						<span>bedrooms</span>
						<div class="icon">
							<i class="fas fa-bed"></i> <span>3</span>
						</div>
					</div>
					<div class="name-icon">
						<span>bathrooms</span>
						<div class="icon">
							<i class="fas fa-sink"></i> <span>3.5</span>
						</div>
					</div>
					<div class="name-icon">
						<span>area</span>
						<div class="icon">
							<i class="fas fa-vector-square"></i> <span>3500</span>
						</div>
					</div>
				</section>

			</div>

		</section>
		<section class="card">
			<figure>
				<div class="img-overlay hot-home">
					<img
						src="https://media-cldnry.s-nbcnews.com/image/upload/t_fit-560w,f_auto,q_auto:best/newscms/2018_30/1355945/home-exterior-today-180726-tease.jpg"
						alt="Trulli">
					<div class="overlay">
						<a href="#">view property</a>
					</div>
					<div class="cont">
						<div class="icons-img">
							<button>
								<i class="fas fa-heart"></i>
							</button>
							<button>
								<i class="fas fa-share"></i>
							</button>
						</div>
					</div>
				</div>
				<figcaption>home in merrick way</figcaption>
			</figure>
			<div class="card-content">
				<p>Enchantine three bedroom, three bath home with spacious oe
					bedroom, one bath...</p>

				<section class="icons-home">
					<div class="name-icon">
						<span>bedrooms</span>
						<div class="icon">
							<i class="fas fa-bed"></i> <span>3</span>
						</div>
					</div>
					<div class="name-icon">
						<span>bathrooms</span>
						<div class="icon">
							<i class="fas fa-sink"></i> <span>3</span>
						</div>
					</div>
					<div class="name-icon">
						<span>area</span>
						<div class="icon">
							<i class="fas fa-vector-square"></i> <span>4300</span>
						</div>
					</div>
					<div class="name-icon">
						<span>bedrooms</span>
						<div class="icon">
							<i class="fas fa-bed"></i> <span>3</span>
						</div>
					</div>
					<div class="name-icon">
						<span>bathrooms</span>
						<div class="icon">
							<i class="fas fa-sink"></i> <span>3.5</span>
						</div>
					</div>
					<div class="name-icon">
						<span>area</span>
						<div class="icon">
							<i class="fas fa-vector-square"></i> <span>3500</span>
						</div>
					</div>
				</section>

			</div>

		</section> -->
	</div>

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

<jsp:include page="../pages/footer.jsp" />
<%--action tag --%>
	
<script>
    document.addEventListener('DOMContentLoaded', function() {
        const imageElements = document.querySelectorAll('.image-container img');
        const imagePaths = [
            '${pageContext.request.contextPath}/resources/images/others/my_house.jpg',
            '${pageContext.request.contextPath}/resources/images/others/my_house2.jpg',
            '${pageContext.request.contextPath}/resources/images/others/my_house3.jpg'
        ];

        let currentIndex = 0;

        // Function to update image sources
        function updateImages() {
            imageElements.forEach((img, index) => {
                img.src = imagePaths[(currentIndex + index) % imagePaths.length];
            });
            currentIndex = (currentIndex + 1) % imagePaths.length;
        }

        // Automatically update images every 3 seconds
        setInterval(updateImages, 3000);
    });
    
    
    </script>