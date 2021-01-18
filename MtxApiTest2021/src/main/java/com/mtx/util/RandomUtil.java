package com.mtx.util;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;

public class RandomUtil {
	/*
	 * 随机自然数，【0，maxNum】
	 */
    public static int randNum(int maxNum) {
    	Random random = new Random();
    	return random.nextInt(maxNum+1);
    }
    
    public static String getRanStrAndNumberByLen(int lengthOfString) {
    	String chars = "1,2,3,4,5,6,7,8,9,0,A,B,C,D,E";
    	String[] charArr = chars.split(",");
    	StringBuffer randomStr = new StringBuffer();
    	Random random = new Random();
    	int strLen = charArr.length;
    	int count = 0;
    	while(count < lengthOfString ) {
    		int i = random.nextInt(strLen);
    		randomStr.append(charArr[i]);
    		count++;
    	}
    	return randomStr.toString();
    }
    
    public static String getRndNumByLen(int lengthOfNumber) {
    	int i,count = 0;
    	StringBuffer randomStr = new StringBuffer("");
    	Random rnd = new Random();
    	while(count < lengthOfNumber) {
    		i = Math.abs(rnd.nextInt(10));
    		if(i == 0 && count ==0) {
    			//保证首位不是0
    		}else {
    			randomStr.append(String.valueOf(i));
    			count++;
    		}
    	}
    	return randomStr.toString();
    }
    
    /*
     * 开始时间，结束时间，随机日期yyyy-MM-dd
     */
	public static String randomDate(String beginDate, String endDate) {
//    	Random random = new Random();
//    	for(int i = 0; i < 100; i++) {
//    		System.out.println((long)(random.nextDouble()*100));
//    	}
		SimpleDateFormat f = new SimpleDateFormat("yyyy-MM-dd");
		try {

			SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");

			Date start = format.parse(beginDate);// 构造开始日期

			Date end = format.parse(endDate);// 构造结束日期

			// getTime()表示返回自 1970 年 1 月 1 日 00:00:00 GMT 以来此 Date 对象表示的毫秒数。

			if (start.getTime() >= end.getTime()) {

				return null;

			}

			long date = random(start.getTime(), end.getTime());

			return f.format(new Date(date));

		} catch (Exception e) {

			e.printStackTrace();

		}

		return null;

	}

	private static long random(long begin, long end) {

		long rtn = begin + (long) (Math.random() * (end - begin));

		// 如果返回的是开始时间和结束时间，则递归调用本函数查找随机值

		if (rtn == begin || rtn == end) {

			return random(begin, end);

		}

		return rtn;
	}
    
    public static void main(String[] args) {
//    	System.out.println(randomDate("2020-1-1","2020-12-1"));
		for (int i = 0; i < 20; i++ ) {
//			System.out.println(randNum(3));
			System.out.println(getRanStrAndNumberByLen(10));
		}
	}
}
