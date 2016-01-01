angular.module('productApp').service('productService', ['$http', function($http) {
    return {
        getProductDetails: function(product) {
            return $http.get(window.location.protocol + '//' + window.location.hostname + ':' + window.location.port + '/get/product/' + product);
        },

        addProductDetails: function(product_name, product_stock, product_price) {
                    $http.post(window.location.protocol + '//' + window.location.hostname + ':' + window.location.port + '/add/' + product_name + '/' + product_stock + '/' + product_price);
        }
    };
}]);