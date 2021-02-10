console.log("Sanity Check: Js working!");

var $addPinButton = $('#add-pin-button');

$addPinButton.on('click', function(e) {
	e.preventDefault();
	console.log("Clicked the add pin button!");
	$('#add-pin-modal-container').addClass('show-add-pin-modal');
});