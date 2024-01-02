package com.Response;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

public class CustomeResponse {
	
	public static ResponseEntity<Object> response(String message,HttpStatus status, Object object){
		Map<String , Object> map = new HashMap<>();
		map.put("message", message);
		map.put("Status", status.value());
		map.put("Data", object);
		return new ResponseEntity<Object>(map,status);
	}
	
	public static ResponseEntity<Object> alreadyExist(String message,HttpStatus status, boolean result){
		Map<String , Object> map = new HashMap<>();
		map.put("message ", message);
		map.put("Status ", status.value());
		map.put(" Employee already exists ", result);
		return new ResponseEntity<Object>(map,status);
	}
	
	public static ResponseEntity<Object> deleteResponse(String message,HttpStatus status, String message1){
		Map<String , Object> map = new HashMap<>();
		map.put("message", message);
		map.put("Status", status.value());
		map.put("message1", message1);
		return new ResponseEntity<Object>(map,status);
	}
}
