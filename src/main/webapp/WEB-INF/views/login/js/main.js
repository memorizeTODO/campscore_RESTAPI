/**
 * 
 */
(function($){
	console.log("Hello from my-script.js!");

	
	$(document).ready(function(){
	   var $inputElement = $("input");
	   $inputElement.change(function() {
	      alert($inputElement.prop("name") +"값 변경");
	   })
	});
	
	/*
	$(document).ready(function() {
	    $(document).on('change',"input", function(event) {
	        alert("start_date button is change!");
	        console.log("startDate button is change!");
	        start_date=$("input[name='start_date']").value();
	        event.preventDefault();
	    });
	});
	
	$(document).ready(function() {
	    $(document).on('change','input', function(event) {
	        alert("endDate button is change!");
	        console.log("end_date button is change!");
	        start_date=$("input[name='end_date']").value();
	        event.preventDefault();
	    });
	});
	*/
})(jQuery);

