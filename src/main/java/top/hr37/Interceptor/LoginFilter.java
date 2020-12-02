package top.hr37.Interceptor;

import org.omg.CORBA.Request;
import top.hr37.setting.domain.User;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class LoginFilter implements Filter {

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {

        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;
        //System.out.println(request.getScheme() + "://" + request.getServerName() + ":" + 	request.getServerPort() + request.getContextPath() + "/");

        //获取uri
        String path = request.getServletPath();

        //获取session
        User user = (User) request.getSession().getAttribute("user");

        //System.out.println("/login.jsp".equals(path));
        //判断
        if("/login.jsp".equals(path) || "/setting/user/login.do".equals(path) || user != null){
            filterChain.doFilter(servletRequest,servletResponse);
        }else {
            response.sendRedirect(request.getContextPath() + "/login.jsp");
        }

    }

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }



    @Override
    public void destroy() {

    }
}
