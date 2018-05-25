package top.zhoumy.system.service;

import java.util.List;

import top.zhoumy.common.service.IService;
import top.zhoumy.system.domain.Dict;


public interface DictService extends IService<Dict> {

	List<Dict> findAllDicts(Dict dict);

	Dict findById(Long dictId);

	void addDict(Dict dict);

	void deleteDicts(String dictIds);

	void updateDict(Dict dicdt);
}
