angular.module('productApp').controller('productController', function($scope, productService) {

    $scope.getProduct = function() {
            productService.getProductDetails($scope.product)
                .then(function(response){
                    $scope.productData = response.data;
                });
        }

    $scope.addProduct = function() {
            productService.addProductDetails($scope.product_name, $scope.product_stock, $scope.product_price);
        }
});