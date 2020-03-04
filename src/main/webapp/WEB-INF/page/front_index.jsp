<%@ page language="java" import="java.util.*" pageEncoding="utf-8" %>
<%
	String path = application.getContextPath();
%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
	<meta charset="UTF-8">
	<meta name="description" content="">
	<meta http-equiv="X-UA-Compatible" content="IE=edge">
	<meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
	<!-- The above 4 meta tags *must* come first in the head; any other head content must come *after* these tags -->

	<!-- Title  -->
	<title>Home</title>

	<!-- Favicon  -->
	<link rel="icon" href="img/core-img/favicon.ico">
	<!-- Style CSS -->
	<link rel="stylesheet" href=<%=path+"/resources/frontindex/style.css"%>>



</head>

<body>
<!-- Preloader -->
<div id="preloader">
	<div class="medilife-load"></div>
</div>

<!-- ***** Header Area Start ***** -->
<header class="header-area">
	<!-- Top Header Area -->
	<div class="top-header-area">
		<div class="container h-100">
			<div class="row h-100">
				<div class="col-12 h-100">
					<div class="h-100 d-md-flex justify-content-between align-items-center">
						<p>欢迎来到 <span>健康团检</span> 系统</p>
						<p>营业时间 : 8：00 - 22：00  联系电话 : <span>+86-800-600-1234</span></p>
					</div>
				</div>
			</div>
		</div>
	</div>

	<!-- Main Header Area -->
	<div class="main-header-area" id="stickyHeader">
		<div class="container h-100">
			<div class="row h-100 align-items-center">
				<div class="col-12 h-100">
					<div class="main-menu h-100">
						<nav class="navbar h-100 navbar-expand-lg">
							<!-- Logo Area  -->
							<a class="navbar-brand" href="index.html"><img src="img/core-img/logo.png" alt="Logo"></a>

							<button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#medilifeMenu" aria-controls="medilifeMenu" aria-expanded="false" aria-label="Toggle navigation"><span class="navbar-toggler-icon"></span></button>

							<div class="collapse navbar-collapse" id="medilifeMenu">
								<!-- Menu Area -->
								<ul class="navbar-nav ml-auto">
									<li class="nav-item active">
										<a class="nav-link" href="index.html">主页 <span class="sr-only">(current)</span></a>
									</li>
									<li class="nav-item">
										<a class="nav-link" href="about-us.html">登陆</a>
									</li>
								</ul>
								<!-- Appointment Button -->
								<!--                                    <a href="#" class="btn medilife-appoint-btn ml-30">For <span>emergencies</span> Click here</a>-->
							</div>
						</nav>
					</div>
				</div>
			</div>
		</div>
	</div>
</header>
<!-- ***** Header Area End ***** -->

<!-- ***** Hero Area Start ***** -->
<section class="hero-area">
<%--	<div class="hero-slides owl-carousel">--%>
<%--		<!-- Single Hero Slide -->--%>
<%--		<div class="single-hero-slide bg-img bg-overlay-white" style="background-image: url(img/bg-img/hero1.jpg);">--%>
<%--			<div class="container h-100">--%>
<%--				<div class="row h-100 align-items-center">--%>
<%--					<div class="col-12">--%>
<%--						<div class="hero-slides-content">--%>
<%--							<h2 data-animation="fadeInUp" data-delay="100ms">Medical Services that <br>You can Trust 100%</h2>--%>
<%--							<h6 data-animation="fadeInUp" data-delay="400ms">Lorem ipsum dolor sit amet, consectetuer adipiscing elit sed diam nonummy nibh euismod.</h6>--%>
<%--							<!--                                <a href="#" class="btn medilife-btn mt-50" data-animation="fadeInUp" data-delay="700ms">Discover Medifile <span>+</span></a>-->--%>
<%--						</div>--%>
<%--					</div>--%>
<%--				</div>--%>
<%--			</div>--%>
<%--		</div>--%>
<%--		<!-- Single Hero Slide -->--%>
<%--		<div class="single-hero-slide bg-img bg-overlay-white" style="background-image: url(img/bg-img/breadcumb3.jpg);">--%>
<%--			<div class="container h-100">--%>
<%--				<div class="row h-100 align-items-center">--%>
<%--					<div class="col-12">--%>
<%--						<div class="hero-slides-content">--%>
<%--							<h2 data-animation="fadeInUp" data-delay="100ms">Medical Services that <br>You can Trust 100%</h2>--%>
<%--							<h6 data-animation="fadeInUp" data-delay="400ms">Lorem ipsum dolor sit amet, consectetuer adipiscing elit sed diam nonummy nibh euismod.</h6>--%>
<%--							<!--                                <a href="#" class="btn medilife-btn mt-50" data-animation="fadeInUp" data-delay="700ms">Discover Medifile <span>+</span></a>-->--%>
<%--						</div>--%>
<%--					</div>--%>
<%--				</div>--%>
<%--			</div>--%>
<%--		</div>--%>
<%--		<!-- Single Hero Slide -->--%>
<%--		<div class="single-hero-slide bg-img bg-overlay-white" style="background-image: url(img/bg-img/breadcumb1.jpg);">--%>
<%--			<div class="container h-100">--%>
<%--				<div class="row h-100 align-items-center">--%>
<%--					<div class="col-12">--%>
<%--						<div class="hero-slides-content">--%>
<%--							<h2 data-animation="fadeInUp" data-delay="100ms">Medical Services that <br>You can Trust 100%</h2>--%>
<%--							<h6 data-animation="fadeInUp" data-delay="400ms">Lorem ipsum dolor sit amet, consectetuer adipiscing elit sed diam nonummy nibh euismod.</h6>--%>
<%--							<!--                                <a href="#" class="btn medilife-btn mt-50" data-animation="fadeInUp" data-delay="700ms">Discover Medifile <span>+</span></a>-->--%>
<%--						</div>--%>
<%--					</div>--%>
<%--				</div>--%>
<%--			</div>--%>
<%--		</div>--%>
<%--	</div>--%>
</section>
<!-- ***** Hero Area End ***** -->


<!-- ***** About Us Area Start ***** -->
<section class="medica-about-us-area section-padding-100-20">
	<div class="container">
		<div class="row">
			<div class="col-12 col-lg-4">
				<div class="medica-about-content">
					<h2>We always put our pacients first</h2>
					<p>Lorem ipsum dolor sit amet, consectetuer adipiscing elit, sed diam nonummy nibh euismod tincidunt ut laoreet dolore magna aliquam erat volutpat. Lorem ipsum dolor sit amet, consectetuer adipiscing eli.</p>
					<a href="#" class="btn medilife-btn mt-50">View the services <span>+</span></a>
				</div>
			</div>
			<div class="col-12 col-lg-8">
				<div class="row">
					<!-- Single Service Area -->
					<div class="col-12 col-sm-6">
						<div class="single-service-area d-flex">
							<div class="service-icon">
								<i class="icon-doctor"></i>
							</div>
							<div class="service-content">
								<h5>The Best Doctors</h5>
								<p>Lorem ipsum dolor sit amet, consecte tuer adipiscing elit, sed diam nonummy nibh euismod tincidunt ut.</p>
							</div>
						</div>
					</div>
					<!-- Single Service Area -->
					<div class="col-12 col-sm-6">
						<div class="single-service-area d-flex">
							<div class="service-icon">
								<i class="icon-blood-donation-1"></i>
							</div>
							<div class="service-content">
								<h5>Baby Nursery</h5>
								<p>Dolor sit amet, consecte tuer elit, sed diam nonummy nibh euismod tincidunt ut ldolore magna.</p>
							</div>
						</div>
					</div>
					<!-- Single Service Area -->
					<div class="col-12 col-sm-6">
						<div class="single-service-area d-flex">
							<div class="service-icon">
								<i class="icon-flask-2"></i>
							</div>
							<div class="service-content">
								<h5>Laboratory</h5>
								<p>Lorem ipsum dolor sit amet, consecte tuer adipiscing elit, sed diam nonummy nibh euismod tincidunt ut.</p>
							</div>
						</div>
					</div>
					<!-- Single Service Area -->
					<div class="col-12 col-sm-6">
						<div class="single-service-area d-flex">
							<div class="service-icon">
								<i class="icon-emergency-call-1"></i>
							</div>
							<div class="service-content">
								<h5>Emergency Room</h5>
								<p>Dolor sit amet, consecte tuer elit, sed diam nonummy nibh euismod tincidunt ut ldolore magna.</p>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
</section>
<!-- ***** About Us Area End ***** -->

<!-- ***** Cool Facts Area Start ***** -->
<section class="medilife-cool-facts-area section-padding-100-0">
	<div class="container">
		<div class="row">
			<!-- Single Cool Fact-->
			<div class="col-12 col-sm-6 col-lg-3">
				<div class="single-cool-fact-area text-center mb-100">
					<i class="icon-blood-transfusion-2"></i>
					<h2><span class="counter">5632</span></h2>
					<h6>Blood donations</h6>
					<p>Dolor sit amet, consecte tuer adipiscing elit, sed diam nonummy nibh euismod tincidunt.</p>
				</div>
			</div>
			<!-- Single Cool Fact-->
			<div class="col-12 col-sm-6 col-lg-3">
				<div class="single-cool-fact-area text-center mb-100">
					<i class="icon-atoms"></i>
					<h2><span class="counter">23</span>k</h2>
					<h6>Pacients</h6>
					<p>Dolor sit amet, consecte tuer adipiscing elit, sed diam nonummy nibh euismod tincidunt.</p>
				</div>
			</div>
			<!-- Single Cool Fact-->
			<div class="col-12 col-sm-6 col-lg-3">
				<div class="single-cool-fact-area text-center mb-100">
					<i class="icon-microscope"></i>
					<h2><span class="counter">25</span></h2>
					<h6>Specialities</h6>
					<p>Dolor sit amet, consecte tuer adipiscing elit, sed diam nonummy nibh euismod tincidunt.</p>
				</div>
			</div>
			<!-- Single Cool Fact-->
			<div class="col-12 col-sm-6 col-lg-3">
				<div class="single-cool-fact-area text-center mb-100">
					<i class="icon-doctor-1"></i>
					<h2><span class="counter">723</span></h2>
					<h6>Doctors</h6>
					<p>Dolor sit amet, consecte tuer adipiscing elit, sed diam nonummy nibh euismod tincidunt.</p>
				</div>
			</div>
		</div>
	</div>
</section>
<!-- ***** Cool Facts Area End ***** -->

<!-- ***** Gallery Area Start ***** -->
<div class="medilife-gallery-area owl-carousel">
	<!-- Single Gallery Item -->
	<div class="single-gallery-item">
		<img src="img/bg-img/g1.jpg" alt="">
		<div class="view-more-btn">
			<a href="img/bg-img/g1.jpg" class="btn gallery-img">See More +</a>
		</div>
	</div>
	<!-- Single Gallery Item -->
	<div class="single-gallery-item">
		<img src="img/bg-img/g2.jpg" alt="">
		<div class="view-more-btn">
			<a href="img/bg-img/g2.jpg" class="btn gallery-img">See More +</a>
		</div>
	</div>
	<!-- Single Gallery Item -->
	<div class="single-gallery-item">
		<img src="img/bg-img/g3.jpg" alt="">
		<div class="view-more-btn">
			<a href="img/bg-img/g3.jpg" class="btn gallery-img">See More +</a>
		</div>
	</div>

	<!-- Single Gallery Item -->
	<div class="single-gallery-item">
		<img src="img/bg-img/g4.jpg" alt="">
		<div class="view-more-btn">
			<a href="img/bg-img/g4.jpg" class="btn gallery-img">See More +</a>
		</div>
	</div>
</div>
<!-- ***** Gallery Area End ***** -->


<!-- ***** Blog Area End ***** -->

<!-- ***** Footer Area Start ***** -->
<footer class="footer-area section-padding-100">
	<!-- Bottom Footer Area -->
	<div class="bottom-footer-area">
		<div class="container-fluid">
			<div class="row">
				<div class="col-12">
					<div class="bottom-footer-content">
						<!-- Copywrite Text -->
						<div class="copywrite-text">
							<p>Copyright &copy; 2018.Company name All rights reserved</p>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
</footer>
<!-- ***** Footer Area End ***** -->

<!-- jQuery (Necessary for All JavaScript Plugins) -->
<%--<script src="js/jquery/jquery-2.2.4.min.js"></script>--%>
<!-- Popper js -->
<%--<script src="js/popper.min.js"></script>--%>
<%--<!-- Bootstrap js -->--%>
<%--<script src="js/bootstrap.min.js"></script>--%>
<%--<!-- Plugins js -->--%>
<%--<script src="js/plugins.js"></script>--%>
<%--<!-- Active js -->--%>
<%--<script src="js/active.js"></script>--%>
<script src=<%=path+"/resources/frontindex/js/jquery/jquery-2.2.4.min.js"%>></script>
<script src=<%=path+"/resources/frontindex/js/popper.min.js"%>></script>
<script src=<%=path+"/resources/frontindex/js/bootstrap.min.js"%>></script>
<script src=<%=path+"/resources/frontindex/js/plugins.js"%>></script>
<script src=<%=path+"/resources/frontindex/js/active.js"%>></script>


</body>

</html>
