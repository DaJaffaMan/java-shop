angular.module('productApp').service('productService', ['$http', function($http) {
    return {
        getProductDetails: function(product) {
            return $http.get('http://localhost:4567/get/product/' + product);
        },

        addProductDetails: function(product_name, product_stock, product_price) {
                    $http.post('http://localhost:4567/add/' + product_name + '/' + product_stock + '/' + product_price);
        }
    };
}]);