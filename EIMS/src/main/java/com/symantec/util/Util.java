package com.symantec.util;

import org.springframework.stereotype.Component;

@Component
public class Util{

	public static boolean isEmptyOrNull(String str){
		return null == str || "".equals(str);
	}
	
}
