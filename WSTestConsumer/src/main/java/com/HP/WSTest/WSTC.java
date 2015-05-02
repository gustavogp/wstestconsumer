package com.HP.WSTest;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

import com.fasterxml.jackson.databind.ObjectMapper;

public class WSTC {

	public static void main(String[] args) {
		
		WSTC WSConsumer = new WSTC();
		
		WSConsumer.retrieveJson();
	}
	
		
	public boolean retrieveJson() {
			ObjectMapper mapper = new ObjectMapper();
			boolean jsonRetrieved = false;
			URL webservice_url;
			MyProp consumer = new MyProp();
			
			try {
				webservice_url = new URL("http://localhost:8080/WSTest/myresource/json");
				HttpURLConnection urlCon = (HttpURLConnection) webservice_url.openConnection();
				consumer = mapper.readValue(new BufferedReader(new InputStreamReader(urlCon.getInputStream())), MyProp.class);
				
				for (MyPropType mtp : consumer.myPropList) {
					System.out.println("Count: " + mtp.getCount() + ", User Name: " + mtp.getUserName());
				}
				
			} catch (MalformedURLException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
			return jsonRetrieved;
	}


}
