package com.symantec.bean;

public class Employee {

	private Long id;
	
	private String firstName;
	
	private String lastName;
	
	private String department;
	
	private String email;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
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
	
	public String toString(){
		StringBuffer sbuf = new StringBuffer("Employee Info:");
		sbuf.append("id: " + this.id).append(" first name:" + this.firstName)
			.append(" lastName:"+ this.lastName).append(" department:" + this.department)
			.append(" email"+ this.email);
		
		return sbuf.toString();
	}
	
}
