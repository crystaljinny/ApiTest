package com.mtx.util;

public class StringUtil {
	public static String getStrByRLBoundary(String strSource, String leftBoundary, String rightBoundary ) {
		int start = strSource.indexOf(leftBoundary);
        int startLength = leftBoundary.length();
        int leftIndex = start + startLength;
        int rightIndex =  strSource.indexOf(rightBoundary);
        String strTarget = strSource.substring(leftIndex, rightIndex);
        System.out.println(strTarget);
        return strTarget;
	}
	
	public static void main(String[] args) {
        String strSource = "{\"code\":0,\"Admin-Token\":\"e80441be74c14277991579ef1ecfe2fa\",\"user\":{\"deptName\":\"办公室\",\"img\":\"\",\"createTime\":\"2019-02-13 15:43:26\",\"lastLoginTime\":\"2020-12-01 09:24:56\",\"num\":\"390823685766627\",\"sex\":null,\"mobile\":\"12312341234\",\"realname\":\"admin\",\"parentName\":null,\"post\":\"1\",\"userId\":3,\"parentId\":0,\"lastLoginIp\":\"192.168.43.191\",\"deptId\":1,\"email\":null,\"username\":\"admin\",\"status\":1},\"auth\":{\"oa\":{\"book\":{\"read\":true},\"announcement\":{\"save\":true,\"update\":true,\"delete\":true}},\"work\":{\"task\":{\"save\":true},\"work\":{\"update\":true},\"taskClass\":{\"save\":true,\"update\":true,\"delete\":true}},\"bi\":{\"product\":{\"read\":true},\"oa\":{\"read\":true},\"business\":{\"read\":true},\"achievement\":{\"read\":true},\"contract\":{\"read\":true},\"ranking\":{\"read\":true},\"portrait\":{\"read\":true},\"customer\":{\"read\":true}},\"project\":{\"projectManage\":{\"save\":true}},\"crm\":{\"product\":{\"read\":true,\"excelexport\":true,\"save\":true,\"update\":true,\"index\":true,\"excelimport\":true,\"status\":true},\"business\":{\"read\":true,\"transfer\":true,\"teamsave\":true,\"save\":true,\"update\":true,\"index\":true,\"delete\":true},\"leads\":{\"transform\":true,\"read\":true,\"transfer\":true,\"excelexport\":true,\"save\":true,\"update\":true,\"index\":true,\"excelimport\":true,\"delete\":true},\"contract\":{\"discard\":true,\"read\":true,\"transfer\":true,\"teamsave\":true,\"save\":true,\"update\":true,\"index\":true,\"delete\":true},\"pool\":{\"receive\":true,\"excelexport\":true,\"index\":true,\"distribute\":true},\"receivables\":{\"read\":true,\"save\":true,\"update\":true,\"index\":true,\"delete\":true},\"contacts\":{\"read\":true,\"transfer\":true,\"excelexport\":true,\"save\":true,\"update\":true,\"index\":true,\"excelimport\":true,\"delete\":true},\"customer\":{\"read\":true,\"transfer\":true,\"teamsave\":true,\"excelexport\":true,\"save\":true,\"update\":true,\"index\":true,\"lock\":true,\"excelimport\":true,\"dealStatus\":true,\"putinpool\":true,\"delete\":true}},\"manage\":{\"oa\":{\"examine\":true},\"system\":{\"read\":true,\"update\":true},\"examineFlow\":{\"update\":true},\"work\":{\"update\":true},\"permission\":{\"update\":true},\"configSet\":{\"read\":true,\"update\":true},\"users\":{\"deptSave\":true,\"deptDelete\":true,\"read\":true,\"userEnables\":true,\"userUpdate\":true,\"userSave\":true,\"deptUpdate\":true},\"crm\":{\"field\":true,\"achievement\":true,\"pool\":true,\"setting\":true}}}}"; 
        String leftBoundary = "\"Admin-Token\":\"";
        String rightBoundary = "\",\"user\"";
        getStrByRLBoundary(strSource,leftBoundary,rightBoundary);
	}
}
