$(document).ready(function(){
	
	$(document).on("click", ".search-inline", function(){
		var searchString = $(this).parent().siblings().children().text();
		$("#reading_material").val(searchString);
		$("#GoToCategory").submit();
	});
	
});