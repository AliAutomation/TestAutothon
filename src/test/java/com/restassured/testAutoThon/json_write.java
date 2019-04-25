package com.restassured.testAutoThon;

        // Java program to read JSON from a file

        // Java program for write JSON to a file

        import java.io.FileNotFoundException;
        import java.io.PrintWriter;
        import java.util.LinkedHashMap;
        import java.util.Map;
        import org.json.simple.JSONArray;
        import org.json.simple.JSONObject;

public class json_write
{
    public static void main(String[] args) throws FileNotFoundException
    {
        // creating JSONObject
        JSONObject jo = new JSONObject();

        // putting data to JSONObject
        jo.put("top_retweet_count", "John");
        jo.put("top_", "Smith");
        jo.put("age", 25);

        // for address data, first create LinkedHashMap
        Map m = new LinkedHashMap(4);
        m.put("streetAddress", "21 2nd Street");
        m.put("city", "New York");
        m.put("state", "NY");
        m.put("postalCode", 10021);

        // putting address to JSONObject
        jo.put("address", m);

        // for phone numbers, first create JSONArray
        JSONArray ja = new JSONArray();

        m = new LinkedHashMap(2);
        m.put("type", "home");
        m.put("number", "212 555-1234");

        // adding map to list
        ja.add(m);

        m = new LinkedHashMap(2);
        m.put("type", "fax");
        m.put("number", "212 555-1234");

        // adding map to list
        ja.add(m);

        // putting phoneNumbers to JSONObject
        jo.put("phoneNumbers", ja);

        // writing JSON to file:"JSONExample.json" in cwd
        PrintWriter pw = new PrintWriter("JSONExample.json");
        pw.write(jo.toJSONString());
        System.out.println("Write Successful");

        pw.flush();
        pw.close();
    }
}


