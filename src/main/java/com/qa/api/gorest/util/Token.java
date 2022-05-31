package com.qa.api.gorest.util;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

import java.util.HashMap;
import java.util.Map;

import org.testng.annotations.Test;

import io.restassured.path.json.JsonPath;

public class Token {

	public static Map<Object, Object> appTokenMap;
	public static Map<String, String> tokenMap = new HashMap<String, String>();
	public static String clientID = "d8a73d6b141e1d4";

	/*
	 * This function generate token for Auth 2.0 API
	 */
	public static Map<Object, Object> getAccessToken() {

		Map<String, String> formparams = new HashMap<String, String>();
		formparams.put("refresh_token", "f9f92c0db11930c185c6807218f1919e22f1b193");
		formparams.put("client_id", "d8a73d6b141e1d4");
		formparams.put("client_secret", "540acb1f9dfb077a709a30d4e329a12d116116f2");
		formparams.put("grant_type", "refresh_token");

		JsonPath tokenJson = 
				given()
					.formParams(formparams)
				.when()
					.post("https://api.imgur.com/oauth2/token")
				.then()
					.extract().jsonPath();

		System.out.println(tokenJson.getMap(""));

		appTokenMap = tokenJson.getMap("");
		return appTokenMap;
	}

	public static Map<String, String> getAuthToken() {

		String authToken = appTokenMap.get("access_token").toString();
		System.out.println("Auth Token ====> " + authToken);

		tokenMap.put("Authorization", "Bearer " + authToken);
		return tokenMap;

	}

	public static Map<String, String> getClientID() {

		System.out.println("Client ID ====> " + clientID);

		tokenMap.put("Authorization", "Client-ID " + clientID);
		return tokenMap;

	}

	/*
	 * public static void main(String[] args) { getAccessToken(); }
	 */

}
