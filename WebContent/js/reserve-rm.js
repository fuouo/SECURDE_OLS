/* RESERVE-RM.JS 
 * Includes button events for reservation of rm 
 * 		1) Reserve Book Button events
 * 		2) Confirm Reservatipn Button events
 * 		3) Cancel Reservation Button events
 */

function clickedType(id)
{
	submitReservation(id);
}

function submitReservation(id){	
 	$("#locationID").val(id);
 	console.log("ehhe " + $("#locationID").val()); 
 	 $("#reserveForm").submit();
	
}

function review(id)
{
	 console.log("hahaha " + id);
	$("#reviewID").val(id);
	$("#reviewID").text(id);
	console.log("EHHE " + $("#reviewID").val()); 
	 $("#reviewForm").submit();
	
}


$(document).ready(function(){
	$('.reserve-inline:not(.disabled)').click(function(){
		console.log($(this).attr('id'));
		clickedType($(this).attr('id'));
		//$("#reserveForm").submit();
	});
	 
	$('#cancel-reserve').click(function(){
	    $('#overlay-screen').fadeOut("fast", function(){});
	    $('#confirm-reservation').fadeOut(350, function(){});
	});

	$('#overlay-screen').click(function(){
	    $('#overlay-screen').fadeOut("fast", function(){});
	    $('#confirm-reservation').fadeOut(350, function(){});
	});

});
