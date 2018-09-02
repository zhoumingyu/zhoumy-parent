package top.zhoumy.system.controller;

import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

import top.zhoumy.common.annotation.Log;
import top.zhoumy.common.controller.BaseController;
import top.zhoumy.common.domain.QueryRequest;
import top.zhoumy.common.domain.ResponseBo;
import top.zhoumy.common.util.FileUtils;
import top.zhoumy.system.domain.User;
import top.zhoumy.system.domain.UserWali;
import top.zhoumy.system.service.UserWaliService;

@Controller
public class UserWaliController extends BaseController {

	@Autowired
	private UserWaliService userService;

	@RequestMapping("userwali")
	@RequiresPermissions("userwali:list")
	public String index(Model model) {
		User user = super.getCurrentUser();
		model.addAttribute("user", user);
		return "system/userwali/user";
	}

	@Log("获取用户信息")
	@RequestMapping("userwali/list")
	@ResponseBody
	public Map<String, Object> userList(QueryRequest request, UserWali user) {
		PageHelper.startPage(request.getPageNum(), request.getPageSize());
		List<UserWali> list = this.userService.findUserWali(user);
		PageInfo<UserWali> pageInfo = new PageInfo<UserWali>(list);
		return getDataTable(pageInfo);
	}

	@RequestMapping("userwali/excel")
	@ResponseBody
	public ResponseBo userExcel(UserWali user) {
		try {
			List<UserWali> list = this.userService.findUserWali(user);
			return FileUtils.createExcelByPOIKit("用户表", list, UserWali.class);
		} catch (Exception e) {
			e.printStackTrace();
			return ResponseBo.error("导出Excel失败，请联系网站管理员！");
		}
	}

	@RequestMapping("userwali/csv")
	@ResponseBody
	public ResponseBo userCsv(UserWali user) {
		try {
			List<UserWali> list = this.userService.findUserWali(user);
			return FileUtils.createCsv("用户表", list, User.class);
		} catch (Exception e) {
			e.printStackTrace();
			return ResponseBo.error("导出Csv失败，请联系网站管理员！");
		}
	}

}
