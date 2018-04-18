package com.soap.lib;

import org.apache.log4j.Logger;
import org.junit.Assert;

import java.io.FileInputStream;
import java.util.Properties;


	//Contains generic reusable components
	public class SoapCommonUtil {

		final static Logger Log = Logger.getLogger(SoapCommonUtil.class); //for logging

		//Returns value of a property from api.properties file
		public static String getPropertyValue(String sPropertyName){

			Properties oProperties= new Properties();
			String sPropFilePath= "src/test/resources/soap.properties";


			try {
				FileInputStream oPropFileInputStream=new FileInputStream(sPropFilePath);
				oProperties.load(oPropFileInputStream);
				String sPropertyvalue=oProperties.getProperty(sPropertyName);
				if (sPropertyvalue != null){
					return sPropertyvalue;
				}
				else{
					Assert.fail("Property does not exist in properties file: " + sPropertyName);
					return null;
				}

			} catch (Exception e) {
				Log.info("Properties File not found or incorrect property: " + sPropFilePath);
				Assert.fail("Properties File not found: " + sPropFilePath);
				return null;
			}

		}


		//Returns the current Environment from properties file
			public static String getEnv(){

				return getPropertyValue("env");

			}

	}



	