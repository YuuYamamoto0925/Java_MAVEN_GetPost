package com.maven.main;

import com.maven.getpost.GetPostCodeAPI;
import com.maven.getpost.PostCodeCheck;
import com.maven.getpost.PostInfoJson;

public class Exec {
    public static void main(String[] args) {
		
        // arglengthcheck
    	if(args.length != 1) {
    		System.out.println("一つの引数(郵便番号のみ)設定してください。");
    		System.out.println("処理を終了します");
    		return;
    	}
		
		String postCode = args[0];
    	String[] postInfo = null;

    	// postcodecheck
    	if(!PostCodeCheck.isValidZipCode(postCode)) {
    		System.out.println("郵便番号の指定が間違っています。");
    		System.out.println("処理を終了します");
    		return;
    	}
    	
    	// postcodeapi
    	GetPostCodeAPI postAPI = new GetPostCodeAPI(postCode);
    	String strPostJson = postAPI.GetStrPost_Json();
    	
    	// resultJsonProcessing
    	if(strPostJson != null) {
    		PostInfoJson strPostInfo = new PostInfoJson(strPostJson);
    		postInfo = strPostInfo.getPostInfoArray();
    	}else {
    		System.out.println("正常にレスポンスを取得できません。");
    		System.out.println("処理を終了します");
    		return;
    	}
    	
    	// postinforesult
    	if(postInfo != null) {
    		System.out.println("[検索結果を出力します]");
    		for(String result:postInfo) {
        		System.out.println(result);
        	}
    	}
    }
}
