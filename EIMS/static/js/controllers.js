'use strict';

var employeeCtrls = angular.module('employeeCtrls', ['employeeServices']);

employeeCtrls.controller('HelloCtrl', ['$scope',
    function($scope) {
        $scope.greeting = {
            text: 'Hello'
        };
    }
]);

employeeCtrls.controller('EmployeeCtrls', ['$scope', '$http','$window', '$location', 'shareFactory','employeeFunc', 
    function ($scope, $http, $window, $location, shareFactory,employeeFunc) {

    $scope.checkNullParam = function(){
        if(!($scope.firstName)){
            alert("Please insert firstName");
            return 0;
        }

        if(!($scope.lastName)){
            alert("Please insert lastName");
            return 0;
        }

        if(!($scope.department)){
            alert("Plese insert department");
            return 0;
        }

        if(!($scope.email)){
            alert("Please insert email");
            return 0;
        }

        return 1;
    };

    $scope.listEmployees = function(){
        $http.get('/employees/list').success(function(data) {
            $scope.employees = data;
        });
    };

    $scope.addEmployee = function(){
        //alert(fName + "  " + lName + "  "+ dpt+ "  " + em);
        employeeFunc.addEmployee($scope.firstName, $scope.lastName, $scope.department, $scope.email, function(){$location.path("/list");});
    };

    $scope.editEmployee = function() {
        alert("update an employee");
        //todo...
    };

    $scope.submit = function(){
        if(!($scope.checkNullParam())){
            return;
        }

        var update = shareFactory.getUpdate();
        if(update){
            $scope.editEmployee();
        }else{
            $scope.addEmployee();
        }
    };

    $scope.deleteEmployee = function(id){
        $http.delete('/employee/delete/' + id).success(function(){
            $scope.listEmployees();
            //alert("delete employee:" + id);
        });
    };

    $scope.getEmployeeById = function(id, callback){
        $http.get('/employee/get/' + id).success(function(data){
            $scope.employee = data;
            callback($scope.employee);
        });
    };

    $scope.forwardToAddPage = function(){
        shareFactory.setUpdate(false);
        $location.path("/add");
    };

    $scope.forwardToEditPage = function(id){
        shareFactory.setUpdate(true);
        $scope.getEmployeeById(id, function(){
            //alert($scope.employee);
            $scope.update = shareFactory.getUpdate();
            $location.path("/add");
        });
    };

}]);


