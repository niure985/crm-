package top.hr37.setting.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import top.hr37.setting.domain.User;
import top.hr37.setting.service.UserService;
import top.hr37.utils.MD5Util;
import top.hr37.utils.PrintJson;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@Controller
@RequestMapping("/setting")
public class UserController {

    @Resource
    private UserService service;
    private ModelAndView mv = new ModelAndView();

    @RequestMapping(value = "/user/login.do")
    @ExceptionHandler
    public void userLogin(HttpServletRequest request, HttpServletResponse response,String loginAct, String loginPwd) throws IOException {

        //将密码明文形式转换为MD5的密文
        String loginPwdMD5 = MD5Util.getMD5(loginPwd);

        //接受浏览器端ip地址
        String ip = request.getRemoteAddr();

        //定义异常
        try{
            //连接数据库查询用户是否存在
            User user = service.userLogin(loginAct,loginPwdMD5,ip);

            //获取session
            HttpSession session = request.getSession(false);

            //把user对象保存到session
            session.setAttribute("user",user);

            //如果执行到此处，说明没有抛出异常，表示登录成功
            /*
            返回{"success":true,""msg":?}
             */
            PrintJson.printJsonFlag(response,true);
        } catch (Exception e){

            e.printStackTrace();

            //如果执行了Catch的信息，说明验证失败，为controller抛出异常，登录失败
            String msg = e.getMessage();
            Map<String,Object> map = new HashMap<String,Object>();
            map.put("success",false);
            map.put("msg",msg);
            PrintJson.printJsonObj(response,map);
        }

    }

}
