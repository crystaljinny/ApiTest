package com.mtx.shop1118;

import java.io.IOException;
import java.net.URISyntaxException;

import org.apache.http.client.ClientProtocolException;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class MtxShopCartSaveTest {
CloseableHttpClient httpClient;
	
	@BeforeClass
	public void init() throws ClientProtocolException, URISyntaxException, IOException {
		httpClient = HttpClients.createDefault();
		
		String name = "jinny";
		String pwd = "123456";
		String expectLogin = "登录成功";
		String res = MtxShopLogin.login(httpClient,name,pwd);
		Assert.assertTrue(res.contains(expectLogin));
	}
	
	
	@DataProvider
	public Object[][] saveData(){
		Object[][] objects = {
				{1,1,"加入成功"},
				{100,1,"商品不存在"},
				{10,1,""},
				
		};
		return objects;
	}
	
	@Test(description = "shop-cart-save-数据驱动",dataProvider = "saveData")
	public void test04_cartSave(int goodsId, int stock,String expect ) throws ClientProtocolException, URISyntaxException, IOException {
		String resSave =  MtxShopCartSave.save(httpClient, goodsId, stock);
		Assert.assertTrue(resSave.contains(expect));
	}
}
