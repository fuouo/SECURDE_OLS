<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<c:if test = "${first_namelast_name != 'Sign In'}">
		<meta http-equiv="refresh" content="900;url=./SignOutServlet">
	</c:if>

    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta name="description" content="SHS Library Books and Meeting Room Reservations">
    <title>Lib.U</title>
    
    <!--online resources-->
    <link href="https://fonts.googleapis.com/css?family=Varela+Round" rel="stylesheet"> <!-- font -->
    
    <!--local resources-->
    <!-- apis -->
    <link rel="stylesheet" type="text/css" href="bootstrap-3.3.7-dist/css/bootstrap.min.css"> 
    <link rel="stylesheet" type="text/css" href="css/navbar-fixed-side.css" />
    <link rel="stylesheet" type="text/css" href="css/navbar-redesigned.css"/>
    <link rel="stylesheet" type="text/css" href="img/icons_by_freepik/font/flaticon.css"> 
    <link rel="stylesheet" type="text/css" href="css/content.css"> 
</head>

<body>
<div class="container-fluid">
  <div class="row">
  	<!--  MENU -->
    <jsp:include page="../reusable/navbar.jsp"/>
    <!--  END MENU -->
    <div class="col-sm-9 col-lg-10 content">
      <!-- your page content -->
      <div class="header">
       <h1>SHS Online Library System</h1>
       <h2>Reserve Books and Meeting Rooms anytime, anywhere!</h2>
      </div>

      <div id="overlay-screen" style="display: none;"></div>

      <div id="confirm-reservation" class="pop-up" style="display: none;">
        <h3 align="center">Confirm Reservation?</h3>
        <div align="center" class="divider"></div>

        <h3>Reserving <a href="rm-details.html" class="title">Title</a></h3>

        <div style="padding: 0 20%;" align="left">
          <b>Reservation Date</b>: <span class="confirm-reserve-date">07 / 02 / 2017</span><br>
          <b>Anticipated Return Date</b>: <span class="confirm-return-date">07 / 09 / 2017</span>
        </div>

        <br>

        <div class="row">
          <div class="col-md-1"></div>
          <div class="col-md-2"></div>
          <div class="col-md-2"><button id="submit-reserve" class="btn btn-default">Confirm</button></div>
          <div class="col-md-1"></div>
          <div class="col-md-2"><button id="cancel-reserve" class="btn btn-default">Cancel</button></div>
          <div class="col-md-2"></div>
          <div class="col-md-1"></div>

        </div>
      </div>

      <div class="rm-results">
      <b class="results-found" style="margin: -1px;" >${numOfRM} results found</b>
      <c:forEach items="${reading_material}" var = "i" >
      <!-- START -->
      <div class="row rm-gen-details">
        <div class="col-md-2">
	        <img src="img/book_placeholder.jpg" class="rm-img" width="100%" style="margin: auto 0;"></div>
	        <div class="col-md-10 rm-information">
	        <c:set var="rmID">${i.RMID_Location}</c:set>
	          <a onclick="view_details('${rmID}')" class="title"><b>${i.title}</b></a>
	          <span class="author">${i.author}</span>
	          <span class="pub-info">${i.publisher}</span>
	          <br>
	          
	          <div class="row">
	            <b>
	            <div class="col-md-4"><u>Location</u></div>
	            <div class="col-md-4"><u>Status</u></div></b>
	            <div class="col-md-4"></div>
	          </div>
	          <div class="location-status">
	            <div class="row">
	              <div class="col-md-4"><span class="location">${i.RMID_Location}</span></div>
	              <c:choose>
		              <c:when test="${i.status == 'AVAILABLE'}">
		              	<div class="col-md-4"><span class="available-status">${i.status}</span></div>
		              </c:when>
		              <c:when test="${i.status == 'RESERVED'}">
		              	<div class="col-md-4"><span class="reserved-status">${i.status}</span></div>
		              </c:when>
		              <c:when test="${i.status == 'BORROWED'}">
		              	<div class="col-md-4"><span class="borrowed-status">${i.status}</span></div>
		              </c:when>
	              </c:choose>
	              <c:set var="status">${i.status}</c:set>
	              <c:choose>
	              <c:when test = "${user_type == 'STUDENT' or user_type == 'FACULTY'}">
		        	<c:choose>
					    <c:when test="${status== 'AVAILABLE' or status == 'BORROWED'}">
					    	<c:set var="loc">${i.RMID_Location}</c:set>
					        <div class="col-md-4">
					       		<button id="${i.RMID_Location}" class="reserve-inline btn btn-default">Reserve</button>
					       	</div>
					    </c:when>    
					    <c:otherwise>
					        <div class="col-md-4"><button class="reserve-inline btn btn-default disabled">Reserve</button></div>
					    </c:otherwise>
				 	</c:choose>
			      </c:when>
			      <c:otherwise> <!--  if moderator, shouldn't be able to reserve -->
			      	<div class="col-md-4"></div>
			      </c:otherwise>
				  </c:choose>
	              
	              
	            </div>
	          </div>
          </div>
          </div>
        </c:forEach>
        
		<!-- END -->
      </div>


	<!-- don't go beyond this point -->
    </div> <!-- end of content -->
  </div> <!-- end of row -->
</div> <!-- end of container-fluid -->
<form id="signInFirstForm" action="sign_in_sign_up.jsp" method="post"></form>
<form id="reserveForm" action="ReserveRMServlet" method="post">
		 <input type = "hidden" name="locationID" id="locationID" value="hehehe">
</form>
<form id="viewDetailsForm" action="RMDetailsServlet" method="post">
		 <input type = "hidden" name="rmID_location" id="rmID_location" value="hehehe">
</form>

<!--  INSERT SCRIPT TAGS HERE -->
<!-- must be in every page -->
<script src="js/jquery-3.0.0.min.js"></script>
<script src="bootstrap-3.3.7-dist/js/bootstrap.min.js"></script>
<script src="js/app.js" type="text/javascript" charset="utf-8"></script>			
<!-- //////////////////// -->
<script src="js/reserve-rm.js" type="text/javascript" charset="utf-8"></script>
<script src="js/rmdetails.js" type="text/javascript" charset="utf-8"></script>

</body>
</html>
