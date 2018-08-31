package com.co.pubb.controller;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.text.DateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Handles requests for the application home page.
 */
@Controller
public class PubbController {
	
	private static final Logger logger = LoggerFactory.getLogger(PubbController.class);
	private static final String computedURL = "https://api.pubg.com/shards/pc-as";
	private static final String Key = "eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJqdGkiOiJkNzg1ZGQzMC04ZDdlLTAxMzYtN2YzOC01Nzc2ZmI4NTM3NWMiLCJpc3MiOiJnYW1lbG9ja2VyIiwiaWF0IjoxNTM1NTIyNDY2LCJwdWIiOiJibHVlaG9sZSIsInRpdGxlIjoicHViZyIsImFwcCI6IndvbyJ9.cpmCSZaOue4PSeU0lBgS1qhtKfKYva-Tld8RGzPPhKs";
	
	
	@RequestMapping(value = "/PUBG", method = RequestMethod.GET)
	public String testPUBG() {
		logger.info("Enter PUBG() / Request GET");		
		
		return "pubgTest";
	}
	
	@SuppressWarnings("resource")
	@RequestMapping(value = "/PUBG/player", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, String> player(@RequestParam String name) throws Exception{
		logger.info("Enter player() / Request POST");
		
		Map<String, String> resultMap = new HashMap<String, String>();
		InputStream is = null;
		InputStreamReader isr = null;
		BufferedReader br = null;
		
		HttpURLConnection player = (HttpURLConnection) new URL(computedURL + "/players?filter[playerNames]=" + name).openConnection();
		player.setRequestMethod("GET");
		player.setRequestProperty("Authorization","Bearer " + Key);
		player.setRequestProperty("Accept", "application/vnd.api+json");
		
		is = player.getInputStream();
		isr = new InputStreamReader(is);
		br = new BufferedReader(isr);
		
        String userInput = br.readLine();
        System.out.println("user input :"+userInput);
        
        resultMap.put("player", userInput);
        
        is.close();
        br.close();
        
        
        
        HttpURLConnection sample = (HttpURLConnection) new URL(computedURL + "/matches/51d37850-9333-4182-952e-d9b3c736a537").openConnection();
        sample.setRequestMethod("GET");
        sample.setRequestProperty("Authorization","Bearer " + Key);
        sample.setRequestProperty("Accept", "application/vnd.api+json");
		
		is = sample.getInputStream();
		isr = new InputStreamReader(is);
		br = new BufferedReader(isr);
		
        String userInput2 = br.readLine();
        System.out.println("user input :"+userInput2);
        
        resultMap.put("matches", userInput2);
        
        
        JSONParser parser = new JSONParser(); 
        Object obj = parser.parse( userInput ); 


        JSONObject jsonObj = (JSONObject) obj; 
        
        
        return resultMap;

	}
	
}