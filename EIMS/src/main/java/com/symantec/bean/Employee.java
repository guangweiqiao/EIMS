package com.symantec.bean;


public class Employee {

	private String id;
	
	private String firstName;
	
	private String lastName;
	
	private String department;
	
	private String email;

	public Employee(){}
	
	public Employee(String firstName, String lastName){
		this(firstName, lastName, null,null);
	}
	
	public Employee(String firstName, String lastName, String department, String email){
		this.firstName = firstName;
		this.lastName  = lastName;
		this.department= department;
		this.email     = email;
	}
	
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getDepartment() {
		return department;
	}

	public void setDepartment(String department) {
		this.department = department;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}
	
	@Override
	public String toString(){
		StringBuffer sbuf = new StringBuffer("Employee Info:");
		sbuf.append("id: " + this.id).append(" first name:" + this.firstName)
			.append(" lastName:"+ this.lastName).append(" department:" + this.department)
			.append(" email: "+ this.email);
		
		return sbuf.toString();
	}
	
	@Override
	public boolean equals(Object o){
		if(null == o || !(o instanceof Employee)){
			return false;
		}
		
		Employee employee = (Employee)o;
		return (this.id.equals(employee.getId()) && this.firstName.equals(employee.getFirstName()) 
				&& this.lastName.equals(employee.getLastName()) && this.email.equals(employee.getEmail())
				&& this.department.equals(employee.getDepartment())) ? true : false;
	}
}
