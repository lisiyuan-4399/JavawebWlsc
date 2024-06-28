package cn.ruanwenjun.filter;

import javax.servlet.*;
import javax.servlet.http.*;
import java.io.IOException;

public class AuthenticationFilter implements Filter {

    private String[] excludedUrls; // 用于存放不需要过滤的页面路径

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        //  从配置文件读取不过滤的文件
        String excludedUrlsStr = filterConfig.getInitParameter("excludedUrls");
        if (excludedUrlsStr != null && !excludedUrlsStr.trim().isEmpty()) {
            excludedUrls = excludedUrlsStr.split(",");
        }
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {

        HttpServletRequest httpRequest = (HttpServletRequest) request;
        HttpServletResponse httpResponse = (HttpServletResponse) response;

        String requestURI = httpRequest.getRequestURI();

        // 检查是否需要拦截
        boolean isExcluded = false;
        if (excludedUrls != null) {
            for (String url : excludedUrls) {
                if (requestURI.contains(url.trim())) {
                    isExcluded = true;
                    break;
                }
            }
        }

        // 如果请求的页面需要拦截，则检查用户是否已登录
        if (!isExcluded && !isLoggedIn(httpRequest)) {
            // 用户未登录，重定向到登录页面
            httpResponse.sendRedirect(httpRequest.getContextPath() + "/login.jsp");
            return;
        }

        // 允许请求继续通过过滤器
        chain.doFilter(request, response);
    }

    @Override
    public void destroy() {
        // 可以在这里清理资源（如果需要的话）
    }

    // 判断用户是否已登录的方法
    private boolean isLoggedIn(HttpServletRequest request) {
        // 这里根据你的具体逻辑判断用户是否已登录，可以使用 session 或其他方式
        // 示例：假设用户登录信息保存在 session 中
        return request.getSession().getAttribute("user") != null;
    }
}

