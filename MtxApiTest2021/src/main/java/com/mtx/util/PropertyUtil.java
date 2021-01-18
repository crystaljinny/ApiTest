package com.mtx.util;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;
import java.util.Set;

import org.apache.log4j.Logger;

public class PropertyUtil {
	private static Logger logger = Logger.getLogger(PropertyUtil.class);
    public static Properties readProperties(String file) {
    	Properties properties = new Properties();
    	InputStream inputStream;
    	try {
			inputStream = new FileInputStream(file);
			BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
			properties.load(bufferedReader);
			bufferedReader.close();
			inputStream.close();
		} catch (Exception e) {
			logger.error(e.getMessage());
			throw new RuntimeException(e.getMessage());
		}
    	return properties;
    }
    
    /**
     * 获取配置文件全部参数
     * @param args
     */
    public static Map<String, String> getAllKeyValues(String file){
    	Properties properties = PropertyUtil.readProperties(file);
    	Set<Map.Entry<Object, Object>> entrySet = properties.entrySet();
    	Map<String, String> params = new HashMap<String, String>();
    	for (Map.Entry<Object, Object> entry : entrySet) {
			params.put(entry.getKey().toString(), entry.getValue().toString());
		}
    	return params;
    }
    
    public static void main(String[] args) {
		Properties properties = PropertyUtil.readProperties("src/main/resources/shopparams/order.properties");
		System.out.println(properties.getProperty("buy_type"));
	}
}
