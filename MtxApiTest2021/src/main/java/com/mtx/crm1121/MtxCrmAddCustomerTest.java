package com.mtx.crm1121;

import java.io.IOException;
import java.net.URISyntaxException;

import org.apache.http.client.ClientProtocolException;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONPath;
import com.mtx.util.StringUtil;

public class MtxCrmAddCustomerTest {
CloseableHttpClient httpClient;
String adminToken;
	
	@BeforeClass
	public void init() {
		httpClient = HttpClients.createDefault();
	}
	
	@Test
	public void test01_login() throws ClientProtocolException, URISyntaxException, IOException {
		String res =  MtxCrmLogin.login(httpClient, "admin", "123456");
		Assert.assertTrue(res.contains("\"code\":0"));
//		String leftBoundary = "\"Admin-Token\":\"";
//      String rightBoundary = "\",\"user\"";
//      adminToken = StringUtil.getStrByRLBoundary(res, leftBoundary, rightBoundary);
		//通过jsonpath获取token
		adminToken = (String) JSONPath.extract(res, "$.Admin\\-Token");  //-需要转义，表达式需区分大小写
		
	}
	
	@Test(dependsOnMethods = "test01_login")
	public void test02_addCustomer() throws ClientProtocolException, URISyntaxException, IOException {
		String res = MtxCrmAddCustomer.addCumstomer(httpClient,adminToken);
		Assert.assertTrue(res.contains("\"code\":0"));
		String customerName = (String) JSONPath.extract(res, "$.data.customerName");//绝对路径
		System.out.println(customerName);
		JSONArray customerIds = (JSONArray) JSONPath.extract(res, "$..customerId");  //相对路径，获取的结果为JSONArray
		int customerId = (Integer) customerIds.get(0);
		System.out.println(customerId);
	}
}
