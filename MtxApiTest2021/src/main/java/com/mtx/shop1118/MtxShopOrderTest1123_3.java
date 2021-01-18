package com.mtx.shop1118;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import javax.swing.border.TitledBorder;

import org.apache.http.client.ClientProtocolException;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.alibaba.fastjson.JSONObject;
import com.mtx.http.MtxHttpClient;
import com.mtx.util.DBUtil;
import com.mtx.util.JsonPathUtil;
import com.mtx.util.PropertyUtil;

/*
 * 从配置文件读取服务IP
 */
public class MtxShopOrderTest1123_3 {
    String host;
    @BeforeClass
    public void setUp() {
    	Properties properties = PropertyUtil.readProperties("src/main/resources/http.properties");
    	host = properties.getProperty("http.mtxshop.url");
    	
    	Properties dbProperties = PropertyUtil.readProperties("src/main/resources/db.properties");
        String url = dbProperties.getProperty("db.mtxshop.url");
        String user = dbProperties.getProperty("db.mtxshop.username");
        String password = dbProperties.getProperty("db.mtxshop.password");
    	DBUtil.getConnect(url, user, password);
    }
    
    @Test
	public void test01_login() throws ClientProtocolException, URISyntaxException, IOException {
		String uri = host+"/mtx/index.php?s=/index/user/login.html";
		Map<String, String> params = new HashMap<String, String>();
		params.put("accounts", "jinny");
		params.put("pwd", "123456");
		Map<String, String> headers = new HashMap<String, String>();
		headers.put("X-Requested-With", "XMLHttpRequest");
		String res = MtxHttpClient.sendPost(uri, params, headers);
		Assert.assertTrue(res.contains("登录成功"));
	}

	@Test(dependsOnMethods = "test01_login")
	public void test02_addOrder() throws ClientProtocolException, URISyntaxException, IOException {
		String uri = host+"/mtx/index.php?s=/index/buy/add.html";
		
		Map<String, String> params = new HashMap<String, String>();
		params = PropertyUtil.getAllKeyValues("src/main/resources/shopparams/order.properties");
		
		Map<String, String> headers = new HashMap<String, String>();
		headers.put("X-Requested-With", "XMLHttpRequest");
		String res = MtxHttpClient.sendPost(uri, params, headers);
		Assert.assertTrue(res.contains("提交成功"));
		
		String id = (String) JsonPathUtil.extract(res, "$.data.order.id");
		ArrayList<Map> datas = DBUtil.getData("SELECT user_id,goods_id,title FROM s_order_detail WHERE order_id=" + id + ";");
		System.out.println(datas.size());
		Object userId = datas.get(0).get("user_id");
		Object goodsId = datas.get(0).get("goods_id");
		Object title = datas.get(0).get("title");
//		Assert.assertEquals(userId, 28);
//		Assert.assertEquals(goodsId, 1);
		Assert.assertEquals(title.toString(), "MIUI/小米 小米手机4 小米4代 MI4智能4G手机包邮 黑色 D-LTE（4G）/TD-SCD");
	}
}
