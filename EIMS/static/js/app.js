/*jshint strict:false, undef: true, unused: true */
/* global angular */

var employeeApp = angular.module('employeeApp', [
    'ngRoute', 'ngAnimate', 'employeeCtrls','employeeFilters', 'employeeDirectives'
]);

employeeApp.config(function($routeProvider) {
    $routeProvider.when('/hello', {
        templateUrl: 'html/hello.html',
        controller: 'HelloCtrl'
    }).when('/list',{
    	templateUrl:'html/employees.html',
    	controller:'listCtrl'
    }).when('/add', {
        templateUrl: 'html/employee-add.html',
        controller: 'addAndUpdateCtrl'
    }).when('/logon', {
        templateUrl: 'html/logon.html',
        controller: 'LogonCtrl'
    }).otherwise({
        redirectTo: '/logon'
    });
});
