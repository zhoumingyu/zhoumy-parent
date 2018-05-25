package top.zhoumy.system.dao;

import java.util.List;

import top.zhoumy.common.config.MyMapper;
import top.zhoumy.system.domain.Role;
import top.zhoumy.system.domain.RoleWithMenu;


public interface RoleMapper extends MyMapper<Role> {
	
	List<Role> findUserRole(String userName);
	
	List<RoleWithMenu> findById(Long roleId);
}