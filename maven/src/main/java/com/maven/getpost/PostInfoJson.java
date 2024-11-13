package com.maven.getpost;

import org.json.JSONArray;
import org.json.JSONObject;

public class PostInfoJson {
    private String strPostJson = null;
	
	public PostInfoJson(String strPostJson) {
		this.strPostJson = strPostJson;
	}
	
	public String[] getPostInfoArray() {
		
		String[] Response = new String[4];
		
		// JSON analysis
		JSONObject jsonObject = new JSONObject(strPostJson);
        
        // results field, and isnull
        if (!jsonObject.isNull("results")) {
        	
        	JSONArray results = jsonObject.getJSONArray("results");
        	JSONObject result = results.getJSONObject(0);
        	
        	Response[0] = "郵便番号:" + result.getString("zipcode");
        	Response[1] = "住所(漢字):" + result.getString("address1") + " " + result.getString("address2") + " " + result.getString("address3");
        	Response[2] = "住所(ｶﾅ):" + result.getString("kana1") + " " + result.getString("kana2") + " " + result.getString("kana3");
        	Response[3] = "都道府県コード:" + result.getString("prefcode");
        	
        }else {
        	
            String message = jsonObject.isNull("message") ? null : jsonObject.getString("message");
            String results = jsonObject.isNull("results") ? null : jsonObject.getString("results");
            int status = jsonObject.getInt("status");
        	
        	Response[0] = "入力した郵便番号から住所は存在しませんでした。";
        	Response[1] = "message:" + message;
        	Response[2] = "results:" + results;
        	Response[3] = "status:" + status;
        	
        }
        
		return Response;
	}
}
