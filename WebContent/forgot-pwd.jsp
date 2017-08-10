<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

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
      <h2>Forgot your Password?</h2>
      <div class="row">
        <div class="col-md-6 col-md-offset-3">
          <form class="form-horizontal" id="username" action="LoadSecretQuestionServlet" method = "POST">
              <div class="form-group">
                <label for="login-idnum" class="col-sm-2 control-label">ID Number</label>
                <div class="col-sm-10">
                  <input type="text" name="id_number" class="form-control form-components-rd erase-margin" id="login-idnum" placeholder="ID Number" value="${id_number}">
                </div>
              </div>
              <div class="form-group" align="left">
                <div class="col-sm-offset-2 col-sm-10">
                  <div type="submit" id="verify-username" class="btn btn-default submit-btn form-components-rd auto-width erase-margin">Submit</div>
                </div>
              </div>
			</form>
		<%-- 		<c:set var="id_number1">${id_number}</c:set> --%>
			<form class="form-horizontal" id="secret-question" style="display:none" action="AnswerSecretQuestionServlet" method = "POST">
              <div>
             	 <input type="hidden" name="id_number_ans" id="idnumber2" value="${id_number_ans}">
                <div class="form-group">
                  <label for="login-idnum" class="col-sm-2 control-label">Secret Question</label>
                  <div class="col-sm-10">
                    <div id= "question" class="well">${sqID}</div>
                    <div class="alert alert-danger" style="display: none;" role="alert">Wrong Answer!</div>
                    <input type="text" class="form-control form-components-rd erase-margin" id="login-idnum" placeholder="Your Answer" name="sq_answer">
                  </div>
                </div>
                <div class="form-group" align="left">
                  <div class="col-sm-offset-2 col-sm-10">
                    <button type="submit" id="verify-secret-answer" class="btn btn-default submit-btn form-components-rd auto-width erase-margin">Submit</button>
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

<!--  INSERT SCRIPT TAGS HERE -->
<!-- must be in every page -->
<script src="js/jquery-3.0.0.min.js"></script>
<script src="bootstrap-3.3.7-dist/js/bootstrap.min.js"></script>
<script src="js/app.js" type="text/javascript" charset="utf-8"></script>			
<!-- //////////////////// -->
<script src="js/forgot-pwd.js" type="text/javascript" charset="utf-8"></script>
</body>
</html>
