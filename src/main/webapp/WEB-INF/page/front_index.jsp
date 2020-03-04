<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%
	String path = application.getContextPath() + "/";
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
<head>
	<meta charset="UTF-8">
	<meta name="description" content="">
	<meta http-equiv="X-UA-Compatible" content="IE=edge">
	<meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
	<!-- The above 4 meta tags *must* come first in the head; any other head content must come *after* these tags -->

	<!-- Title  -->
	<title>健康团检</title>

	<!-- Favicon  -->
	<link rel="icon" href=<%=path+"/resources/frontindex/js/img/core-img/favicon.ico"%>>
	<!-- Style CSS -->
	<link rel="stylesheet" href=<%=path+"/resources/frontindex/style.css"%>>
	<script src=<%=path+"/resources/frontindex/js/jquery/jquery-2.2.4.min.js"%>></script>


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
<%--							<a class="navbar-brand" href="index.html"><img src="img/core-img/logo.png" alt="Logo"></a>--%>

							<button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#medilifeMenu" aria-controls="medilifeMenu" aria-expanded="false" aria-label="Toggle navigation"><span class="navbar-toggler-icon"></span></button>

							<div class="collapse navbar-collapse" id="medilifeMenu">
								<!-- Menu Area -->
								<ul class="navbar-nav ml-auto">
									<li class="nav-item">
										<a class="nav-link" href=<%=path%>>登陆</a>
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
	<div class="hero-slides owl-carousel">
		<!-- Single Hero Slide -->
		<div class="single-hero-slide bg-img bg-overlay-white" style="background-image: url(<%=path+"/resources/frontindex/img/bg-img/hero1.jpg"%>);">
			<div class="container h-100">
				<div class="row h-100 align-items-center">
					<div class="col-12">
						<div class="hero-slides-content">
							<h2 data-animation="fadeInUp" data-delay="100ms">我们始终把顾客放在首位<br>让你放心100%</h2>
							<h6 data-animation="fadeInUp" data-delay="400ms">我们的良心，换来病人的信心</h6>
							<!--                                <a href="#" class="btn medilife-btn mt-50" data-animation="fadeInUp" data-delay="700ms">Discover Medifile <span>+</span></a>-->
						</div>
					</div>
				</div>
			</div>
		</div>
		<!-- Single Hero Slide -->
		<div class="single-hero-slide bg-img bg-overlay-white" style="background-image: url(<%=path+"/resources/frontindex/img/bg-img/breadcumb3.jpg"%>);">
			<div class="container h-100">
				<div class="row h-100 align-items-center">
					<div class="col-12">
						<div class="hero-slides-content">
							<h2 data-animation="fadeInUp" data-delay="100ms">您的健康<br>我们的追求</h2>
							<h6 data-animation="fadeInUp" data-delay="400ms">至诚至善，医博惠民</h6>
							<!--                                <a href="#" class="btn medilife-btn mt-50" data-animation="fadeInUp" data-delay="700ms">Discover Medifile <span>+</span></a>-->
						</div>
					</div>
				</div>
			</div>
		</div>
		<!-- Single Hero Slide -->
		<div class="single-hero-slide bg-img bg-overlay-white" style="background-image: url(<%=path+"/resources/frontindex/img/bg-img/breadcumb1.jpg"%>);">
			<div class="container h-100">
				<div class="row h-100 align-items-center">
					<div class="col-12">
						<div class="hero-slides-content">
							<h2 data-animation="fadeInUp" data-delay="100ms">妙手仁心 <br>关爱一生</h2>
							<h6 data-animation="fadeInUp" data-delay="400ms">精于医，惠于民，康于众</h6>
							<!--                                <a href="#" class="btn medilife-btn mt-50" data-animation="fadeInUp" data-delay="700ms">Discover Medifile <span>+</span></a>-->
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
</section>
<!-- ***** Hero Area End ***** -->


<!-- ***** About Us Area Start ***** -->
<section class="medica-about-us-area section-padding-100-20">
	<div class="container">
		<div class="row">
			<div class="col-12 col-lg-4">
				<div class="medica-about-content">
					<h2>不倦不休，为爱奔忙</h2>
					<p>急患者之所急，想患者之所想，帮患者之所需。对待老人，我们是孩子；对待孩子，我们是父母；对待同辈，我们是朋友。</p>
<%--					<a href="#" class="btn medilife-btn mt-50">View the services <span>+</span></a>--%>
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
								<h5>让健康永驻 为生命护航</h5>
<%--								<p>Lorem ipsum dolor sit amet, consecte tuer adipiscing elit, sed diam nonummy nibh euismod tincidunt ut.</p>--%>
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
								<h5>承百年医风 护生命航程</h5>
<%--								<p>Dolor sit amet, consecte tuer elit, sed diam nonummy nibh euismod tincidunt ut ldolore magna.</p>--%>
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
								<h5>树岗位新风 展天使风采</h5>
<%--								<p>Lorem ipsum dolor sit amet, consecte tuer adipiscing elit, sed diam nonummy nibh euismod tincidunt ut.</p>--%>
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
								<h5>但愿人长久 健康永相伴</h5>
<%--								<p>Dolor sit amet, consecte tuer elit, sed diam nonummy nibh euismod tincidunt ut ldolore magna.</p>--%>
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
<%--					<p>Dolor sit amet, consecte tuer adipiscing elit, sed diam nonummy nibh euismod tincidunt.</p>--%>
				</div>
			</div>
			<!-- Single Cool Fact-->
			<div class="col-12 col-sm-6 col-lg-3">
				<div class="single-cool-fact-area text-center mb-100">
					<i class="icon-atoms"></i>
					<h2><span class="counter">23</span>k</h2>
					<h6>Pacients</h6>
<%--					<p>Dolor sit amet, consecte tuer adipiscing elit, sed diam nonummy nibh euismod tincidunt.</p>--%>
				</div>
			</div>
			<!-- Single Cool Fact-->
			<div class="col-12 col-sm-6 col-lg-3">
				<div class="single-cool-fact-area text-center mb-100">
					<i class="icon-microscope"></i>
					<h2><span class="counter">25</span></h2>
					<h6>Specialities</h6>
<%--					<p>Dolor sit amet, consecte tuer adipiscing elit, sed diam nonummy nibh euismod tincidunt.</p>--%>
				</div>
			</div>
			<!-- Single Cool Fact-->
			<div class="col-12 col-sm-6 col-lg-3">
				<div class="single-cool-fact-area text-center mb-100">
					<i class="icon-doctor-1"></i>
					<h2><span class="counter">723</span></h2>
					<h6>Doctors</h6>
<%--					<p>Dolor sit amet, consecte tuer adipiscing elit, sed diam nonummy nibh euismod tincidunt.</p>--%>
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
		<img src=<%=path+"/resources/frontindex/img/bg-img/g1.jpg"%> alt="">
		<div class="view-more-btn">
			<a class="btn gallery-img" href=<%=path+"/resources/frontindex/img/bg-img/g1.jpg"%>></a>
		</div>
	</div>
	<!-- Single Gallery Item -->
	<div class="single-gallery-item">
		<img src=<%=path+"/resources/frontindex/img/bg-img/g2.jpg"%> alt="">
		<div class="view-more-btn">
			<a class="btn gallery-img" href=<%=path+"/resources/frontindex/img/bg-img/g2.jpg"%>></a>
		</div>
	</div>
	<!-- Single Gallery Item -->
	<div class="single-gallery-item">
		<img src=<%=path+"/resources/frontindex/img/bg-img/g3.jpg"%> alt="">
		<div class="view-more-btn">
			<a class="btn gallery-img" href=<%=path+"/resources/frontindex/img/bg-img/g3.jpg"%>></a>
		</div>
	</div>

	<!-- Single Gallery Item -->
	<div class="single-gallery-item">
		<img src=<%=path+"/resources/frontindex/img/bg-img/g4.jpg"%> alt="">
		<div class="view-more-btn">
			<a class="btn gallery-img href=<%=path+"/resources/frontindex/img/bg-img/g1.jpg"%>"></a>
		</div>
	</div>
</div>
<!-- ***** Gallery Area End ***** -->


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
<%--							<p>健康团检，伴你每天</p>--%>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
</footer>
<!-- ***** Footer Area End ***** -->


<script src=<%=path+"/resources/frontindex/js/popper.min.js"%>></script>
<script src=<%=path+"/resources/frontindex/js/bootstrap.min.js"%>></script>
<script src=<%=path+"/resources/frontindex/js/plugins.js"%>></script>
<script src=<%=path+"/resources/frontindex/js/active.js"%>></script>

</body>

</html>
