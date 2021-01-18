package com.mtx.apitest;

import java.io.IOException;
import java.net.URISyntaxException;

import org.apache.http.client.ClientProtocolException;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.alibaba.fastjson.JSONObject;
import com.mtx.crm1121.CrmTestBase;
import com.mtx.crm1121.server.AddProductServer;
import com.mtx.util.ExcelUtil;


public class DataDriverCrmTests extends CrmTestBase {
	@DataProvider  //获取添加产品数据
	public Object[][] getAddProductData() throws Exception{
		ExcelUtil excelUtil = new ExcelUtil("src/main/resources/crmparams/crmdata.xlsx");
		Object[][] sheetData = excelUtil.getSheetData("新建产品");
		excelUtil.close();
		return sheetData;
	}
	
	@DataProvider
	public Object[][] proData(){
		Object[][] objects = {
				{"新建产品正确","{\"entity\":{\"name\":\"Java全栈自动化\",\"category_id\":23,\"num\":\"00001\",\"price\":\"6780\",\"description\":\"这是一个很好的课程\"}}","12345"},
				{"新建产品正确","{\"entity\":{\"name\":\"\",\"category_id\":23,\"num\":\"00001\",\"price\":\"6780\",\"description\":\"这是一个很好的课程\"}}","12345"},
				
		};
		return objects;
	}
	
	@Test(dataProvider = "getAddProductData")
	public void test01_addProduct(String casename,String params,String assertvalue) throws ClientProtocolException, URISyntaxException, IOException {
		JSONObject paramsJSON = JSONObject.parseObject(params);
		String res =AddProductServer.addProduct(uri, token,paramsJSON);
		Assert.assertTrue(res.contains(assertvalue));
	}
   
}
