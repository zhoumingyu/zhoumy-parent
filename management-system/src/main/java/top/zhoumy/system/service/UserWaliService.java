package top.zhoumy.system.service;

import java.util.List;

import top.zhoumy.common.service.IService;
import top.zhoumy.system.domain.UserWali;

public interface UserWaliService extends IService<UserWali> {
	UserWali getUser(String phone);

	List<UserWali> findUserWali(UserWali user);
}
