$(document).ready(function() {
   	//Meeting Rooms
   $('#mRoom').click(function() {
	   console.log("Meeting Rooms CLICKED");
	   $("#meetingRoomForm").submit();
   });
   
  //Reservations
   $('#reservations').click(function() {
	   console.log("Resevations CLICKED");
	   $("#yourReservationsForm").submit();
   });
   
	$('#logo').click(function() {
		console.log("LOGO CLICKED");
		$("#homeForm").submit();
	});
	 	
 	$("#signInSignOut").click(function() {
 		console.log($("#account-name").text());
 		$("#signInForm").submit();
 	});
 	
 	$("#signInSignOut").click(function() {
  		console.log($("#account-name").text());
  		if($("#account-name").text() == "Sign In"){
  			console.log("GO TO SIGN IN");
  			window.location.href = "sign_in_sign_up.jsp";
  		}
  	});
 
 });