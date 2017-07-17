/* RESERVE-RM.JS 
 * Includes button events for reservation of rm 
 * 		1) Reserve Book Button events
 * 		2) Confirm Reservatipn Button events
 * 		3) Cancel Reservation Button events
 */

$('#submit-reserve').click(function(){
    alert("Reserved!");
})

$('.reserve-inline:not(.disabled)').click(function(){
    $('#overlay-screen').fadeIn("fast", function(){})
    $('#confirm-reservation').fadeIn(350, function(){});
})

$('#cancel-reserve').click(function(){
    $('#overlay-screen').fadeOut("fast", function(){})
    $('#confirm-reservation').fadeOut(350, function(){});
})

$('#overlay-screen').click(function(){
    $('#overlay-screen').fadeOut("fast", function(){})
    $('#confirm-reservation').fadeOut(350, function(){});
})
