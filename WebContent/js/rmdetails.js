function view_details(id)
{
	 console.log("view " + id);
	$("#rmID_location").val(id);
	$("#rmID_location").text(id);
	console.log("rmIDLocation " + $("#rmID_location").val()); 
	 $("#viewDetailsForm").submit();
	
}

$("#edit-rm-btn").click(function(e){
	  e.preventDefault();
	  $("#content-edit-book").fadeIn("fast", function(){});
	  $(".rm-gen-details").fadeOut("fast", function(){});
	  $(".rm-reviews").fadeOut("fast", function(){});
	})

	$("#submit-edit-rm").click(function(e){
	  //temporary
	  location.reload();

	});