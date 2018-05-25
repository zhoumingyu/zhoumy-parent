package top.zhoumy.system.service;

import top.zhoumy.common.service.IService;
import top.zhoumy.system.domain.UserRole;

public interface UserRoleService extends IService<UserRole> {

	void deleteUserRolesByRoleId(String roleIds);

	void deleteUserRolesByUserId(String userIds);
}
