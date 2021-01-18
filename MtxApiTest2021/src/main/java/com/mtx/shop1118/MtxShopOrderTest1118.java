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

public class MtxShopOrderTest1118 {
CloseableHttpClient httpClient;
	
	@BeforeClass
	public void init() throws ClientProtocolException, URISyntaxException, IOException {
		httpClient = HttpClients.createDefault();
		
		String name = "jinny";
		String pwd = "123456";
		String expect = "登录成功";
		String res = MtxShopLogin.login(httpClient,name,pwd);
		Assert.assertTrue(res.contains(expect));
	}
	
	@Test(description  = "shop-buy-add-正例")
	public void test01_cartSave() throws ClientProtocolException, URISyntaxException, IOException {
		int goodsId = 1;
		int stock = 1;
		String buy_type = "goods";
		int address_id = 7;
		int payment_id = 1; 
		String spec = "[]"; 
		String user_note = "留言。。。";
		String expectBuy = "提交成功";
		String resBuy = MtxShopOrder.buy(httpClient, goodsId, stock, buy_type, address_id, payment_id, spec, user_note);
		Assert.assertTrue(resBuy.contains(expectBuy));
	}
	
	@DataProvider
	public Object[][] buyData(){
		Object[][] objects = {
				{1,1,"goods",7,1,"[]","留言。。。","提交成功"},
				
		};
		return objects;
	}
	
	@Test(description = "shop-buy-add-数据驱动",dataProvider = "buyData")
	public void test02_buyAdd(int goodsId, int stock, String buy_type, int address_id,
			int payment_id, String spec, String user_note,String expect ) throws ClientProtocolException, URISyntaxException, IOException {
		String resSave =  MtxShopOrder.buy(httpClient, goodsId, stock, buy_type, address_id, payment_id, spec, user_note);
		Assert.assertTrue(resSave.contains(expect));
	}
}
