package com.mtx.util;

import com.alibaba.fastjson.JSONPath;

public class JsonPathUtil {
    public static Object extract(String json, String path) {
    	return JSONPath.extract(json, path);
    }
}
