package com.restassured.testAutoThon;

import java.util.List;

import org.testng.annotations.Test;

import twitter4j.Query;
import twitter4j.QueryResult;
import twitter4j.Status;
import twitter4j.Twitter;
import twitter4j.TwitterException;
import twitter4j.TwitterFactory;
import twitter4j.conf.ConfigurationBuilder;

public class twitterResponse {
	
	private static final String TWITTER_CONSUMER_KEY = "F093ZDKCTAcu8PM3Art7nDhWF";
	private static final String TWITTER_SECRET_KEY = "sSO12aZ1eI6tpRbs2P6vGC1W9z5LoT4mvGNJYJO5sWnwvGx98l";
	private static final String TWITTER_ACCESS_TOKEN = "1121274138587480064-ZNQrrGySQC5A5XXwp4gOpbKpPhyaqe";
	private static final String TWITTER_ACCESS_TOKEN_SECRET = "GpwziOnjOMQwjk4cVjRQbdAs9XLFD6sKhhUYXv3330EnC";

	
    @Test(description="Twitter API") 
	/* public void twitterfeed() */
	public void twitterfeed()
	{

	
	ConfigurationBuilder cb = new ConfigurationBuilder();
	cb.setDebugEnabled(true)
	    .setOAuthConsumerKey(TWITTER_CONSUMER_KEY)
	    .setOAuthConsumerSecret(TWITTER_SECRET_KEY)
	    .setOAuthAccessToken(TWITTER_ACCESS_TOKEN)
	    .setOAuthAccessTokenSecret(TWITTER_ACCESS_TOKEN_SECRET);
	
	TwitterFactory tf = new TwitterFactory(cb.build());
	
	Twitter twitter = tf.getInstance();
	
	try {
	    Query query = new Query("MrEdPanama");
	    QueryResult result;
	    do {
	        result = twitter.search(query);
	        List<Status> tweets = result.getTweets();
	        for (Status tweet : tweets) {
	            System.out.println("@" + tweet.getUser().getScreenName() + " - " + tweet.getText());
	        }
	    } while ((query = result.nextQuery()) != null);
	    System.exit(0);
	} catch (TwitterException te) {
	    te.printStackTrace();
	    System.out.println("Failed to search tweets: " + te.getMessage());
	    System.exit(-1);
	}
	}
} 
 

