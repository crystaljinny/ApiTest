package com.mtx.crm1121.server;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.Map;

import org.apache.http.client.ClientProtocolException;

import com.mtx.http.MtxHttpClient;
import com.mtx.util.PropertyUtil;

public class LoginServer {
    static String paramPath = "src/main/resources/crmparams/login.properties";
    static String urlPath = "/login";
    public static String login(String uri) throws ClientProtocolException, URISyntaxException, IOException {
    	Map<String, String> params = PropertyUtil.getAllKeyValues(paramPath);
    	String res = MtxHttpClient.sendPost(uri+urlPath, params);
    	return res;
    }
    
    public static String login(String uri,String key,String value) throws ClientProtocolException, URISyntaxException, IOException {
    	Map<String, String> params = PropertyUtil.getAllKeyValues(paramPath);
    	params.put(key, value);
    	String res = MtxHttpClient.sendPost(uri+urlPath, params);
    	return res;
    }
}
