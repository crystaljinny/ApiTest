package com.mtx.apitest;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.Properties;

import org.apache.http.client.ClientProtocolException;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.alibaba.fastjson.JSONObject;
import com.mtx.crm1121.CrmTestBase;
import com.mtx.crm1121.server.AddCustomerServer;
import com.mtx.crm1121.server.LoginServer;
import com.mtx.util.JsonPathUtil;
import com.mtx.util.PropertyUtil;

public class AddCustomerTests extends CrmTestBase{   
    @Test(description = "mtx-crm-添加客户-正例")
    public void test01_addCustomer() throws ClientProtocolException, URISyntaxException, IOException {
    	String res = AddCustomerServer.addCustomer(uri, token);
    	Object extract = JsonPathUtil.extract(res, "$.code");
    	Assert.assertEquals(extract, 0);
    }
    
    @Test(description = "mtx-crm-添加客户-反例-客户名称为空")
    public void test02_addCustomer() throws ClientProtocolException, URISyntaxException, IOException {
    	String res = AddCustomerServer.addCustomer(uri, token, "$.entity.customer_name","");
    	Object extract = JsonPathUtil.extract(res, "$.code");
    	Assert.assertEquals(extract, 0);
    }
    
    @Test(description = "mtx-crm-添加客户-反例-手机号超长")
    public void test03_addCustomer() throws ClientProtocolException, URISyntaxException, IOException {
    	String res = AddCustomerServer.addCustomer(uri, token, "$.entity.mobile","135815876765897");
    	Object extract = JsonPathUtil.extract(res, "$.code");
    	Assert.assertEquals(extract, 0);
    }
    
    @Test(description = "mtx-crm-添加客户-反例-客户名称必填字段缺失")
    public void test04_addCustomer() throws ClientProtocolException, URISyntaxException, IOException {
    	String res = AddCustomerServer.addCustomer(uri, token, "$.entity.customer_name");
    	Object extract = JsonPathUtil.extract(res, "$.code");
    	Assert.assertEquals(extract, 500);
    }
    
    
    
}
