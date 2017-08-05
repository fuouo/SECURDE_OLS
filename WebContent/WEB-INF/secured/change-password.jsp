<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
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
    <link rel="stylesheet" type="text/css" href="vitalets-bootstrap-datepicker-c7af15b/css/datepicker.css"/>
    <link rel="stylesheet" type="text/css" href="css/content.css"> 
    <link rel="stylesheet" type="text/css" href="css/alert/alert.css"> 
</head>

<body>
<div class="container-fluid">
  <div class="row">
    <jsp:include page="../reusable/navbar.jsp"/>    
    <div class="col-sm-9 col-lg-10 content admin-area">
      <!-- your page content -->
      <div class="header">
       <h1>SHS Online Library System</h1>
       <h2>Manage Books. Add Books. Delete Books. Edit Books.</h2>
       <h3></h3>
      </div>
      
      <div class="lesser-padding-content" >
        <h3 align="center">Change your password</h3>
        <div class="content">
	        <div class="col-md-2"></div>
	        <div class="col-md-8">
	        	<form id="content-enter-username">
		          <div class="form-group">
		            
		            <div class="alert alert-danger" id="invalid-id" style="display: none;" role="alert">Invalid Input!</div>
		            
				    <label for="current_password">Current Password</label>
				    <input type="password" class="form-control form-components-rd" id="current_password" name="current_password" placeholder="Current Password">
				    
				    <label for="new_password">New Password</label>
				    <input type="password" class="form-control form-components-rd" id="new_password" name="new_password" placeholder="Current Password">
				    
				    <label for="re_new_password">Re-Enter New Password</label>
				    <input type="password" class="form-control form-components-rd" id="re_new_password" name="re_new_password" placeholder="Current Password">
				    
				    <button type="submit" id="submit-change-password" class="btn btn-default submit-btn form-components-rd auto-width erase-margin">Submit</button>
				  </div>
		        </form>
		        
		        
	        </div>
	        <div class="col-md-2"></div>
        </div>
      </div>
      
      
      
<!-- insert alerts beyond this point -->
<div id="ohsnap">
</div>

<!-- don't go beyond this point -->
    </div> <!-- end of content -->
  </div> <!-- end of row -->
</div> <!-- end of container-fluid -->

<!--  INSERT SCRIPT TAGS HERE -->
<!-- must be in every page -->
<script src="js/jquery-3.0.0.min.js"></script>
<script src="bootstrap-3.3.7-dist/js/bootstrap.min.js"></script>
<script src="vitalets-bootstrap-datepicker-c7af15b/js/bootstrap-datepicker.js"></script>
<script src="js/app.js"></script>			
<!-- //////////////////// -->


</body>
</html>