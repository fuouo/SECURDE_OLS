
$(document).ready(function() {
	
	console.log("Question" + $("#question").text());
	
	if($("#question").text() != "")
	{
		console.log($("#idnumber2").val() +" hehehhe" );
		$("#idnumber2").text($("#idnumber2").val());
		console.log($("#idnumber2").text() +" hehehhe1" );
		$("#username").fadeOut("fast", function(){});
	    $("#secret-question").fadeIn("fast", function(){});
	}
});

$("#verify-secret-answer").click(function(){
});

 $("#verify-username").click(function(){
	console.log("Load Secret Question");
	$("#username").submit();
	$("#username").fadeOut("fast", function(){});
    $("#secret-question").fadeIn("fast", function(){});
    
}); 