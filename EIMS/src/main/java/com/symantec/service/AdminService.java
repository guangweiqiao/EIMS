package com.symantec.service;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Service;
import com.symantec.bean.Admin;
import com.symantec.util.Util;
import static org.springframework.data.mongodb.core.query.Criteria.where;
import static org.springframework.data.mongodb.core.query.Query.query;

@Service
public class AdminService {

	private static final Log logger = LogFactory.getLog(AdminService.class);
	
	@Autowired
	private MongoTemplate mongoTemplate;
	
	public boolean login(String name, String password){
		if(Util.isEmptyOrNull(name) || Util.isEmptyOrNull(password)){
			if(logger.isInfoEnabled()){
				logger.info("Admin password or name is null");
				return false;
			}
		}
		
		//find one admin 
		Admin admin = mongoTemplate.findOne(query(where("name").is(name).
											andOperator(where("password").is(password))), Admin.class);
		
		if(null == admin){
			if(logger.isInfoEnabled()){
				logger.info("Login failed with name:"+ name + " password:"+ password);
				return false;
			}
		}
		
		return true;
	}
}
