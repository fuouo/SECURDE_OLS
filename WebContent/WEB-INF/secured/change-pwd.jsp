<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<html lang="en">
<head>

	<meta http-equiv="refresh" content="600;url=./SignOutServlet">
	
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta name="description" content="SHS Library Books and Meeting Room Reservations">
    <title>Lib.U</title>
    
    <!--online resources-->
    <link href="https://fonts.googleapis.com/css?family=Varela+Round" rel="stylesheet"> <!-- font -->
    
    <!--local resources-->
    	<!-- API -->
    <link rel="stylesheet" type="text/css" href="bootstrap-3.3.7-dist/css/bootstrap.min.css"> 
    <link rel="stylesheet" type="text/css" href="css/navbar-fixed-side.css" />
    	<!-- SELF -->
    <link rel="stylesheet" type="text/css" href="css/navbar-redesigned.css"/>
    <link rel="stylesheet" type="text/css" href="img/icons_by_freepik/font/flaticon.css"> 
    <link rel="stylesheet" type="text/css" href="css/content.css"> 
</head>

<body>
<div class="container-fluid">
  <div class="row">
    <!-- NAV BAR -->
    <jsp:include page="/WEB-INF/reusable/navbar.jsp"/>    
    <!-- END OF NAV BAR -->
    <div class="col-sm-9 col-lg-10 content">
      <!-- your page content -->
      <div class="header">
       <h1>SHS Online Library System</h1>
       <h2>Reserve Books and Meeting Rooms anytime, anywhere!</h2>
      </div>

      <div id="overlay-screen" style="display: none;"></div>
      <!-- SEARCH BAR -->
      <jsp:include page="/WEB-INF/reusable/search-bar-toggable.jsp"/>    
      <!-- END OF SEARCH BAR -->  
      
      <div align="center" id="content-forgotten">
      <h2>Change Password</h2>
      <div class="row">
        <div class="col-md-6 col-md-offset-3">
			<form class="form-horizontal" id="chnge-password" action="PasswordChangeServlet" method = "POST">
              <div>
             	 <input type="hidden" name="id_number" id="idnumber" value="${id_number}">
                
                  <div class="col-sm-10">
                  
                    <div class="alert alert-danger" style="display: none;" role="alert">Password does not match!</div>
                    <br>
                    	<label for="password">New Password</label>
                    	
                    	<div class="row" align="left">
				          <label for="password" class="form-label col-sm-2">Password</label>
			               <input placeholder="New Password ..." class="form-control form-components-rd" type="password" id="password" name="passwordHash" style="width:70%" required/>
		          		   <span id="passstrength"></span>
		          		   <span id="passwordError"></span>
			            </div>
			            
                    	 <div id="password-match" class="alert alert-danger" style="display: none;" role="alert">Password Do Not Match</div>
                    	<label for="confirm_password" >Confirm New Password</label>
                    	<input type="password" class="form-control form-components-rd" name="confirm_passwordHash" id="confirm_password" placeholder="Confrim New Password" style="width:70%" required>
                  		
                  
                  <label for="password-rules">Password Rules:</label>
		          	<ul>
		          		<li>Must have at least 8 characters long</li>
		          		<li>Must have at least one capital letter</li>
		          		<li>Must have at least one number</li>
		          		<li>Must have at least one special character</li>
		          	</ul>
                  
                  </div>
                <div class="form-group" align="left">
                  <div class="col-sm-offset-2 col-sm-10">
                    <button type="submit" id="verify-new-password" class="btn btn-default submit-btn form-components-rd auto-width erase-margin">Submit</button>
                  </div>
                </div>
              </div>
            </form>
        </div>
      </div>

      </div>



<!-- don't go beyond this point -->
    </div> <!-- end of content -->
  </div> <!-- end of row -->
</div> <!-- end of container-fluid -->
<form id="meetingRoomForm" action="MeetingRoomPageServlet" method="post"></form>
<form id="signInForm" action="SignInSignUpPageServlet" method="post"></form>
<form id="homeForm" action="HomePageServlet" method="post"></form>
<form id="yourReservationsForm" action="CalendarOrgRepServlet" method="post"></form>

<!--  INSERT SCRIPT TAGS HERE -->
<!-- must be in every page -->
<script src="js/jquery-3.0.0.min.js"></script>
<script src="bootstrap-3.3.7-dist/js/bootstrap.min.js"></script>
<script src="js/menu-links.js"></script>
<script src="js/app.js" type="text/javascript" charset="utf-8"></script>			
<!-- //////////////////// -->
<script src="js/password-meter.js" type="text/javascript" charset="utf-8"></script>
<script src="js/input-validation.js" type="text/javascript" charset="utf-8"></script>
<script src="js/change-password.js" type="text/javascript" charset="utf-8"></script>

<script>

</script>
</body>
</html>
