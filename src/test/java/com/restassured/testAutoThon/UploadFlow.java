package com.restassured.testAutoThon;

import java.io.IOException;

import org.testng.annotations.Test;

import com.app.pages.LoginPage;
import com.app.pages.UploadToBerkeleyPage;

public class UploadFlow {
	
	@Test
	public void uploadTestFlow() throws IOException {
		
		UploadToBerkeleyPage objUpload = new UploadToBerkeleyPage();
		objUpload.loadPage();
		objUpload.uploadFile();
		objUpload.enterNotes();
		objUpload.submit();
		
		
	}

}
