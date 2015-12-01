$(document).ready(function(){
$.ajax({
url: "http://localhost:4567/get/stock/banana"
}).then(function(data){
$('.productName').append(data.productName);
}
})