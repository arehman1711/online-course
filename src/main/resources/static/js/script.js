
        function submitForm(id) {
         let form  = document.getElementById(id);;
            form.submit();
        }

       $(document).ready(function() {
       var text_max = $('#courseDescription').attr("maxlength");

       var text_length = $('#courseDescription').val().length;
       var text_remaining = text_max - text_length;

       $('#textarea_feedback').html(text_max + ' characters remaining');

       $('#courseDescription').keyup(function() {
           var text_length = $('#courseDescription').val().length;
           var text_remaining = text_max - text_length;

           $('#textarea_feedback').html(text_remaining + ' characters remaining');
       });

       });

