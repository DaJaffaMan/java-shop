$.ajax({
   type: "GET",
   crossDomain: true,
   dataType: 'json',
   contentType:'application/json',
   url: "http://localhost:4567/get/stock/banana",
   success: function(data){
     $('.productName').append(data.productName);
   }
});