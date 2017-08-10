
var regexName = /^[a-zA-Z\s]*$/;

var regexID = 

//$('#first-name').keyup(function(e) {
//     if(RegExpression.test($('#first-name').val())) {
//    	 
//     } else {
//    	 console.log("wrong");
//    	 e.preventDefault();
//     }
//});

$('#first-name').on("keypress", function(e) {
	return (regexName.test(String.fromCharCode(e.which)))
});

$('#last-name').on("keypress", function(e) {
	return (regexName.test(String.fromCharCode(e.which)))
});

