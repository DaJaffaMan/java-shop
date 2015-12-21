angular.module('productApp').controller('productController', function($scope, product) {
$scope.getProduct = function(){
product.getProductDetails();
}
//$scope.addProduct = function(productName, stock, price) {
//product.addProductDetails();
//}
});
