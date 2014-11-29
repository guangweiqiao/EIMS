var employeeCtrls = angular.module('employeeCtrls', []);

employeeCtrls.controller('HelloCtrl', ['$scope',
    function($scope) {
        $scope.greeting = {
            text: 'Hello'
        };
    }
]);


employeeCtrls.controller('EmployeeCtrls', ['$scope', '$http','$window', '$location', function ($scope, $http, $window, $location) {

    $scope.listEmployees = function(){
        $http.get('/employees').success(function(data) {
            $scope.employees = data;
        });
    }

    $scope.addEmployee = function(){
        var fName = $scope.firstName;
        var lName = $scope.lastName;
        var dpt = $scope.department;
        var em = $scope.email;

        //alert(fName + "  " + lName + "  "+ dpt+ "  " + em);

        $http.post("/createEmployee", {
            firstName : fName,
            lastName  : lName,
            department: dpt,
            email     : em
        }).success(function(){
            //$window.location.href = "/employees"; //old usage
            $location.path("/list");
            //$scope.listEmployees();
        });
    };

    $scope.deleteEmployee = function(id){
        $http.delete('/employees/' + id).success(function(){
            
            $scope.listEmployees();
            alert("delete employee:" + id);
        });
    };

    $scope.listEmployees();
}]);


