package com.sapient.taf.utils;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.lang.reflect.Type;
import com.google.common.reflect.TypeToken;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.stream.JsonReader;

public class JsonUtils {
	
	private File json;

	public File getJson() {
		return json;
	}

	public void setJson(File json) {
		this.json = json;
	}
	
	public <T> T readJson(Type T) throws FileNotFoundException {
		return this.readJson(getJson(), T);
	}

	public <T> T readJson(File json, Type T) throws FileNotFoundException {
		Type TYPE = TypeToken.of(T).getType();
		GsonBuilder builder = new GsonBuilder();
		builder.setPrettyPrinting().serializeNulls();
		Gson gson = builder.create();
		JsonReader jReader = new JsonReader(new FileReader(json));
		return gson.fromJson(jReader, TYPE);
	}
}