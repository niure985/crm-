package top.hr37.setting.service.impl;

import org.springframework.stereotype.Service;
import top.hr37.Exception.LoginException;
import top.hr37.setting.dao.UserDao;
import top.hr37.setting.domain.User;
import top.hr37.setting.service.UserService;
import top.hr37.utils.DateTimeUtil;

import javax.annotation.Resource;

@Service
public class UserServiceImpl implements UserService {

    @Resource
    private UserDao userDao;

    @Override
    public User userLogin(String loginAct, String loginPwd,String ip) throws LoginException {
        User user = userDao.userLogin(loginAct,loginPwd,ip);

        if(user == null){

            throw new LoginException("账号或密码错误！");

        }

        //执行到此说明账号密码正确
        //验证失效时间
        String expireTime = user.getExpireTime();

        String currentTime = DateTimeUtil.getSysTime();

        if(expireTime.compareTo(currentTime) < 0){

            throw new LoginException("账号已失效！");

        }

        //判断锁定状态
        String lockState = user.getLockState();

        if("0".equals(lockState)){

            throw new LoginException("账号已锁定！");
        }

        //判断ip
        String allowIp = user.getAllowIps();
        if(! allowIp.contains(ip)){
            throw new LoginException("该ip无权访问！");
        }

        return user;
    }
}
