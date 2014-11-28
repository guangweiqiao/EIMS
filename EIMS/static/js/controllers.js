var employeeCtrls = angular.module('employeeCtrls', []);

employeeCtrls.controller('HelloCtrl', ['$scope',
    function($scope) {
        $scope.greeting = {
            text: 'Hello'
        };
    }
]);

employeeCtrls.controller('BookListCtrl', ['$scope',
    function($scope) {
        $scope.books =[
        	{title:"Spring in action",author:"123"}]
    }
]);

employeeCtrls.controller('EmployeeListCtrl', ['$scope','$http', 
    function ($scope, $http) {
    	$http.get('/employees').success(function(data) {
            $scope.employees = data;
        });
    }
]);


