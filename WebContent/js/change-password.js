$(document).ready(function(){
	
	$("#verify-new-password").click(function(e){
		e.preventDefault();
		
		if(validatePassword() && ($("#password").val() == $('#confirm_password').val()))
			$("#chnge-password").submit();
		
	});
	
});

