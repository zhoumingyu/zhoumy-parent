package top.zhoumy.system.service;

import java.util.List;

import top.zhoumy.common.domain.Tree;
import top.zhoumy.common.service.IService;
import top.zhoumy.system.domain.Dept;


public interface DeptService extends IService<Dept> {

	Tree<Dept> getDeptTree();

	List<Dept> findAllDepts(Dept dept);

	Dept findByName(String deptName);

	Dept findById(Long deptId);
	
	void addDept(Dept dept);
	
	void updateDept(Dept dept);

	void deleteDepts(String deptIds);
}
