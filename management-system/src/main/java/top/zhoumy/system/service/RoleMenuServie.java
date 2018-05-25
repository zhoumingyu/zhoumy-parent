package top.zhoumy.system.service;

import top.zhoumy.common.service.IService;
import top.zhoumy.system.domain.RoleMenu;

public interface RoleMenuServie extends IService<RoleMenu> {

	void deleteRoleMenusByRoleId(String roleIds);

	void deleteRoleMenusByMenuId(String menuIds);
}
