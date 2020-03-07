package com.mi.haircut.utils;

import javax.servlet.http.HttpServletRequest;
/**
 * https://blog.csdn.net/qq_36411874/article/details/79938439
 * @author lgq
 * @date 2018年12月11日
 */
public class WebServletUtil {
	
	private static final String[] HEADERS_TO_TRY = { 
		        "X-Forwarded-For",
		        "Proxy-Client-IP",
		        "WL-Proxy-Client-IP",
		        "HTTP_X_FORWARDED_FOR",
		        "HTTP_X_FORWARDED",
		        "HTTP_X_CLUSTER_CLIENT_IP",
		        "HTTP_CLIENT_IP",
		        "HTTP_FORWARDED_FOR",
		        "HTTP_FORWARDED",
		        "HTTP_VIA",
		        "REMOTE_ADDR",
		        "X-Real-IP"};
		 
    /***
     * 用这个
     * 获取客户端ip地址(可以穿透代理)
     * @param request
     * @return
     */
    public static String getClientIpAddress(HttpServletRequest request) {
        for (String header : HEADERS_TO_TRY) {
            String ip = request.getHeader(header);
            if (ip != null && ip.length() != 0 && !"unknown".equalsIgnoreCase(ip)) {
                return ip;
            }
        }
        return request.getRemoteAddr();
    }
   
    
    
	
}
