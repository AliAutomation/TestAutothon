package com.sapient.taf.framework.coreclasses;

import java.io.IOException;

import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Parameters;

import com.sapient.taf.reporting.IReportComm;
import com.sapient.taf.reporting.ReportUtil;

public class BaseClass {

	protected IReportComm reportUtil=new ReportUtil();
	protected ReadInputData inputData;
    private static final String inputDataFile = "testData.properties";

    protected BaseClass() {


        try {
            inputData = new ReadInputData(inputDataFile);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
	
	
	
}
