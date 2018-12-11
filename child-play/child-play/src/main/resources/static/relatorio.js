$(document).ready(function () {
   $.ajax({
        url: '/relatorio/ver', method: 'GET', success: function (data) {
            
            console.log(data);

        }
    });
});