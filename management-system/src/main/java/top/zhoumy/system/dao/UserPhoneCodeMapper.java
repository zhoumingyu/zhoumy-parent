package top.zhoumy.system.dao;

import org.apache.ibatis.annotations.Param;

import top.zhoumy.common.config.MyMapper;
import top.zhoumy.system.domain.UserPhoneCode;

public interface UserPhoneCodeMapper extends MyMapper<UserPhoneCode> {
	UserPhoneCode getUserPhoneCodeByPhone(@Param("phone") String phone);

	Integer insertUserPhoneCode(UserPhoneCode userPhoneCode);
}
