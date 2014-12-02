var employeeServices = angular.module('employeeServices', []);

employeeServices.service('employeeService', ['$scope',function($scope) {

}]);

employeeServices.service('shareFactory', [function() {

	var update;
	var employee;

	this.getUpdate = function(){
		return this.update;
	};

	this.setUpdate = function(update){
		this.update = update;
	};

	this.getEmployee = function(){
		return this.employee;
	};

	this.setEmployee = function(employee){
		this.employee = employee;
	};
}]);

employeeServices.service('employeeFunc', ['$http',function ($http) {
	
	this.addEmployee = function(fName,lName,dpt,em,callback){
		$http.post("/employee/add", {
            firstName : fName,
            lastName  : lName,
            department: dpt,
            email     : em
        }).success(function(){
        	callback();
        });
	}
}])

