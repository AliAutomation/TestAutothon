package com.sapient.taf.framework.coreclasses;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.net.MalformedURLException;
import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.IInvokedMethod;
import org.testng.ITestContext;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.sapient.taf.drivermanager.Driver;
import com.sapient.taf.drivermanager.DriverFactory;
import com.sapient.taf.drivermanager.DriverManager;
import com.sapient.taf.reporting.IReportComm;
import com.sapient.taf.reporting.ReportUtil;

import cucumber.api.testng.CucumberFeatureWrapper;
import cucumber.api.testng.TestNGCucumberRunner;


public class BaseClassCucumber{

	
	public static TestNGCucumberRunner testNGCucumberRunner;
	
	public static List<String> tests = new ArrayList<String>();
	public static String currentClass;
	//	protected IReportComm reportUtil=new ReportUtil();
	
	/*protected ReadInputData inputData;
    private static final String inputDataFile = "testData.properties";
    
    
    protected BaseClassCucumber() {


        try {
            inputData = new ReadInputData(inputDataFile);
        } catch (IOException e) {
            e.printStackTrace();
        }    }*/
	

    
	@BeforeMethod
	public void start(Method method, ITestResult testResult) throws MalformedURLException, InvocationTargetException, InstantiationException, IllegalAccessException, NoSuchMethodException, SecurityException, IllegalArgumentException, FileNotFoundException {
		
		System.out.println("Inside");

				DriverManager.setWebDriver(DriverFactory
						.createInstance("chrome"));
			
			
		
			
			
	}
	
	@AfterMethod
	public void end() {
		
		DriverManager.getDriver().quitDriver();
		
		
	}
	
	
	/**
	 * @return returns two dimensional array of {@link CucumberFeatureWrapper}
	 *         objects.
	 */
	@DataProvider
	public Object[][] features() {
		return testNGCucumberRunner.provideFeatures();
	}

	
	@BeforeClass
	public void test(ITestContext context) {
		String className = context.getCurrentXmlTest().getSuite().getClass().getName();
		currentClass = this.getClass().getSimpleName();

		System.out.println("Class Name" +currentClass);
		testNGCucumberRunner = new TestNGCucumberRunner(this.getClass());
		 //reportUtil=new ReportUtil();
//System.out.println("report Util " + reportUtil);
		
		
	}
	
	@AfterTest
	public void end2() {
		tests.add(currentClass);
		testNGCucumberRunner.finish();
	}
	
	
	@Test(groups = "cucumber", description = "Runs Cucumber Feature", dataProvider = "features")
	public void feature(CucumberFeatureWrapper cucumberFeature) {

		testNGCucumberRunner.runCucumber(cucumberFeature.getCucumberFeature());
	}

}
