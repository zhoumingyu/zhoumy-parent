package top.zhoumy.system.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import top.zhoumy.common.config.MyMapper;
import top.zhoumy.system.domain.UserWali;

public interface UserWaliMapper extends MyMapper<UserWali> {
	UserWali getUserPhoneCodeByPhone(@Param("phone") String phone);

	List<UserWali> findUserWali(UserWali user);
}
