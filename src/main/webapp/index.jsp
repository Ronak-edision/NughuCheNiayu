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
<title>NughuCheNiyau</title>
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/stylesheets/index.css">

<link rel="stylesheet"
	href="${pageContext.request.contextPath}/stylesheets/footer.css">

<link rel="stylesheet"
	href="${pageContext.request.contextPath}/stylesheets/productPage.css">

<link rel="stylesheet"
	href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.0.0-beta3/css/all.min.css">


<link rel="preconnect" href="https://fonts.googleapis.com">
<link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
<link
	href="https://fonts.googleapis.com/css2?family=Ubuntu:wght@300;400;500;700&display=swap"
	rel="stylesheet">
<link href="https://fonts.googleapis.com/icon?family=Material+Icons"
	rel="stylesheet">
</head>
<body>
	<main>

		<jsp:include page="pages/navbar.jsp" />
		<%--action tag --%>


		<div class="home">

			<div class="home-image">
				<div class="home-text">
					<h1>investing in your one home at a time</h1>
					<p>
						<span style="color: rgb(230, 255, 5);">Write Something
							quote like</span>
					</p>
					<a href="#">get started</a>
				</div>
			</div>

			<c:choose>
				<c:when test="${empty requestScope.source}">
					<p>Directly opened index.jsp</p>
				</c:when>
				<c:otherwise>
					<p>Index.jsp redirected from ${requestScope.source}</p>
				</c:otherwise>
			</c:choose>

			<!-- Continue with your property display logic -->

			<!-- <div class="home-bottom1">
				<div class="container" style="display: flex; justify-content: space-between; align-items: center; flex-wrap: wrap; gap: 2rem; width: 100%; transform: translate(0, -4.5rem); border-radius: 1.5rem; box-shadow: 2px 2px 35px #161c8a3c; padding: 2.5rem 1rem; width: 88vw; margin: 0 auto;">
					<div class="box">
						<h5>location</h5>
						<select>
							<option value="Mangal Bazar">Mangal Bazar</option>
							<option value="Kumari pati">Kumari pati</option>
							<option value="usa">Patan Dhoka</option>
							Add more options as needed
						</select>
					</div>
					<div class="box">
						<h5>property type</h5>
						<select>
							<option value="duplex">Duplex</option>
							<option value="apartment">Apartment</option>
							<option value="villa">Villa</option>
							Add more options as needed
						</select>
					</div>
					<div class="box">
						<h5>budget</h5>
						<select>
							<option value="12000">RS 12,000</option>
							<option value="20000">RS 20,000</option>
							<option value="50000">RS 50,000</option>
							Add more options as needed
						</select>
					</div>
					<div class="box">
						<a href="#">search property</a>
					</div>
				</div>
			</div> -->
		</div>

		<div class="con-section">
			<div class="contact-us">
				<div class="text">
					<h2>navigation your real estate journey</h2>
					<p>Write something about benefits of buying house</p>
					<!-- <a href="#">contact us</a> -->
				</div>

				<div class="box">
					<div class="image">
						<img
							src="${pageContext.request.contextPath}/resources/images/others/modern-business-building-with-glass-wall-from-empty-floor.jpg"
							alt="">
					</div>

					<div class="s-box">
						<h3>22k+</h3>
						<p>Property Ready to buy and sell</p>
					</div>
				</div>
			</div>
		</div>

		<div class="convenience">
			<div class="provided">
				<div class="box">
					<h2>11K+</h2>
					<p>happy customers with our services</p>
				</div>
				<div class="box">
					<h2>22K+</h2>
					<p>the best property Will provide</p>
				</div>
				<div class="box">
					<h2>520+</h2>
					<p>awesome companies belive in us</p>
				</div>
			</div>
			<div class="content">
				<div class="box">
					<h2>where comfort meets convenience</h2>
				</div>

				<div class="box">
					<p>Write Something about company</p>
				</div>
			</div>

			<div class="video">
				<video controls muted autoplay loop
					src="${pageContext.request.contextPath}/resources/images/others/java_video.mp4"></video>
			</div>
		</div>




		<div class="property">
			<div class="prop-h">
				<h2>our popular property</h2>
				<p>Write something</p>
			</div>

			<div class="container">
				<div class="cards">



					<c:forEach var="property" items="${propertyLists}">
						<%
						logger.info("in the jstl loop the property list is: ");
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
											</button>S
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


				</div>
			</div>
			<div class="explore">
				<a href="#">explore all property</a>
			</div>
		</div>
	</main>
	<jsp:include page="pages/footer.jsp" />
	<%--action tag --%>


	<script
		src="https://cdnjs.cloudflare.com/ajax/libs/gsap/3.12.3/gsap.min.js"
		integrity="sha512-7Au1ULjlT8PP1Ygs6mDZh9NuQD0A5prSrAUiPHMXpU6g3UMd8qesVnhug5X4RoDr35x5upNpx0A6Sisz1LSTXA=="
		crossorigin="anonymous" referrerpolicy="no-referrer"></script>
	<script
		src="https://cdnjs.cloudflare.com/ajax/libs/gsap/3.12.3/ScrollTrigger.min.js"
		integrity="sha512-LQQDtPAueBcmGXKdOBcMkrhrtqM7xR2bVrnMtYZ8ImAZhFlIb5xrMqQ6uZviyeSB+4mYj89ta8niiCIQM1Gj0w=="
		crossorigin="anonymous" referrerpolicy="no-referrer"></script>
	<script src="script.js"></script>
</body>
</html>