function getProduct(){
$.ajax({
   type: "GET",
   crossDomain: true,
   dataType: 'json',
   contentType:'application/json',
   url: "http://localhost:4567/get/product/" + document.getElementById("product").value,
   success: function(data){
     $(".productName").html(JSON.stringify(data));
   }
});
}