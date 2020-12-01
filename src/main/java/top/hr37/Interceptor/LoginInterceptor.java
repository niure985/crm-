package top.hr37.Interceptor;

import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class LoginInterceptor implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

        //获取session
        HttpSession session = request.getSession(false);

        //获取url
        String uri = request.getRequestURI();

        //判断uri
        if(uri.indexOf("/login") >= 0){
            return true;
        }

        //判断session
        if(session != null && session.getAttribute("user") != null){
            return true;
        }

        response.sendRedirect("myWeb");

        return false;

    }
}
