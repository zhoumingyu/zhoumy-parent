package top.zhoumy.api;

import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.apache.shiro.session.Session;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

//import top.zhoumy.alisms.SmsUtils;
import top.zhoumy.common.controller.BaseController;
import top.zhoumy.common.domain.ResponseBo;
import top.zhoumy.common.util.vcode.Captcha;
import top.zhoumy.common.util.vcode.GifCaptcha;
import top.zhoumy.system.domain.UserPhoneCode;
import top.zhoumy.system.domain.UserWali;
import top.zhoumy.system.service.UserPhoneCodeService;
import top.zhoumy.system.service.UserWaliService;

@Controller
@RequestMapping("/api")
public class WaliUserController extends BaseController {

	Logger logger = LoggerFactory.getLogger(WaliUserController.class);
	@Autowired
	UserPhoneCodeService userPhoneCodeService;

	@Autowired
	UserWaliService userWaliService;

	@RequestMapping("login")
	public String wali() {
		
		return "api/login";
	}

	@RequestMapping("waliindex")
	public String Index(String login) {
		return "api/waliindex";
	}

	@GetMapping(value = "regeidtGifCode")
	public void getGifCode(HttpServletResponse response, HttpServletRequest request) {
		try {
			response.setHeader("Pragma", "No-cache");
			response.setHeader("Cache-Control", "no-cache");
			response.setDateHeader("Expires", 0);
			response.setContentType("image/gif");

			Captcha captcha = new GifCaptcha(100, 30, 4);
			captcha.out(response.getOutputStream());
			Session session = super.getSession();
			session.removeAttribute("_regeditcode");
			session.setAttribute("_regeditcode", captcha.text().toLowerCase());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@GetMapping(value = "loginGifCode")
	public void getLoginGifCode(HttpServletResponse response, HttpServletRequest request) {
		try {
			response.setHeader("Pragma", "No-cache");
			response.setHeader("Cache-Control", "no-cache");
			response.setDateHeader("Expires", 0);
			response.setContentType("image/gif");

			Captcha captcha = new GifCaptcha(100, 30, 4);
			captcha.out(response.getOutputStream());
			Session session = super.getSession();
			session.removeAttribute("_logincode");
			session.setAttribute("_logincode", captcha.text().toLowerCase());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@PostMapping("/regedit")
	@ResponseBody
	public ResponseBo regedit(String phone, String code) {
		if (!StringUtils.isNotBlank(code)) {
			return ResponseBo.warn("手机验证码不能为空！");
		}
		try {
			UserPhoneCode userPhoneCode = userPhoneCodeService.getUserPhoneCode(phone);
			if (userPhoneCode == null) {
				return ResponseBo.error("手机验证码有误");
			}
			UserWali wali = userWaliService.getUser(phone);
			if (wali != null) {
				return ResponseBo.error("已经注册成功，不能重复注册");
			}
			if (userPhoneCode.getPhoneCode().equals(code)) {
				UserWali uWali = new UserWali();
				uWali.setPhone(phone);
				uWali.setCreateTime(new Date());
				userWaliService.save(uWali);
				return ResponseBo.ok();

			} else {
				return ResponseBo.error("手机验证码有误");
			}
		} catch (Exception e) {
			return ResponseBo.error("！");
		}
	}

	@PostMapping("/getPhoneCode")
	@ResponseBody
	public ResponseBo regeidtGifCode(String phone, String code) {
		if (!StringUtils.isNotBlank(code)) {
			return ResponseBo.warn("验证码不能为空！");
		}
		Session session = super.getSession();
		String sessionCode = (String) session.getAttribute("_regeditcode");
		if (!code.toLowerCase().equals(sessionCode)) {
			return ResponseBo.warn("验证码错误！");
		}
		try {

			UserWali wali = userWaliService.getUser(phone);
			if (wali != null) {
				return ResponseBo.error("已经注册成功，不能重复注册");
			}
			String phoneCode = getRandNum(1, 999999);
			// 发送手机验证码
			if (userPhoneCodeService.addOrUpdate(phone, phoneCode) > 0) {
				logger.info("手机号：" + phone + "短信验证码：" + phoneCode);
				// SmsUtils.sendSms(phone, phoneCode);
				return ResponseBo.ok();
			} else {
				return ResponseBo.error("请稍后再试");
			}

		} catch (Exception e) {
			return ResponseBo.error("！");
		}
	}

	@PostMapping("/getLoginPhoneCode")
	@ResponseBody
	public ResponseBo LoginGifCode(String phone, String code) {
		if (!StringUtils.isNotBlank(code)) {
			return ResponseBo.warn("验证码不能为空！");
		}
		Session session = super.getSession();
		String sessionCode = (String) session.getAttribute("_logincode");
		if (!code.toLowerCase().equals(sessionCode)) {
			return ResponseBo.warn("验证码错误！");
		}
		try {

			UserWali wali = userWaliService.getUser(phone);
			if (wali != null) {
				return ResponseBo.error("已经注册成功，不能重复注册");
			}
			String phoneCode = getRandNum(1, 999999);
			// 发送手机验证码
			if (userPhoneCodeService.addOrUpdate(phone, phoneCode) > 0) {
				logger.info("手机号：" + phone + "短信验证码：" + phoneCode);
				// SmsUtils.sendSms(phone, phoneCode);
				return ResponseBo.ok();
			} else {
				return ResponseBo.error("请稍后再试");
			}

		} catch (Exception e) {
			return ResponseBo.error("！");
		}
	}

	public String getRandNum(int min, int max) {
		int randNum = min + (int) (Math.random() * ((max - min) + 1));
		return String.valueOf(randNum);
	}

}
