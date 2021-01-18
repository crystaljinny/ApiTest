package com.mtx.pinter1118;

import java.io.IOException;
import java.net.URISyntaxException;

import javax.naming.InitialContext;

import org.apache.http.client.ClientProtocolException;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class MtxPinterQueryTest {
	CloseableHttpClient httpClient;
	
	@BeforeClass
	public void init() {
		httpClient = HttpClients.createDefault();
	}
	
    @Test
    public void test01_query() throws ClientProtocolException, URISyntaxException, IOException {
    	String name = "admin";
    	String pwd = "123456";
    	String expect = "\"code\":\"0\",\"message\":\"success\",\"";
    	
    	String res = MtxPinterLogin.login(httpClient,name, pwd);
    	Assert.assertTrue(res.contains(expect));
    	
    	String resQ = MtxPinterQuery.query(httpClient,name);
    	Assert.assertTrue(resQ.contains(expect));
    	
    }
}
