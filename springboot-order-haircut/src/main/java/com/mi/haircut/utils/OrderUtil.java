package com.mi.haircut.utils;


public class OrderUtil {
	/**
	 * 生成编号
	 * @return
	 */
	public static String getOrderNum() {
		String orderNum = "";
		String yyyymmdd = TimeUtil.dateFormtStr2(null);
		int ranNum = (int) ((Math.random() * 9 + 1) * 10000);
		orderNum = yyyymmdd + ranNum;
		return orderNum;
	}
	
}
