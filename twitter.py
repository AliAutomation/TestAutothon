# -*- coding: utf-8 -*-
"""
Created on Thu Apr 25 10:34:46 2019

@author: viksindh
"""

# Import the necessary package to process data in JSON format
try:
    import json
except ImportError:
    import simplejson as json

# Import the tweepy library
import tweepy
import ast

# Variables that contains the user credentials to access Twitter API 
ACCESS_TOKEN = '1121274138587480064-ZNQrrGySQC5A5XXwp4gOpbKpPhyaqe'
ACCESS_SECRET = 'GpwziOnjOMQwjk4cVjRQbdAs9XLFD6sKhhUYXv3330EnC'
CONSUMER_KEY = 'F093ZDKCTAcu8PM3Art7nDhWF'
CONSUMER_SECRET = 'sSO12aZ1eI6tpRbs2P6vGC1W9z5LoT4mvGNJYJO5sWnwvGx98l'

# Setup tweepy to authenticate with Twitter credentials:

auth = tweepy.OAuthHandler(CONSUMER_KEY, CONSUMER_SECRET)
auth.set_access_token(ACCESS_TOKEN, ACCESS_SECRET)

# Create the api to connect to twitter with your creadentials
api = tweepy.API(auth, wait_on_rate_limit=True, wait_on_rate_limit_notify=True, compression=True)
for status in tweepy.Cursor(api.home_timeline).items(5):
	print(status._json)
	f=open("Response.json","w")
	f.write(str(status._json))
	
