package com.maven.getpost;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.net.UnknownHostException;

public class GetPostCodeAPI {
    private final static String urlHost = "https://zipcloud.ibsnet.co.jp/api/search";
	private final static String urlParameter01 = "zipcode";
	private String postCode = null;

	public GetPostCodeAPI(String postCode) {
		this.postCode = postCode;
	}
	
	public String GetStrPost_Json() {
		
		String apiUrl = urlHost + "?" + urlParameter01 + "=" + postCode;
		
		try {
			
	        URL url = new URL(apiUrl);
	        
	        // HttpURLConnection Object Create
	        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
	        connection.setRequestMethod("GET");
	        
	        // response code get
	        int responseCode = connection.getResponseCode();
	        
	        if(responseCode == 200) {
	            BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream(), StandardCharsets.UTF_8));
	            String inputLine;
	            StringBuffer response = new StringBuffer();
	            
	            while ((inputLine = in.readLine()) != null) {
	                response.append(inputLine);
	            }
	            in.close();
		        
	        	return response.toString();
	        }
	        
	        return null;
	        
		} catch (UnknownHostException e) {
			System.out.println("接続先のホストと接続できません。");
			System.out.println("以下にエラーを出力します");
			e.printStackTrace();
		} catch (Exception e) {
            System.out.println("GetPostCodeAPI内の処理でエラーが発生しました。");
            System.out.println("以下にエラーを出力します");
            e.printStackTrace();
        }
		
		return null;
	}
}
