package com.soap;

import static com.jayway.restassured.RestAssured.given;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;

import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.InputStreamEntity;
import org.apache.http.impl.client.HttpClientBuilder;

import com.jayway.restassured.http.ContentType;
import com.jayway.restassured.response.Response;
import com.restassured.PostService;
import com.restassured.RequestWrapper;
import com.restassured.ResponseWrapper;
import com.sapient.taf.log.LoggerFactory;
import com.sapient.taf.log.MyLogger;
import com.soap.lib.SoapRequestWrapper;
import com.soap.lib.SoapResponseWrapper;

 

public class SoapResponse {

	//String endPoint="http://www.dneonline.com/calculator.asmx?WSDL";
	
	
	private SoapResponseWrapper response = new SoapResponseWrapper();
	private final static MyLogger logger = LoggerFactory.getLogger(PostService.class);

	public SoapResponseWrapper getResponse(SoapRequestWrapper request, String testAPIName) throws ClientProtocolException, IOException {

		//Get Params
		String result=null;
		String endPoint = request.getEndPoint();
		
		File xmlFile = request.getRequestBody();
		
		HttpClient client=HttpClientBuilder.create().build();
		
		
		HttpPost post=new HttpPost(endPoint);
		
		post.setEntity(new InputStreamEntity(new FileInputStream(xmlFile)));
		
		post.setHeader("Content-type","text/xml");
		
		HttpResponse response=client.execute(post);
		
		BufferedReader br=new BufferedReader(new InputStreamReader(response.getEntity().getContent()));
		
		String line="";
		
		StringBuffer sb =new StringBuffer();
		
		while((line=br.readLine())!=null)
		{
			sb.append(line);
		}
		PrintWriter pw = new PrintWriter(System.getProperty("user.dir")+"//src//test//resources//soapActualResponses//"+ testAPIName +".xml");

		pw.write(sb.toString());
		
		pw.close();
		
		pw.flush();

		

		this.response.setResponseCode(String.valueOf(response.getStatusLine().getStatusCode()));
	
		System.out.println("response Code" + this.response.getResponseCode());
		//this.response.setResponseBody(response.getEntity().getContent());
//response.getEntity()
		return this.response;
	}
	/*public void getResponse() throws ClientProtocolException, IOException {
		
		
		File requestFile=new File(System.getProperty("user.dir")+"//src//main//resources//soapRequests//requestPost.xml");
		
		
		HttpClient client=HttpClientBuilder.create().build();
		
		
		HttpPost post=new HttpPost(endPoint);
		
		post.setEntity(new InputStreamEntity(new FileInputStream(requestFile)));
		
		post.setHeader("Content-type","text/xml");
		
		HttpResponse response=client.execute(post);
		
		
		System.out.println(response.getStatusLine().getStatusCode());
		
		BufferedReader br=new BufferedReader(new InputStreamReader(response.getEntity().getContent()));
		
		String line="";
		
		StringBuffer sb =new StringBuffer();
		
		while((line=br.readLine())!=null)
		{
			sb.append(line);
		}
		PrintWriter pw = new PrintWriter(System.getProperty("user.dir")+"//src//main//resources//soapResponses//response.xml");

		pw.write(sb.toString());
		
		pw.close();
		
		pw.flush();
	
	}
	
	public static void main(String[] args) throws ClientProtocolException, IOException {
		
		Respone ps=new Respone();
		
		ps.getResponse();
	}*/
}
