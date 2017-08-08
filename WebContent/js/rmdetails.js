function view_details(id)
{
	 console.log("view " + id);
	$("#rmID_location").val(id);
	$("#rmID_location").text(id);
	console.log("rmIDLocation " + $("#rmID_location").val()); 
	 $("#viewDetailsForm").submit();
	
}