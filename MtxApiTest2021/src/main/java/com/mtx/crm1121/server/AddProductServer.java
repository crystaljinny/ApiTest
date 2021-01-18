package com.mtx.crm1121.server;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.net.URISyntaxException;
import java.util.HashMap;
import java.util.Map;

import org.apache.http.client.ClientProtocolException;

import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.JSONPath;
import com.alibaba.fastjson.JSONReader;
import com.mtx.http.MtxHttpClient;
import com.mtx.util.RandomUtil;

public class AddProductServer {
	static String paramPath = "src/main/resources/crmparams/AddProduct.json";
    static String urlPath = "/CrmProduct/saveAndUpdate";
    
    public static String addProduct(String uri,String token) throws ClientProtocolException, URISyntaxException, IOException {
    	Map<String,String> headers = new HashMap<String, String>();
    	headers.put("Admin-Token", token);
    	JSONReader jsonReader = new JSONReader(new FileReader(new File(paramPath)));
    	String readString = jsonReader.readString();
    	JSONObject paramsJsonObject = JSONObject.parseObject(readString);
    	JSONPath.set(paramsJsonObject, "$.entity.name", RandomUtil.getRanStrAndNumberByLen(5));
    	JSONPath.set(paramsJsonObject, "$.entity.num", RandomUtil.getRndNumByLen(5));
    	return MtxHttpClient.sendPostJson(uri+urlPath, paramsJsonObject, headers);
    }
    
    public static String addProduct(String uri,String token,String jsonPath,Object value) throws ClientProtocolException, URISyntaxException, IOException {
    	Map<String,String> headers = new HashMap<String, String>();
    	headers.put("Admin-Token", token);
    	JSONReader jsonReader = new JSONReader(new FileReader(new File(paramPath)));
    	String readString = jsonReader.readString();
    	JSONObject paramsJsonObject = JSONObject.parseObject(readString);
    	JSONPath.set(paramsJsonObject, "$.entity.name", RandomUtil.getRanStrAndNumberByLen(5));
    	JSONPath.set(paramsJsonObject, "$.entity.num", RandomUtil.getRndNumByLen(5));
    	JSONPath.set(paramsJsonObject, jsonPath, value);  //改指定jsonPath下的value
    	return MtxHttpClient.sendPostJson(uri+urlPath, paramsJsonObject, headers);
    }
    
    public static String addProduct(String uri,String token,String jsonPath) throws ClientProtocolException, URISyntaxException, IOException {
    	Map<String,String> headers = new HashMap<String, String>();
    	headers.put("Admin-Token", token);
    	JSONReader jsonReader = new JSONReader(new FileReader(new File(paramPath)));
    	String readString = jsonReader.readString();
    	JSONObject paramsJsonObject = JSONObject.parseObject(readString);
    	JSONPath.set(paramsJsonObject, "$.entity.name", RandomUtil.getRanStrAndNumberByLen(5));
    	JSONPath.set(paramsJsonObject, "$.entity.num", RandomUtil.getRndNumByLen(5));
        JSONPath.remove(paramsJsonObject, jsonPath);  //删除key
    	return MtxHttpClient.sendPostJson(uri+urlPath, paramsJsonObject, headers);
    }
    
    public static String addProduct(String uri,String token,JSONObject params) throws ClientProtocolException, URISyntaxException, IOException {
    	Map<String,String> headers = new HashMap<String, String>();
    	headers.put("Admin-Token", token);
    	JSONPath.set(params, "$.entity.name", RandomUtil.getRanStrAndNumberByLen(5));
    	JSONPath.set(params, "$.entity.num", RandomUtil.getRndNumByLen(5));
    	return MtxHttpClient.sendPostJson(uri+urlPath, params, headers);
    }
    
}
