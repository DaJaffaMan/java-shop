var app = angular.module('productApp',  ['ngRoute']);

app.config(function($routeProvider) {
		$routeProvider

			.when('/', {
				templateUrl : 'index.html',
				controller  : 'productController'
			})
			.when('/view', {
				templateUrl : 'pages/view-product.html',
				controller  : 'productController'
			})
			.when('/add', {
				templateUrl : 'pages/add-product.html',
				controller  : 'productController'
			});
	});