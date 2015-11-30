function GetStock($scope, $http) {
    $http.get('http://localhost:4567/get/stock/banana').
        success(function(data) {
            $scope.response = data;
        });
}