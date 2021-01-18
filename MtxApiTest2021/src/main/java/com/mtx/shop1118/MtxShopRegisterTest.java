package com.mtx.shop1118;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import org.apache.http.client.ClientProtocolException;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.alibaba.fastjson.JSONPath;
import com.mtx.http.MtxHttpClient;
import com.mtx.util.DBUtil;
import com.mtx.util.EncryptionsUtil;
import com.mtx.util.JsonPathUtil;
import com.mtx.util.PropertyUtil;

public class MtxShopRegisterTest {
	String host;
	String accounts = "roy";
    @BeforeClass
    public void setUp() {
    	Properties properties = PropertyUtil.readProperties("src/main/resources/http.properties");
    	host = properties.getProperty("http.mtxshop.url");
    	
    	Properties dbProperties = PropertyUtil.readProperties("src/main/resources/shopparams/db.properties");
        String url = dbProperties.getProperty("db.mtxshop.url");
        String user = dbProperties.getProperty("db.mtxshop.username");
        String password = dbProperties.getProperty("db.mtxshop.password");
    	DBUtil.getConnect(url, user, password);
    	int updateCount = DBUtil.updateData("delete from s_user where username='"+accounts+"';");
    	Assert.assertEquals(updateCount, 1);
    }
    
    @AfterClass
    public void tearDown() {
    	DBUtil.close();
    }
    
	@Test
	public void test01_register() throws ClientProtocolException, URISyntaxException, IOException {
		String uri = host + "/mtx/index.php?s=/index/user/reg.html";

		Map<String, String> params = new HashMap<String, String>();
		params = PropertyUtil.getAllKeyValues("src/main/resources/shopparams/reg.properties");
        params.put("accounts", accounts);
		
		Map<String, String> headers = new HashMap<String, String>();
		headers.put("X-Requested-With", "XMLHttpRequest");
		String res = MtxHttpClient.sendPost(uri, params, headers);
//		Assert.assertTrue(res.contains("注册成功"));
		Assert.assertEquals(JsonPathUtil.extract(res, "$.msg"), "注册成功");
		
		Object user_id = JsonPathUtil.extract(res, "$.data.data.user_id");
		String sql = "SELECT id,username,pwd,salt FROM s_user WHERE id = " + user_id.toString();
		ArrayList<Map> datas = DBUtil.getData(sql);
		Object usernameDB = datas.get(0).get("username");
		Object pwdDB = datas.get(0).get("pwd");
		Object saltDB = datas.get(0).get("salt");
		Assert.assertEquals(usernameDB, params.get("accounts"));
		//将数据库查询到的salt和pwd拼接后进行md5加密，然后和数据库中的pwd进行对比
		String expectPwd =  EncryptionsUtil.md5(saltDB.toString()+params.get("pwd"));
		Assert.assertEquals(pwdDB, expectPwd);
		
	}
}
