package com.app.web.objectrepo;

import org.openqa.selenium.By;

public interface UploadToBerkeleyPageRepo {

		By inpChooseFile= By.xpath("//input[@name='upfile']");
		By inpNote= By.xpath("//input[@name='note']");
		By inpSubmit =By.xpath("//input[@type='submit']");
		
}
