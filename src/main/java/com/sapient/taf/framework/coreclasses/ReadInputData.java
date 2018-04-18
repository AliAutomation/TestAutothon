package com.sapient.taf.framework.coreclasses;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class ReadInputData {
	
	    private Properties inputData;



	    public Properties getOrFile() {
	        return inputData;
	    }


	    public void setOrFile(Properties ioFile) {
	        this.inputData = ioFile;
	    }

	    public ReadInputData(String inputData) throws IOException {
	        this.inputData = new Properties();
	        this.inputData.load(new FileInputStream(FrameworkConstants.testDataFolder + File.separator + inputData));


	    }

	    public String getDirectProperty(String propname) {
	        return inputData.getProperty(propname, null);
	    }



	}


