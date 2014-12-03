package com.symantec.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.symantec.bean.Admin;
import com.symantec.service.AdminService;

@RestController
public class AdminController {

	@Autowired
	private AdminService adminService;
	
	@RequestMapping(value="/admin/logon/{name}/{password}", method = RequestMethod.GET)
	public Admin login(@PathVariable String name, @PathVariable String password){
		return adminService.login(name, password);
	}
}
