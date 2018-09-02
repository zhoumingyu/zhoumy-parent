package top.zhoumy.system.service;

import top.zhoumy.common.service.IService;
import top.zhoumy.system.domain.UserPhoneCode;

public interface UserPhoneCodeService extends IService<UserPhoneCode> {
	Integer addOrUpdate(String phone, String code);

	UserPhoneCode getUserPhoneCode(String phone);
}
