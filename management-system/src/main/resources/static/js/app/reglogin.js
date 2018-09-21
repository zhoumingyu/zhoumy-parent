$(document).ready(function() {

	$('input').iCheck({
		checkboxClass : 'icheckbox_minimal-green',
		radioClass : 'iradio_minimal-green',
		increaseArea : '20%'
	});
	$("#codediv").hide();
	
});

function reloadRegeditCode() {
	$("#validateCodeImg").attr("src",
			ctx + "api/loginGifCode?data=" + new Date() + "");
}

var clock = '';
var nums = 60;
var btn;

function sendCode(thisBtn) {
	var phone = $(".one input[name='phone']").val().trim();
	var code = $(".one input[name='regeditcode']").val().trim();
	if (phone == "") {
		$MB.n_warning("手机号码不能为空！");
		return;
	}
	if (code == "") {
		$MB.n_warning("请输入验证码！");
		return;
	}
	$.ajax({
		type : "post",
		url : ctx + "api/getLoginPhoneCode",
		data : {
			"phone" : phone,
			"code" : code
		},
		async : false,
		dataType : "json",
		success : function(r) {
			if (r.code == 0) {
				$("#codediv").show();
				$("#codedivimg").hide();
				$("#codedivimgbtn").hide();
				reloadRegeditCode();
				btn = $("#reSend");
				btn.attr("disabled", true)
				btn.val(nums + '秒后可重新获取');
				clock = setInterval(doLoop, 1000); // 一秒执行一次
			} else {
				if (r.msg == '验证码错误！')
					reloadRegeditCode();
				$MB.n_warning(r.msg);
			}
		}
	});
}
function reSend() {
	$("#codedivimg").show();
	$("#codedivimgbtn").show();
	$("#codediv").hide();
}
function doLoop() {
	nums--;
	if (nums > 0) {
		btn.val(nums + '秒后可重新获取');
	} else {
		clearInterval(clock); // 清除js定时器
		btn.attr("disabled", false);
		btn.val('点击发送验证码');
		nums = 60; // 重置时间
	}
}

function regedit() {
	var $loginButton = $("#loginButton");
	var phone = $(".one input[name='phone']").val().trim();
	var code = $(".one input[name='code']").val().trim();

	if (phone == "") {
		$MB.n_warning("手机号码不能为空！");
		return;
	}
	if (code == "") {
		$MB.n_warning("请输入验证码！");
		return;
	}
	$loginButton
			.html("")
			.append(
					"<div class='login-loder'><div class='line-scale'><div></div><div></div><div></div><div></div><div></div></div></div>");

	$.ajax({
		type : "post",
		url : ctx + "api/regedit",
		data : {
			"phone" : phone,
			"code" : code
		},
		dataType : "json",
		success : function(r) {
			if (r.code == 0) {
				$MB.n_success("注册成功");
			} else {
				$MB.n_warning(r.msg);
			}
		}
	});
}

function regist() {
	var username = $(".two input[name='username']").val().trim();
	var password = $(".two input[name='password']").val().trim();
	var cpassword = $(".two input[name='cpassword']").val().trim();
	if (username == "") {
		$MB.n_warning("用户名不能为空！");
		return;
	} else if (username.length > 10) {
		$MB.n_warning("用户名长度不能超过10个字符！");
		return;
	} else if (username.length < 3) {
		$MB.n_warning("用户名长度不能少于3个字符！");
		return;
	}
	if (password == "") {
		$MB.n_warning("密码不能为空！");
		return;
	}
	if (cpassword == "") {
		$MB.n_warning("请再次输入密码！");
		return;
	}
	if (cpassword != password) {
		$MB.n_warning("两次密码输入不一致！");
		return;
	}
	$.ajax({
		type : "post",
		url : ctx + "user/regist",
		data : {
			"username" : username,
			"password" : password,
		},
		dataType : "json",
		success : function(r) {
			if (r.code == 0) {
				$MB.n_success("注册成功，请登录");
				$(".two input[name='username']").val("");
				$(".two input[name='password']").val("");
				$(".two input[name='cpassword']").val("");
				$('.form-toggle').trigger('click');
			} else {
				$MB.n_warning(r.msg);
			}
		}
	});
}

document.onkeyup = function(e) {
	if (window.event)
		e = window.event;
	var code = e.charCode || e.keyCode;
	if (code == 13) {
		login();
	}
}