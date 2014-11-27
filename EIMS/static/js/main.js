function GetEmployee($scope, $http) {
    $http.get('http://localhost:8080/employees/Joe').
        success(function(data) {
            $scope.employee = data;
        });
}