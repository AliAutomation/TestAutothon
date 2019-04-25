package com.restassured.testAutoThon;

        import oauth.signpost.OAuthConsumer;
        import oauth.signpost.commonshttp.CommonsHttpOAuthConsumer;

        import org.apache.commons.io.IOUtils;
        import org.apache.http.HttpResponse;
        import org.apache.http.client.HttpClient;
        import org.apache.http.client.methods.HttpGet;
        import org.apache.http.impl.client.DefaultHttpClient;


public class getTwitterResponse {



    static String AccessToken = "1121274138587480064-ZNQrrGySQC5A5XXwp4gOpbKpPhyaqe";
    static String AccessSecret = "GpwziOnjOMQwjk4cVjRQbdAs9XLFD6sKhhUYXv3330EnC";
    static String ConsumerKey = "F093ZDKCTAcu8PM3Art7nDhWF";
    static String ConsumerSecret = "sSO12aZ1eI6tpRbs2P6vGC1W9z5LoT4mvGNJYJO5sWnwvGx98l";

    /**
     * @param args
     */
    public static void main(String[] args) throws Exception {
        json_read obj = new json_read();
        OAuthConsumer consumer = new CommonsHttpOAuthConsumer(
                ConsumerKey,
                ConsumerSecret);

        consumer.setTokenWithSecret(AccessToken, AccessSecret);
        HttpGet request = new HttpGet("https://api.twitter.com/1.1/statuses/home_timeline.json");
        consumer.sign(request);

        HttpClient client = new DefaultHttpClient();
        HttpResponse response = client.execute(request);

        int statusCode = response.getStatusLine().getStatusCode();
        String responsebody = IOUtils.toString(response.getEntity().getContent());
        System.out.println("responsebody#########" + ":" + responsebody);
        System.out.println(statusCode + ":" + response.getStatusLine().getReasonPhrase());
        System.out.println("response######" + ":" + response.getEntity().getContent());
//        json_read.readdata(IOUtils.toString(response.getEntity().getContent()));
    }


}


