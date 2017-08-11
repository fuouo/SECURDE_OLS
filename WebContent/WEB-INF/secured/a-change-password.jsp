<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html lang="en">
<head>

	<meta http-equiv="refresh" content="600;url=./SignOutServlet">
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
    <link rel="stylesheet" type="text/css" href="img/icons_by_freepik/font/flaticon.css"> 
    <link rel="stylesheet" type="text/css" href="vitalets-bootstrap-datepicker-c7af15b/css/datepicker.css"/>
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
       <h2>Manage Books. Add Books. Delete Books. Edit Books.</h2>
       <h3></h3>
      </div>
      
      
      
      <br><br><br>
      
      <!-- SEARCH BAR -->
      <jsp:include page="../reusable/search-bar.jsp"/>    
      <!-- END OF SEARCH BAR -->

      <div id="content-add-book" class="lesser-padding-content toggable" >
        <h3 align="center">Change Password</h3>
        <div class="content">
        <form id="ChangePasswordServlet" method="post" action="ChangePasswordServlet">
          
          <div id="secret-question" style="display:none;">
            <div class="form-group">
              <label for="login-idnum" class="col-sm-2 control-label">Secret Question</label>
              <div class="col-sm-10">
                <div class="well">Where did you spend your 10th birthday?</div>
                <div class="alert alert-danger" style="display:none;" role="alert">Wrong Answer!</div>
                <input type="text" class="form-control form-components-rd erase-margin" id="login-idnum" placeholder="Your Answer">
              </div>
            </div>
            <div class="form-group" align="left">
              <div class="col-sm-offset-2 col-sm-10">
                <div type="submit" id="verify-secret-answer" class="btn btn-default submit-btn form-components-rd auto-width erase-margin">Submit</div>
              </div>
            </div>
          </div>
          
          <label for="new-password">New Password</label>
          <input type="password" class="form-control form-components-rd" name="current-password" id="current-password" placeholder="Current Password" style="width:70%" required>
        	
        </form>
        </div>
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
<script src="js/register.js" type="text/javascript" charset="utf-8"></script>
<script src="js/manage-books.js" type="text/javascript" charset="utf-8"></script>


</body>
</html>