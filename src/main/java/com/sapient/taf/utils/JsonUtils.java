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
	
	public <T> T readJson() throws FileNotFoundException {
		return this.readJson(getJson());
	}

	public <T> T readJson(File json) throws FileNotFoundException {
		Type TYPE = new TypeToken<T>() {
			private static final long serialVersionUID = -1478317707606547912L;
		}.getType();

		GsonBuilder builder = new GsonBuilder();
		builder.setPrettyPrinting().serializeNulls();
		Gson gson = builder.create();
		JsonReader jReader = new JsonReader(new FileReader(json));
		return gson.fromJson(jReader, TYPE);
	}
}