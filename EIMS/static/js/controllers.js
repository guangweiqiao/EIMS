/*jshint strict:false */

var employeeCtrls = angular.module('employeeCtrls', ['employeeServices']);

employeeCtrls.controller('HelloCtrl', ['$scope',
    function($scope) {
        $scope.greeting = {
            text: 'Hello'
        };
    }
]);

employeeCtrls.controller('LogonCtrl', ['$scope','$http','$location', function ($scope,$http,$location) {
    $scope.user = {
        name     : "admin",
        password : ""
    };

    $scope.logon = function(){
        $http.get('/admin/logon/' + $scope.user.name + "/" + $scope.user.password).success(function(data){
            if(data){
                $location.path("/list");
            }else{
                alert("Login Failed!");
            }
        });
    };

    $scope.reset = function(){
        $scope.user = {
            name     : "admin",
            password : ""
        };
    };
}]);

employeeCtrls.controller('listCtrl',['$scope', '$http','$window', '$location', 
    '$log','shareFactory','employeeService', 
    function ($scope, $http, $window, $location,$log,shareFactory,employeeService) {
    
    $scope.listEmployees = function(){
        employeeService.listEmployees(function(data){$scope.employees = data;});
    };

    $scope.getEmployeeById = function(id){
        employeeService.getEmployeeById(id, function(data){
            shareFactory.setEmployee(data);
            $location.path("/add");
        });
    };

    $scope.forwardToAddPage = function(){
        shareFactory.setUpdate(false);
        $location.path("/add");
    };

    $scope.forwardToEditPage = function(id){
        shareFactory.setUpdate(true);
        $scope.getEmployeeById(id);
    };

    $scope.deleteEmployee = function(id){
        employeeService.deleteEmployee(id, function(){
            $scope.listEmployees();
        });
    };
}]);

employeeCtrls.controller('addAndUpdateCtrl', ['$scope', '$http','$window', '$location', 
    '$log','shareFactory','employeeService', 
    function ($scope, $http, $window, $location,$log,shareFactory,employeeService) {

    var update = shareFactory.getUpdate();
    if(update){
        var data = shareFactory.getEmployee();
        $scope.firstName = data.firstName;
        $scope.lastName  = data.lastName;
        $scope.department= data.department;
        $scope.email     = data.email;
    }else{
        $scope.firstName = '';
        $scope.lastName = '';
        $scope.department= '';
        $scope.email     = '';
    }

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

    $scope.addEmployee = function(){
        employeeService.addEmployee($scope.firstName, $scope.lastName, $scope.department, $scope.email,
         function(){
            $location.path("/list");
         });
    };

    $scope.editEmployee = function() {
        var employee = shareFactory.getEmployee();
        $log.log("update an employee with id:" + employee.id + " firstName:"+ $scope.firstName);
        employeeService.editEmployee(employee.id, $scope.firstName, $scope.lastName, $scope.department, $scope.email,
            function(){
                $location.path("/list");
            });
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

}]);