package com.mirsis.CustomerProject.config;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

public class ResponseHandler {
	public static ResponseEntity<Object> generateResponse(HttpStatus status,String message) {
		Map<String, Object> map = new HashMap<String, Object>();
		if(status.equals(HttpStatus.OK)){
			map.put("status", true);
		}
		else{
			map.put("status", false);
		}
		map.put("message", message);

		return new ResponseEntity<Object>(map, status);
	}
}
