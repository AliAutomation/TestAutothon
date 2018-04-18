package com.restassured.lib;

import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import com.google.gson.JsonObject;
import org.junit.Assert;

public class JSONUtil {

	public static boolean compareJSONs(String expectedJSON, String actualJSON){

		JsonParser parser = new JsonParser();
		JsonElement jsonObj1 = parser.parse(actualJSON);
		JsonElement jsonObj2 = parser.parse(expectedJSON);

		System.out.println("actualJsonObj1: \n" + jsonObj1);
		System.out.println("expectedJsonObj2: \n" + jsonObj2);

		return jsonObj1.equals(jsonObj2);
	}

	private static String getJsonElement(String jsonString, String jsonKey){
		JsonParser jsonParser = new JsonParser();
		try{
			JsonElement jsonElement = jsonParser.parse(jsonString);
			String jsonMember = jsonElement.getAsJsonObject().get(jsonKey).toString();
   			return jsonMember;
		}catch(Exception e){
			Assert.fail("Json missing or invalid key or unable to parse");
			return null;
		}
	}

	public static String getJsonKeyValue(String jsonString, String jsonKey){

		JsonParser jsonParser = new JsonParser();
		String[] jsonKeys = jsonKey.split("\\.", -1);

		//Check whether jsonkey is nested by looking for the presence of "."
		//If the key is not nested then directly fetch jsonElement
		boolean nestedJsonKey = jsonKey.contains(".");
		if(!nestedJsonKey){return getJsonElement(jsonString, jsonKey);}

		try{
			JsonElement jsonElements = jsonParser.parse(jsonString);
			JsonObject asJsonObject = jsonElements.getAsJsonObject();
			JsonObject jsonObject=null;
			String jsonElementValue = null;

			for (int i=1; i<=jsonKeys.length; i++ ) {
				if (i != jsonKeys.length) {
					jsonObject = asJsonObject.getAsJsonObject(jsonKeys[0]);
					System.out.println("Intermediate Object Value " + i + ": " + jsonObject);
				} else {
					jsonElementValue = jsonObject.get(jsonKeys[1]).toString();
					System.out.println("Json Element: " + jsonElementValue);
				}
			}
			return jsonElementValue;
		}catch(Exception e){
			Assert.fail("Json missing or invalid key or unable to parse");
			return null;
		}
	}
}
