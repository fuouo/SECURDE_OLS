<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
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
       <h2>Reserve Books and Meeting Rooms any time, anywhere!</h2>
      </div>


      <div class="rm-results">
      <b id="results-found" style="margin: -1px;" >${numOfauthors} results found</b>
      <c:forEach items="${author}" var = "i" >
      <!-- START -->
      <div class="row rm-gen-details">
      	<div class="col-md-10"><div class="author-name">${i}</div></div>
      	<div class="col-md-2">      	
      	<button class="reserve-inline search-inline btn btn-default">Search Books</button>
      	</div>
      </div>
      <br>
      </c:forEach>
	  <!-- END -->
      </div>
      
      
      <form id="GoToCategory" method="post" action="DisplayCategoryServlet">
      	<input type="hidden" id="RMType" name="RMType" value="ALL"/>
      	<input type="hidden" id="reading_material" name="reading_material" value=""/>
      	<input type="hidden" id ="RMFilter" name="RMFilter" value="AUTHOR"/>
      </form>
     
	<!-- don't go beyond this point -->
    </div> <!-- end of content -->
  </div> <!-- end of row -->
</div> <!-- end of container-fluid -->

<!--  INSERT SCRIPT TAGS HERE -->
<!-- must be in every page -->
<script src="js/jquery-3.0.0.min.js"></script>
<script src="bootstrap-3.3.7-dist/js/bootstrap.min.js"></script>
<script src="js/menu-links.js"></script>
<script src="js/app.js"></script>			
<!-- //////////////////// -->
<script src="js/display-categories.js"></script>

</body>
</html>
