package com.sapient.taf.framework.coreclasses;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

import org.openqa.selenium.By;

public class ObjectRepository {
	private Properties orFile;

	public Properties getOrFile() {
		return orFile;
	}

	public void setOrFile(Properties orFile) {
		this.orFile = orFile;
	}

	public ObjectRepository(String orFile) throws IOException {
		this.orFile = new Properties();
		this.orFile.load(new FileInputStream(FrameworkConstants.objectRepoFolder + File.separator + orFile));
	}

	public String getDirectProperty(String propname) {
		return orFile.getProperty(propname, null);
	}

	public By getLocator(String locatorName) {
		String orTypeValue[] = orFile.getProperty(locatorName, null).split(FrameworkConstants.orTypeValueSplitter);
		return getLocator(orTypeValue[0], orTypeValue[1]);
	}

	private By getLocator(String locatorType, String locatorValue) {
		switch (locatorType.toUpperCase()) {
		case "ID":
			return By.id(locatorValue);
		case "XPATH":
			return By.xpath(locatorValue);
		case "CLASSNAME":
			return By.className(locatorValue);
		case "CSSSELECTOR":
			return By.cssSelector(locatorValue);
		case "NAME":
			return By.name(locatorValue);
		case "LINKTEXT":
			return By.linkText(locatorValue);
		case "PARTIALLINKTEXT":
			return By.partialLinkText(locatorValue);
		case "TAGNAME":
			return By.tagName(locatorValue);
		default:
			return null;
		}
	}
}