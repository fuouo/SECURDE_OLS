<%@ page language="java" contentType="text/html; charset=ISO-8859-1"    pageEncoding="ISO-8859-1"%>
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

<form id="deleteRMForm" action="DeleteRMServlet" method="post"></form>
<!--  TODO: Fix the confirm-override popup pls. Convert to cancellation of reservationss-->
<div id="overlay-screen" style="display: none;"></div>
<div id="confirm-override" class="pop-up" style="display: none;">
  <h3 align="center">Confirm Override?</h3>
  <div align="center" class="divider"></div>
  <h3>Cancelling Reservation of </h3>
  <h4><a href="rm-details.html" class="title">Title</a></h4>
  <br><br><br><br>
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

<div class="container-fluid">
  <div class="row">
    <div class="col-sm-3 col-lg-2">
      <nav class="navbar navbar-default navbar-fixed-side">
        <div class="container">
          <div class="navbar-header">
            <button class="navbar-toggle" data-target=".navbar-collapse" data-toggle="collapse">
              <span class="sr-only">Toggle navigation</span>
              <span class="icon-bar"></span>
              <span class="icon-bar"></span>
              <span class="icon-bar"></span>
            </button>
            <a class="navbar-brand" href="./"></a>
          </div>
          <div class="collapse navbar-collapse">
          <br>
            <div href class="row" id="toggle-search">
              <div class="col-xs-12 col-md-8 search-button">Search For...</div>
              <div class="col-xs-6 col-md-4"><i class="flaticon-loupe"></i></div>
            </div>
            <ul class="nav navbar-nav">
              <li class="">
                <a href="./">Meeting Rooms</a>  
              </li>
              <li class="">
                <a href="inverse.html">Services</a>
              </li>
              <li class="end">
                <a href="inverse.html">Your Reservations</a>
              </li>
              <li class="">
                <a href="admin-area.html">Admin Area</a>
              </li>
              <li class="divider"></li>
            </ul>
            <p class="navbar-text">
              Made by
              <a href="http://www.samrayner.com">Sam Rayner</a>
            </p><br><br><br>
            <span class="divider"></span>
            <a href="sign-in.html" class="self-menu">
              <i class="flaticon-user-2"></i><span id="account-name">Sign In</span>
            </a>

          </div>
        </div>
      </nav>
    </div>
    <div class="col-sm-9 col-lg-10 content admin-area">
      <!-- your page content -->
      <div class="header">
       <h1>SHS Online Library System</h1>
       <h2>Manage Books. Add Books. Delete Books. Edit Books.</h2>
       <h3></h3>
      </div>
      

      <div class="btn-group btn-group-justified" role="group" aria-label="...">
        <div class="btn-group"  role="group">
          <button type="button" id="browse-rm-tab" class="btn btn-default">Browse Reading Materials</button>
        </div>
        <div class="btn-group"  role="group">
          <button type="button" id="add-rm-tab" class="btn btn-default">Add Reading Material</button>
        </div>
      </div>
      <br><br><br>

        <div id = "manage-books" class ="content lesser-padding-content">
          <div class="btn-group btn-group-justified" role="group" aria-label="...">
            <div class="btn-group"  role="group">
              <button type="button" id="browse-rm-tab" class="btn btn-default">Browse Reading Materials</button>
            </div>
            <div class="btn-group"  role="group">
              <button type="button" id="add-rm-tab" class="btn btn-default">Add Reading Material</button>
            </div>
          </div>
          <br><br><br>

          <div id="content-add-book" class="lesser-padding-content toggable" >
            <h3 align="center">Add Reading Material</h3>
            <div class="content">
            <form id="addNewRM" method="post" action="AddRMServlet">
              <fieldset>
              <input type = "hidden" name = "newRMType" id="newRMType"/>
              <div class="form-group">
                <label for="rm-type">Type of Reading Material</i></label>
                <select id="rm-type" class="form-control form-components-rd" style="width:70%">
                  <option value="" selected disabled>Choose Collection</option>
                  <option value="Book">Books</option>
                  <option value="Thesis">Thesis</option>
                  <option value="Magazine">Magazine</option>    
                </select>
              </div>
            
              <label for="author-name">Reading Material Title</label>
              <input type="text" class="form-control form-components-rd" name="title" id="title" placeholder="Author/s" style="width:70%" required>
            
              <label for="location-id">Location <i>(Dewey Decimal System)</i></label>
              <input type="text" class="form-control form-components-rd" name="location-id" id="location-id" placeholder="Location" style="width:70%" required>

              <label for="author-name">Author/s <i>(If more then 2 authors, please separated with & (ampersand) )</i></label>
              <input type="text" class="form-control form-components-rd" name="author-name" id="author-name" placeholder="Author/s" style="width:70%" required>

              <label for="author-name">Publisher</label>
              <input type="text" class="form-control form-components-rd" name="publisher" id="publisher" placeholder="Publisher" style="width:70%" required>

              <label for="year-published">Year Published</label>
              <input type="number" class="form-control form-components-rd" name="year-published" id="year-published" placeholder="Year Published" style="width:70%" required>

              <label for="year-published">Tags</label>
              <input type="text" class="form-control form-components-rd" name="tags" id="tags" placeholder="Tag, Tag, Tag" width:70%" required>
              <br>
              <button type="submit" id="submit-add-rm" class="btn btn-default submit-btn form-components-rd auto-width erase-margin">Add Reading Material</button>
              </fieldset>
            </form>
            </div>
          </div>
          <div id="content-search2">
            <h3 align="center">Search for Books</h3>
            <form class="form-inline input-group" id="search" align="center">
              <div class="form-group">
                <select id="search-filter" class="form-control form-components-rd" style="z-index: -1;">
              <option value="KEYWORDS" selected disabled> Keyword</option>
              <option value="TITLE"> Title</option>
              <option value="AUTHOR"> Author</option>
              <option value="PUBLISHER"> Publisher</option>    
                </select>
              </div>
              <div class="form-group" style="width:50%">
                <input type="text" class="form-control form-components-rd" id="search-bar" placeholder="Search for..." style="z-index: -1;" autofocus>
              </div>
              <div class="form-group">
                <select id="search-collection" class="form-control form-components-rd" style="z-index: -1;">
              <option value="ALL" selected disabled>All</option>
              <option value="BOOK">Books</option>
              <option value="MAGAZINE">Magazines</option>
              <option value="THESIS">Thesis</option>     
                </select>
              </div>
              <div class="form-group">
              <button type="submit" id="submit-search" class="btn btn-default form-components-rd transparent-bg"><i class="flaticon-loupe"></i></button>
              </div>
            </form>
            <br>
            <div class="divider-dark"></div>
          </div>
          <br>
          <div class="rm-results">
            <b id="results-found" style="margin: -1px;">34 results found</b>
            <c:forEach items="${reading_material}" var = "i" >
      <!-- START -->
      <div class="row rm-gen-details">
        <div class="col-md-2">
	        <img src="img/book_placeholder.jpg" class="rm-img" width="100%" style="margin: auto 0;"></div>
	        <div class="col-md-10 rm-information">
	        <c:set var="rmID">${i.RMID_Location}</c:set>
	          <a onclick="review('${rmID}')" class="title"><b>${i.title}</b></a>
	          <span class="author">${i.author}</span>
	          <span class="pub-info">${i.publisher}</span>
	          <br>
	          
	          <div class="row">
                  <b>
                  <div class="col-md-3"><u>Location</u></div>
                  <div class="col-md-3"><u>Status</u></div>
                  <div class="col-md-3"></div>
                  <div class="col-md-3"></div>
                  </b>
                </div>
	          <div class="location-status">
	            <div class="row">
	              <div class="col-md-3"><span class="location">${i.RMID_Location}</span></div>
	              <div class="col-md-3"><span class="reserved-status">${i.status}</span></div>
	              <c:set var="status">${i.status}</c:set>
	              <c:choose>
	              	<c:when test="${status ==  'RESERVED'}">
	              		<div onclick="clickedType('${loc}')" class="col-md-3"><button onclick="clickedType(${i.RMID_Location })" class="reserve-inline btn btn-default override-btn">Override<br> Reservation</button></div>
	              	</c:when>
	              	<c:otherwise>
	              		<c:choose>
	              			<c:when test="${status== 'AVAILABLE'}">
		              			<c:set var="loc">${i.RMID_Location}</c:set>
		              			<div onclick="clickedType('${loc}')" class="col-md-3"><button   onclick="clickedType(${i.RMID_Location })" class="reserve-inline btn btn-default override-btn disabled">Override<br> Reservation</button></div>
	                    	   <div class="col-md-3"><button class="reserve-inline btn btn-default delete-btn"><i class="flaticon-trash"></i></button></div>
						    </c:when>    
						    <c:otherwise> <!--  BORROWED -->
						    	<c:set var="loc">${i.RMID_Location}</c:set>
		              			<div onclick="clickedType('${loc}')" class="col-md-3"><button   onclick="clickedType(${i.RMID_Location })" class="reserve-inline btn btn-default override-btn disabled">Override<br> Reservation</button></div>
	                    	   <div class="col-md-3"><button class="reserve-inline btn btn-default delete-btn disabled"><i class="flaticon-trash"></i></button></div>
						    </c:otherwise>
	              		</c:choose>
	              	</c:otherwise>
				 </c:choose>
	              
	            </div>
	          </div>
          </div>
          </div>
        </c:forEach>
          </div>
        </div>

        <div id ="manage-reservations" class="content lesser-padding-content toggable">
          <div class="panel panel-default">
            <div class="panel-heading">
              <a data-toggle="collapse" href="#current-book-log">Current Reading Material Reservations</a>
            </div>
            
            <div id="current-book-log" class="panel-collapse collapse in">
              <div class="panel-body">
                <div class="log-item bordered-dark">
                  <a class="title">Analyzing Driving Risks of Roadway Traffic under Adverse Weather Conditions: In Case of Rain Day</a>
                  <div class="status reserved-status">Reserved</div>
                  Reserved on : <span class="date_reserved">mm / dd / yyyy</span><br>
                  Return by : <span class="date_returned">mm / dd / yyyy</span>

                  <button class="btn btn-default override-btn">Override Reservation</button>
                </div>

                <div class="log-item bordered-dark">
                  <a class="title">Analyzing Driving Risks of Roadway Traffic under Adverse Weather Conditions: In Case of Rain Day</a>
                  <div class="status reserved-status">Reserved</div>
                  Reserved on : <span class="date_reserved">mm / dd / yyyy</span><br>
                  Return by : <span class="date_returned">mm / dd / yyyy</span>

                  <button class="btn btn-default override-btn">Override Reservation</button>
                </div>

              </div>
            </div>
          </div>
        </div>

        <div id ="manage-accounts" class="content lesser-padding-content toggable">
          <div class="btn-group btn-group-justified" role="group" aria-label="...">
            <div class="btn-group" role="group">
              <button type="button" class="btn btn-default">Add Account</button>
            </div>
            <div class="btn-group" role="group">
              <button type="button" class="btn btn-default">Delete Account</button>
            </div>
          </div><br>
          <div class="panel panel-default">
            <div class="panel-heading"><h3 class="panel-title">Recent Announcements</h3></div>
            <div class="panel-body">
              <ul>
              <li><B>SECURDE Milestone 1 due on July 6, 2017</B></li>
              </ul>
            </div>
          </div>
        </div>



<!-- don't go beyond this point -->
    </div> <!-- end of content -->
  </div> <!-- end of row -->
</div> <!-- end of container-fluid -->
	
<form id="ManageBooks" action="AdminRMServlet" method="post"></form>
<form id="ManageRMReserve" action="AdminRMReserveServlet" method="post"></form>
<form id="ManageMRReserve" action="AdminMRReserveServlet" method="post"></form>
<form id="ManageAccounts" action="AdminAccountsServlet" method="post"></form>
<form id="ExportLog" action="AdminLogServlet" method="post"></form>
<form id="AdminSearchRM" action=AdminRMSearchResultsPageServlet method="post"></form>

<!--  INSERT SCRIPT TAGS HERE -->
<!-- must be in every page -->
<script src="js/jquery-3.0.0.min.js"></script>
<script src="bootstrap-3.3.7-dist/js/bootstrap.min.js"></script>
<script src="js/admin-menu-links.js"></script>
<script src="js/app.js"></script>			
<!-- //////////////////// -->
<script src="js/ohsnap/ohsnap.js"></script>
<script src="js/register.js"></script>
<script src="js/manage-books.js"></script>

<script>

$('.col-md-1').click(function() {
    console.log("Delete CLICKED");
	$("#deleteRMForm").submit();
});

$("a.tab").click(function(e){
  e.preventDefault();
})

$("#books-tab a").click(function(e){
  $("#manage-books").css('display', 'block');
  $("#manage-reservations").css('display', 'none');
  $("#books-tab").addClass("active");
  $("#reservations-tab").removeClass("active");
  $("#accounts-tab").removeClass("active");
  $("#log-tab").removeClass("active");
})

$("#reservations-tab a").click(function(e){
  $("#manage-books").css('display', 'none');
  $("#manage-reservations").css('display', 'block');
  $("#books-tab").removeClass("active");
  $("#reservations-tab").addClass("active");
  $("#accounts-tab").removeClass("active");
  $("#log-tab").removeClass("active");
})


$("#accounts-tab a").click(function(e){
  $("#manage-books").css('display', 'none');
  $("#manage-reservations").css('display', 'none');
  $("#manage-accounts").css('display', 'block');
  $("#books-tab").removeClass("active");
  $("#reservations-tab").removeClass("active");
  $("#accounts-tab").addClass("active");
  $("#log-tab").removeClass("active");
  
})

$("#log-tab a").click(function(e){
  
})

$("#toggle-search").click(function(e){
  window.location.href = "index.html";
})

$(".override-btn:not(.disabled)").click(function(e){
    $('#overlay-screen').fadeIn("fast", function(){});
    $("#confirm-override").fadeIn(350, function(){});
})

$(".delete-btn:not(.disabled)").click(function(e){
    $('#overlay-screen').fadeIn("fast", function(){});
    $("#confirm-override").fadeIn(350, function(){});
})


 $('#overlay-screen').click(function(e){
    $("#confirm-override").fadeIn(350, function(){});

 })

 $("#add-rm-tab").click(function(e){
    $("#content-add-book").fadeIn(350, function(){});
    $(".rm-results").fadeOut(350, function(){});
    $("#content-search2").fadeOut(350, function(){});
 })

 $("#browse-rm-tab").click(function(e){
    $("#content-add-book").fadeOut(350, function(){});
    $(".rm-results").fadeIn(350, function(){});
    $("#content-search2").fadeIn(350, function(){});
 })

 $("#submit-add-rm").click(function(e){
	$("#newRMType").val($("#rm-type option:selected").val());
	 
    $("#addNewRM").submit();
 })
</script>
</body>
</html>