<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
</head>
<body>
<!-- SEARCH BAR -->
<div id="content-search">
  <h3 align="center">Search for Books</h3>
  <form class="form-inline input-group" id="search" align="center" action="RMSearchResultsPageServlet" method = "post">
    <div class="form-group">
      <select id="search-filter" name="RMFilter" class="form-control form-components-rd">
        <option value="KEYWORDS" selected> Keyword</option>
        <option value="TITLE"> Title</option>
        <option value="AUTHOR"> Author</option>
        <option value="PUBLISHER"> Publisher</option>  
      </select>
    </div>
    <div class="form-group" style="width:50%">
      <input type="text" name= "reading_material" class="form-control form-components-rd" id="search-bar" placeholder="Search for..." autofocus>
    </div>
    <div class="form-group">
      <select id="search-collection" name="RMType" class="form-control form-components-rd">
        <option value="ALL" selected>All</option>
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
  <br><br>
  <div align="center">Search For List of : 
  <form method="post" action="DisplayCategoryServlet" class="category-form">
	<input type="hidden" id="RMType" name="RMType" value="BOOK"/>
	<input type="hidden" id="reading_material" name="reading_material" value=""/>
	<input type="hidden" id ="RMFilter" name="RMFilter" value=""/>
	<button class="category-btn form-control form-components-rd" type="submit">Books</button>
  </form>
  
  <form method="post" action="DisplayCategoryServlet" class="category-form">
	<input type="hidden" id="RMType" name="RMType" value="MAGAZINE"/>
	<input type="hidden" id="reading_material" name="reading_material" value=""/>
	<input type="hidden" id ="RMFilter" name="RMFilter" value=""/>
	<button class="category-btn form-control form-components-rd" type="submit">Magazines</button>
  </form>
  
  <form method="post" action="DisplayCategoryServlet" class="category-form">
	<input type="hidden" id="RMType" name="RMType" value="THESIS"/>
	<input type="hidden" id="reading_material" name="reading_material" value=""/>
	<input type="hidden" id ="RMFilter" name="RMFilter" value=""/>
	<button class="category-btn form-control form-components-rd" type="submit">Thesis</button>
  </form>
  
  <form method="post" action="DisplayAuthorServlet" class="category-form">
	<button class="category-btn form-control form-components-rd" type="submit">Authors</button>
  </form>
  
  <form method="post" action="DisplayPublisherServlet" class="category-form">
	<button class="category-btn form-control form-components-rd" type="submit">Publishers</button>
  </form>
  
  
  </div>
  
 </div>
 
 <!-- END OF SEARCH BAR -->
</body>
</html>