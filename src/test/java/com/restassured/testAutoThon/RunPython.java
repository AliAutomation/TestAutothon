package com.restassured.testAutoThon;

import java.io.IOException;

public class RunPython {
	
    public static void main(String[] args) throws IOException 
    {
       // String command = "cmd /c python C:\\Python27\\API\\twitter.py";
    	String command = "cmd /c C:\\automationFramework\\automationFramework\\twitter.py" ;
     //   String command = "cmd /c python C:\\Users\\aasgh2\\AppData\\Local\\Programs\\Python\\Python37-32\\API\\twitter.py";
    	//String command = "cmd.exe\", \"/c\", \"C:\\automationFramework\\automationFramework\\twitter.py" ;
   
        Process p = Runtime.getRuntime().exec(command);
        System.out.println("Done");
        //System.out.println(command);
                     
 }




}
