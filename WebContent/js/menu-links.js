$(document).ready(function() {
   	//Meeting Rooms
   $('#mRoom').click(function(e) {
	   e.preventDefault();
	   console.log("Meeting Rooms CLICKED");
	   $("#meetingRoomForm").submit();
   });
   
  //Reservations
   $('#reservations').click(function(e) {
	   e.preventDefault();
	   console.log("Resevations CLICKED");
	   $("#yourReservationsForm").submit();
   });
   
	$('#logo').click(function(e) {
		   e.preventDefault();
		console.log("LOGO CLICKED");
		$("#homeForm").submit();
	});
	 	
// 	$("#signInSignOut").click(function(e) {
// 	   e.preventDefault();
// 		console.log($("#account-name").text());
// 		$("#signInForm").submit();
// 	});
	
	console.log("MOTHER FUCKING SHIT");
 	
 	$("#signInSignOut").click(function(e) {
 	   e.preventDefault();
  		console.log($("#account-name").text());
  		if($("#account-name").text() == "Sign In"){
  			console.log("FUCKING SHIT");
  			$("#signInForm").submit();
  		}
  	});
 
 });