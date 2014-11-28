var employeeApp = angular.module('employeeApp', [
    'ngRoute', 'ngAnimate', 'employeeCtrls', 'employeeFilters',
    'employeeServices', 'employeeDirectives'
]);

employeeApp.config(function($routeProvider) {
    $routeProvider.when('/hello', {
        templateUrl: 'html/hello.html',
        controller: 'HelloCtrl'
    }).when('/list',{
    	templateUrl:'html/employees.html',
    	controller:'EmployeeListCtrl'
    }).otherwise({
        redirectTo: '/hello'
    })
});
