$(document).ready(function(e) {
	var regexName = /^[a-zA-Z\s]*$/;

	
	$('#first-name').on("keypress", function(e) {
		return (regexName.test(String.fromCharCode(e.which)))
	});

	$('#last-name').on("keypress", function(e) {
		return (regexName.test(String.fromCharCode(e.which)))
	});
	
});

function validateForm() {
	console.log("VALIDATING THE FORM");
	
	var regexEmail = /^\w+([\.-]?\w+)*@\w+([\.-]?\w+)*(\.\w{2,3})+$/;
	console.log(regexEmail.test($('#email-address').val()));
	
	if(document.getElementById("passstrength").innerText != "Strong!") {
		console.log("PASSWORD NOT STRONG ENOUGH");
		
		$('#passwordError').html("Password is not strong enough!");
		$('#passstrength').html("");
		$('#password').focus();
		
		return false;
	} else if(!regexEmail.test($('#email-address').val())){
		console.log("INVALID EMAIL");
		$('#emailError').html("Invalid email!");
		$('#email-address').focus();
		return false;
	} else {
		return true;
	}
}

