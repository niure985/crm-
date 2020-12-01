package top.hr37.setting.service;

import org.apache.ibatis.annotations.Param;
import top.hr37.Exception.LoginException;
import top.hr37.setting.domain.User;

public interface UserService {

    User userLogin(String loginAct,String loginPwd,String ip) throws LoginException;
}
