package top.hr37.setting.dao;

import org.apache.ibatis.annotations.Param;
import top.hr37.setting.domain.User;

public interface UserDao {

    User userLogin(@Param("loginAct") String loginAct,@Param("loginPwd")  String loginPwd,@Param("ip") String ip);
}
