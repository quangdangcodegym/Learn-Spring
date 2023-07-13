$().ready(function() {
	$("#demoForm").validate({
        onfocusout: function (element) {
            $("#demoForm").valid();
        },
        validClass: "success",
        /**
        errorClass: "invalid",
        errorLabelContainer: "#messageBox",
        wrapper: "li",
        invalidHandler: function(event, validator) {
          // 'this' refers to the form
          var errors = validator.numberOfInvalids();
          console.log(errors);
          if (errors) {
            var message = errors == 1
              ? 'You missed 1 field. It has been highlighted'
              : 'You missed ' + errors + ' fields. They have been highlighted';
            $("div.error span").html(message);
            $("div.error").show();
          } else {
            $("div.error").hide();
          }
        },
        **/
        rules: {
			"user": {
				required: true,
				maxlength: 15
			},
			"password": {
				required: true,
				minlength: 3
			},
			"re-password": {
				equalTo: "#password",
				minlength: 3
			}
		},
        showErrors: function(errorMap, errorList) {
            $("#summary").html("Your form contains "
              + this.numberOfInvalids()
              + " errors, see details below.");
            this.defaultShowErrors();
          }
      });
});