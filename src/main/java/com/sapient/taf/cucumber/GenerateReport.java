package com.sapient.taf.cucumber;

import net.masterthought.cucumber.ReportBuilder;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.velocity.exception.VelocityException;

import com.sapient.taf.framework.coreclasses.BaseClassCucumber;
import freemarker.template.Configuration;

public class GenerateReport {
	public static void GenerateMasterthoughtReport() throws VelocityException, IOException {
		File reportOutputDirectory = new File("target/Masterthought");
		List<String> jsonFiles = new ArrayList<>();

		List<String> list = new ArrayList<String>();

		for (String test : BaseClassCucumber.tests) {
			System.out.println("Test Name" + test);
			list.add("target/reports/" + test + ".json");
		}

		/*
		 * jsonFiles.add("target/reports/cucumber-results1.json");
		 * jsonFiles.add("target/reports/cucumber-results2.json");
		 * jsonFiles.add("target/reports/cucumber-results3.json");
		 */

		String pluginUrlPath = "";
		String buildNumber = "";
		String buildProject = "cucumber-jvm";
		boolean skippedFails = true;
		boolean pendingFails = true;
		boolean undefinedFails = true;
		boolean missingFails = true;
		boolean flashCharts = true;
		boolean runWithJenkins = false;
		boolean highCharts = false;
		boolean parallelTesting = true;

		ReportBuilder reportBuilder = new ReportBuilder(list, reportOutputDirectory, pluginUrlPath, buildNumber,
				buildProject, skippedFails, pendingFails, undefinedFails, missingFails, flashCharts, runWithJenkins,
				highCharts, parallelTesting);

		reportBuilder.generateReports();

	}
}
