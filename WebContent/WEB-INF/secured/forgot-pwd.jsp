<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html lang="en">
<head>
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
    <jsp:include page="../reusable/navbar.jsp"/>    
    <div class="col-sm-9 col-lg-10 content admin-area">
      <!-- your page content -->
      <div class="header">
       <h1>SHS Online Library System</h1>
       <h3></h3>
      </div>

      <div class="lesser-padding-content" >
        <h3 align="center">Forgot your Password?</h3>
        <div class="content">
	        <div class="col-md-2"></div>
	        <div class="col-md-8">
	        	<div id="content-enter-username">
		          <div class="form-group">
		            <p align="center">Please enter your ID number so we can email you <br>
		            the confirmation code to the ID number's associated email address.</p>
		            
		            <div class="alert alert-danger" id="invalid-id" style="display: none;" role="alert">ID Number does not exist!</div>
		            
				    <label for="idnumber">ID Number</label>
				    <input type="number" class="form-control form-components-rd" id="id_number" name="id_number" placeholder="ID Number">
				    
				    <button type="submit" id="submit-username" class="btn btn-default submit-btn form-components-rd auto-width erase-margin">Submit</button>
				  </div>
		        </div>
		        
		        <div id="content-enter-secret-answer">
		          <div class="form-group">
		            <h3 align="center">Secret Question</h3>
		            <p align="left">
		            	Q: <span id="secret-question">[secret-question]</span>
		            </p>
		            
		            <div class="alert alert-danger" id="wrong-answer" style="display: none;" role="alert">Wrong Answer!</div>
		            
				    <label for="idnumber">Secret Answer</label>
				    <input type="text" class="form-control form-components-rd" id="idnumber" placeholder="ID Number">
				    
				    <button type="submit" id="submit-answer" class="btn btn-default submit-btn form-components-rd auto-width erase-margin">Submit</button>
				  </div>
		        </div>
	        </div>
	        <div class="col-md-2"></div>
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
<script src="js/app.js"></script>			
<!-- //////////////////// -->
<script>

$(document).ready(function(){
	
	$("#submit-username").click(function(){
		console.log($("#content-enter-username #id_number").val());
		checkUsernameValidity($("#content-enter-username #id_number").val());
		$("#submit-username").show();
		$("#submit-answer").show();
	});
	
});

function showWrongAnswer(){
	$('#wrong-answer').show("fast", function(){});
	  setTimeout(function(){
	      $('#wrong-answer').fadeOut("fast", function(){});  
	    }, 1000);
}

function showInvalidIDNumber(){
	$('#invalid-id').show("fast", function(){});
	  setTimeout(function(){
	      $('#invalid-id').hide("fast", function(){});  
	    }, 1000);
}

function checkUsernameValidity(idnumber){
	console.log(idnumber);
	$.ajax({
		url: "LoadSecretQuestionServlet",
		method: "post",
		data: {
			'id_number': idnumber,
		},
		success: function(question) {
			$("#content-enter-username").hide();
			$("#secret-question").text(question);
		},
	});
}



$("#submit-answer").click(function(){
	
	
	
});

</script>
</body>
</html>
