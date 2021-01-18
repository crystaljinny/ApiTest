package com.mtx.crm1121;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.Properties;

import org.apache.http.client.ClientProtocolException;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;

import com.alibaba.fastjson.JSONObject;

import com.mtx.crm1121.server.LoginServer;
import com.mtx.util.DBUtil;
import com.mtx.util.PropertyUtil;

public class CrmTestBase {
	public static String uri;
    public static String token;
    @BeforeClass
    public void setUp() throws ClientProtocolException, URISyntaxException, IOException {
    	Properties readProperties = PropertyUtil.readProperties("src/main/resources/http.properties");
    	uri = readProperties.getProperty("http.mtxcrm.url");
    	String res = LoginServer.login(uri);
    	JSONObject resJSONObject = JSONObject.parseObject(res);
    	token = resJSONObject.getString("Admin-Token");
    	Properties db = PropertyUtil.readProperties("src/main/resources/db.properties");
    	String urlDB = db.getProperty("db.mtxcrm.url");
    	String nameDB = db.getProperty("db.mtxcrm.username");
    	String pwdDB = db.getProperty("db.mtxcrm.password");
    	DBUtil.getConnect(urlDB, nameDB, pwdDB);
    }
    @AfterClass
    public void tearDown() {
    	DBUtil.close();
    }
}
