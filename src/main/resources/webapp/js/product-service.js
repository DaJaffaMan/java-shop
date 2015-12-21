angular.module('productApp').controller('productController').service('product', function () {

    return {

getProductDetails: function($scope, $http) {
                               $http.get('http://localhost:4567/get/product/' + document.getElementById("product")).
                                   success(function(data) {
                                       $scope.product = data;
                                   });
                               }
    };
});