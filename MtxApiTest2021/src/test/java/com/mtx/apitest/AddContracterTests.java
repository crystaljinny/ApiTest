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
import com.mtx.crm1121.server.AddContracterServer;
import com.mtx.crm1121.server.LoginServer;
import com.mtx.util.JsonPathUtil;
import com.mtx.util.PropertyUtil;

public class AddContracterTests  extends CrmTestBase{
    @Test(description = "mtx-crm-添加联系人-正例")
    public void test01_addContracter() throws ClientProtocolException, URISyntaxException, IOException {
    	String res = AddContracterServer.addContracter(uri, token);
    	Object extract = JsonPathUtil.extract(res, "$.code");
    	Assert.assertEquals(extract, 0);
    }
}
