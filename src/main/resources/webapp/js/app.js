var app = angular.module('productApp', []);

app.controller('productController', function($scope, product) {
$scope.getProduct = function(){
product.getProductDetails();
}
//$scope.addProduct = function(productName, stock, price) {
//product.addProductDetails();
//}
});

app.service('product', function () {

    return {

getProductDetails: function($scope, $http) {
                               $http.get('http://localhost:4567/get/product/' + document.getElementById("product")).
                                   success(function(data) {
                                       $scope.product = data;
                                   });
                               }
    };
});