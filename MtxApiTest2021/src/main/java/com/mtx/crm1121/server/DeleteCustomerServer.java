package com.mtx.crm1121.server;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.net.URISyntaxException;
import java.util.HashMap;
import java.util.Map;

import org.apache.http.client.ClientProtocolException;

import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.JSONReader;
import com.mtx.http.MtxHttpClient;
import com.mtx.util.PropertyUtil;

public class DeleteCustomerServer {
	static String paramPath = "src/main/resources/crmparams/DeleteCustomerByIds.properties";
    static String urlPath = "/CrmCustomer/deleteByIds";
    
    public static String deleteContracter(String uri,String token) throws ClientProtocolException, URISyntaxException, IOException {
    	Map<String,String> headers = new HashMap<String, String>();
    	headers.put("Admin-Token", token);
    	Map<String, String> params = new HashMap<String, String>();
		params = PropertyUtil.getAllKeyValues(paramPath);
    	return MtxHttpClient.sendPost(uri+urlPath, params, headers);
    }
    
    public static String deleteContracter(String uri,String token,String key,String value) throws ClientProtocolException, URISyntaxException, IOException {
    	Map<String,String> headers = new HashMap<String, String>();
    	headers.put("Admin-Token", token);
    	Map<String, String> params = new HashMap<String, String>();
		params = PropertyUtil.getAllKeyValues(paramPath);
		params.put(key, value);
    	return MtxHttpClient.sendPost(uri+urlPath, params, headers);
    }
    
    public static String deleteContracter(String uri,String token,String key) throws ClientProtocolException, URISyntaxException, IOException {
    	Map<String,String> headers = new HashMap<String, String>();
    	headers.put("Admin-Token", token);
    	Map<String, String> params = new HashMap<String, String>();
		params = PropertyUtil.getAllKeyValues(paramPath);
		params.remove(key);
    	return MtxHttpClient.sendPost(uri+urlPath, params, headers);
    }
}
