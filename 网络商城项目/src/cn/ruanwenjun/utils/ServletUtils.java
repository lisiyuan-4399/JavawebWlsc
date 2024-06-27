package cn.ruanwenjun.utils;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;

public class ServletUtils {

    public static String getServleUrl(HttpServletRequest request){
        ServletContext context = request.getServletContext();
        String contextPath = context.getContextPath();

        // 获取主机名（IP地址）和端口号
        String host = request.getServerName();
        int port = request.getServerPort();
        // 构建带有主机IP地址和端口号的完整上下文路径

        return request.getScheme() + "://" + host + ":" + port + contextPath;
    }

}
