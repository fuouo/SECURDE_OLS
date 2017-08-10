$(document).ready(function(){
	
	$('#books-tab').click(function(e) {
	   e.preventDefault();
	   $("#GoToPage #destination").val("/WEB-INF/secured/a-manage-books.jsp");
	   $("#GoToPage").submit();
    });
	
	$('#rm-reserve-tab').click(function(e) {
	   e.preventDefault();
	   $("#GoToPage #destination").val("AdminDisplayRMReservationsServlet");
	   $("#GoToPage").submit();
    });
	
	$('#mr-reserve-tab').click(function(e) {
	   e.preventDefault();
	   $("#GoToPage #destination").val("AdminDisplayMRReservationsServlet");
	   $("#GoToPage").submit();
    });
	
	$('#accounts-tab').click(function(e) {
	   e.preventDefault();
	   $("#GoToPage #destination").val("AdminAccountsServlet");
	   $("#GoToPage").submit();
    });
	
	$('#log-tab').click(function(e) {
	   e.preventDefault();
	   $("#GoToPage #destination").val("ExportServlet");
	   $("#GoToPage").submit();
	});
	
	$('#logout').click(function(e) {
		   e.preventDefault();
		console.log("LOGGING OUT");
		$("#logoutForm").submit();
	});
	
	$("#signInSignOut").click(function(e) {
	 	   e.preventDefault();
	  		console.log($("#account-name").text());
	  		if($("#account-name").text() == "Sign In"){
	  			console.log("FUCKING SHIT");
	  			$("#signInForm").submit();
	  		}
	  	});
});