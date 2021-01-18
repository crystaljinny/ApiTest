package com.mtx.apitest;

import java.io.IOException;
import java.net.URISyntaxException;

import org.apache.http.client.ClientProtocolException;
import org.testng.Assert;
import org.testng.annotations.Test;

import com.mtx.crm1121.CrmTestBase;
import com.mtx.crm1121.server.AddContracterServer;
import com.mtx.crm1121.server.AddCustomerServer;
import com.mtx.crm1121.server.DeleteCustomerServer;
import com.mtx.util.JsonPathUtil;

public class DeleteCustomerTests extends CrmTestBase{
    @Test(description = "正例-新增客户-删除客户")
    public void test01_deleteCustomer() throws ClientProtocolException, URISyntaxException, IOException {
    	String res = AddCustomerServer.addCustomer(uri, token);
    	Object extract = JsonPathUtil.extract(res, "$.code");
    	Assert.assertEquals(extract, 0);
    	Object extractCustomerId = JsonPathUtil.extract(res, "$.data.customerId");
    	
    	String resDelete =DeleteCustomerServer.deleteContracter(uri, token, "customerIds", extractCustomerId.toString());
    	Object extractCode =JsonPathUtil.extract(resDelete, "$.code");
    	Assert.assertEquals(extract, 0);
    }
    
    @Test(description = "反例-新增客户-新增该客户联系人-删除该客户")
    public void test02_deleteCustomer() throws ClientProtocolException, URISyntaxException, IOException {
    	//新增客户
    	String res = AddCustomerServer.addCustomer(uri, token);
    	Object extract = JsonPathUtil.extract(res, "$.code");
    	Assert.assertEquals(extract, 0);
    	Object extractCustomerId = JsonPathUtil.extract(res, "$.data.customerId");
    	
    	//新增联系人
    	String addContract = AddContracterServer.addContracter(uri, token, "$.entity.customer_id", extractCustomerId.toString());
    	Object extract1 = JsonPathUtil.extract(addContract, "$.code");
    	Assert.assertEquals(extract1, 0);
    	
    	//删除该客户
    	String resDelete =DeleteCustomerServer.deleteContracter(uri, token, "customerIds", extractCustomerId.toString());
    	Object extractCode =JsonPathUtil.extract(resDelete, "$.code");
    	Assert.assertEquals(extractCode, 500);
    	Object extractMsg = JsonPathUtil.extract(resDelete, "$.msg");
    	Assert.assertEquals(extractMsg, "该条数据与其他数据有必要关联，请勿删除");
    	
    }
    
    //客户关联商机不能删除，
}
