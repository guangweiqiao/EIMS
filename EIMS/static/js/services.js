var employeeServices = angular.module('employeeServices', []);

employeeServices.service('employeeService', ['$scope',function($scope) {

}]);

employeeServices.service('shareFactory', [function() {

	var update = false;
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

employeeServices.service('employeeService', ['$http',function ($http) {
	
	this.addEmployee = function(fName,lName,dpt,em,callback){
		$http.post("/employee/add", {
            firstName : fName,
            lastName  : lName,
            department: dpt,
            email     : em
        }).success(callback);
	};

	this.editEmployee = function(id,fName,lName,dpt,em,callback){
		$http.put("/employee/update", {
			id        : id,
            firstName : fName,
            lastName  : lName,
            department: dpt,
            email     : em
		}).success(callback);
	};

	this.deleteEmployee = function(id, callback){
		$http.delete('/employee/delete/' + id).success(callback);
	};

	this.listEmployees = function(callback){
		$http.get('/employees/list').success(callback);
	};

	this.getEmployeeById = function(id, callback){
		$http.get('/employee/get/' + id).success(callback);
	};

}])

