package com.heroku.controller;

import com.heroku.form.LoginForm;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.LinkedHashMap;
import java.util.Map;

@Controller
public class UserVaildContreller {

	@RequestMapping("index")
	String index() {
		return "index";
	}

	@RequestMapping("login")
	String login() {
		return "login";
	}

	@RequestMapping(value = "add", method = RequestMethod.POST)
	String add() {
		return "add";
	}

	/**
	 * ajax登录请求
	 * 
	 * @param loginForm
	 * @return
	 */
	@RequestMapping(value = "ajaxLogin", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> submitLogin(@RequestBody LoginForm loginForm) {
		Map<String, Object> resultMap = new LinkedHashMap<String, Object>();

		/*
		 * if(vcode==null||vcode==""){ resultMap.put("status", 500);
		 * resultMap.put("message", "验证码不能为空！"); return resultMap; }
		 */

		/*
		 * Session session = SecurityUtils.getSubject().getSession(); //转化成小写字母
		 * vcode = Svcode.toLowerCase(); String v = (String)
		 * session.getAttribute("_code"); //还可以读取一次后把验证码清空，这样每次登录都必须获取验证码
		 * //session.removeAttribute("_come"); if(!vcode.equals(v)){
		 * resultMap.put("status", 500); resultMap.put("message", "验证码错误！");
		 * return resultMap; }
		 */

		try {
			UsernamePasswordToken token = new UsernamePasswordToken(loginForm.getUsername(), loginForm.getPassword(),
					loginForm.getRememberMe());
			SecurityUtils.getSubject().login(token);
			resultMap.put("status", 200);
			resultMap.put("message", "登录成功");

		} catch (Exception e) {
			resultMap.put("status", 500);
			resultMap.put("message", e.getMessage());
		}
		return resultMap;
	}

}
