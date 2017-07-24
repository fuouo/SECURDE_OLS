$(document).ready(function(){
	
	$(".override-btn:not(.disabled)").click(function(e){
	    $('#overlay-screen').fadeIn("fast", function(){});
	    $("#confirm-override").fadeIn(350, function(){});
	});
	
	$(".delete-btn:not(.disabled)").click(function(e){
	    $('#overlay-screen').fadeIn("fast", function(){});
	    $("#confirm-override").fadeIn(350, function(){});
	});
	
	 $('#overlay-screen').click(function(e){
	    $("#confirm-override").fadeIn(350, function(){});
	
	 });
	 
	 $(document).on("click", ".title", function(){
		var rmID = $(this).parent().attr('id');
		getRMDetails(rmID);
	 });
	 
	 $(document).on("click", ".override-btn", function(){
		 var rmID = $(this).parent().attr('id');
		 overrideReservation(rmID);
	 });
	 
});

function getRMDetails(rmID){
	$("#rmID_location").val(rmID);
	$("#GoToRMDetails").submit();
}

function overrideReservation(rmIDlocation){
	//alert(rmIDlocation);
	///*
	$.ajax({
		url: "OverrideReservationRMServlet",
		method: "post",
		data: {
			'rmID_location': rmIDlocation,
		},
		dataType: "json",
		success: function(result) {
			location.reload();
		}
	});
	
	//*/
	
}
