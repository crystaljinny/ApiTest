package com.mtx.util;

import java.awt.List;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLClientInfoException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;





public class DBUtil {
    public static Connection connection;
    public static void getConnect(String url, String user, String password) {
    	try {
    		connection = DriverManager.getConnection(url, user, password);
		} catch (Exception e) {
			e.printStackTrace();
		}
    }
    
    public static void close() {
    	if(connection!=null) {
    		try {
				connection.close();
				System.out.println("关闭成功");
			} catch (Exception e) {
				e.printStackTrace();
			}
    	}
    }
    
    public static ArrayList<Map> getData(String sql){
    	ArrayList<Map> list = new ArrayList<Map>();
    	try {
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			ResultSet executeQuery = preparedStatement.executeQuery();
			ResultSetMetaData metaData = executeQuery.getMetaData();
			int col = metaData.getColumnCount(); 
			while(executeQuery.next()) {
				Map<String, Object> line = new HashMap<String, Object>();
				for(int i = 1; i <= col; i++) {
					String columnLabel = metaData.getColumnLabel(i);//字段名
					Object object = executeQuery.getObject(i);//字段值
					line.put(columnLabel, object);
				}
				list.add(line);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
    	return list;
    }
    
    public static int updateData(String sql) {
    	PreparedStatement preparedStatement = null;
    	try {
			preparedStatement = connection.prepareStatement(sql);
			int i = preparedStatement.executeUpdate();
			return i;
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			if(preparedStatement != null) {
				try {
					preparedStatement.close();
				} catch (Exception e2) {
					e2.printStackTrace();
				}
			}
		}
		return 0;
    }
    
    
    public static void main(String[] args) {
		String url = "jdbc:mysql://192.168.43.203:3306/mtx?useSSL=false";
		String user = "root";
		String password = "123456";
		getConnect(url, user, password);
		
		String sql = "select * from s_order";
//		ArrayList<Map> list = getData(sql);
//		for (Map map : list) {
//			Set<Map.Entry<String,String>> entrySet = map.entrySet();
//			for (Map.Entry entry : entrySet) {
//				System.out.print(entry.getKey()+"="+entry.getValue().toString()+" ");	
//			}
//			System.out.println();
//		}
		
		sql = "update mtx.s_goods set inventory = 500 where id = 1";
		sql =  "update mtx.s_goods_spec_base set inventory=500 where goods_id =1;";
		int updateCounts = updateData(sql);
		System.out.println(updateCounts);
	}
}
