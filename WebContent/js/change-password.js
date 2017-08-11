$(document).ready(function(){
	
	/*
	$(document).ready(function() {
		console.log($("#idnumber").val() +" hehehhe" );
		$("#idnumber").text($("#idnumber").val())
		console.log($("#idnumber").text() +" hehehhe1" );
	});
	*/
	
	$("#verify-new-password").click(function(e){
		e.preventDefault();
		
		if(validatePassword() && ($("#password").val() == $('#confirm_password').val()))
			$("#chnge-password").submit();
		
		if(($("#password").val() == $('#confirm_password').val())){
			
		}
	});
	
	
	
	
});

