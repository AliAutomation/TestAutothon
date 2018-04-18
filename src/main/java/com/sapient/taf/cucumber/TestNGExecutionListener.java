package com.sapient.taf.cucumber;

import java.io.IOException;

import org.apache.velocity.exception.VelocityException;
import org.testng.IExecutionListener;



public class TestNGExecutionListener implements IExecutionListener {

	@Override
	public void onExecutionStart() {
		System.out.println("TestNG is staring the execution");
	}

	@Override
	public void onExecutionFinish() {
		System.out.println("Generating the Masterthought Report");
		try {
			GenerateReport.GenerateMasterthoughtReport();
		} catch (VelocityException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("TestNG has finished, the execution");

		

	}
}
