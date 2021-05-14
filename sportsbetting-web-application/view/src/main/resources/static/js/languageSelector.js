$(document).ready(function() {
	$("#language").change(function() {
		var selectedOption = $('#language').val();
		if (selectedOption != '') {
			window.location.replace('home?lang=' + selectedOption);
		}
	});
});