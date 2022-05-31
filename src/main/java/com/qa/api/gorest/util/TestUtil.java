package com.qa.api.gorest.util;

import org.apache.commons.lang3.RandomStringUtils;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class TestUtil {
	
	public static String getSerializedJSON(Object obj) {
		ObjectMapper mapper = new ObjectMapper();
		String jsonString = null;
		
		try {
			jsonString = mapper.writeValueAsString(obj);
			System.out.println("JSON Body PayLoad ===> "+ jsonString);
		}catch(JsonProcessingException e) {
			e.printStackTrace();
		}
		return jsonString;
		
	}
	
	/**
	 * @author 
	 * @Date 
	 * @Purpose This method will generate a random integer
	 * @param length --> the length of the random emails we want to generate
	 * @return method will return a random email String
	 */
	public static String generateRandomEmail() {
	    String allowedChars = "abcdefghijklmnopqrstuvwxyz" + "1234567890" + "_-.";
	    String email = "";
	    String temp = RandomStringUtils.random(20, allowedChars);
	    email = temp.substring(0, temp.length() - 9) + "@testdata.com";
	    return email;
	}
	
}
