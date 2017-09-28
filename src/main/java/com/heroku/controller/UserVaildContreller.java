package com.heroku.controller;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.heroku.entity.UPay;
import com.heroku.entity.UPayExample;
import com.heroku.entity.UUser;
import com.heroku.entity.UUserExample;
import com.heroku.form.LoginForm;
import com.heroku.mapper.UPayMapper;
import com.heroku.mapper.UUserMapper;

import net.minidev.json.JSONArray;

@Controller
public class UserVaildContreller {
	private final static Logger logger = LoggerFactory.getLogger(UserVaildContreller.class);
	@Autowired
	UUserMapper uuserMapper;
	@Autowired
	UPayMapper uPayMapper;

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

	@RequestMapping(value = "user2", method = RequestMethod.POST)
	String user2() {
		return "user2";
	}

	@RequestMapping(value = "user3", method = RequestMethod.POST)
	String user3() {
		return "user3";
	}

	@RequestMapping(value = "user4", method = RequestMethod.POST)
	String user4() {
		return "user4";
	}

	@RequestMapping(value = "add-s", method = RequestMethod.POST)
	@ResponseBody
	public List<UPay> add2() {
		UPayExample uPayExample = new UPayExample();
		uPayExample.setOrderByClause("id");
		List<UPay> uPayList = uPayMapper.selectByExample(uPayExample);
		return uPayList;
	}

	@RequestMapping(value = "user-s", method = RequestMethod.POST)
	@ResponseBody
	public List<Map<String, Object>> user22() {

		UUser token = (UUser) SecurityUtils.getSubject().getPrincipal();
		UUserExample exa = new UUserExample();
		if (!"nie".equals(token.getNickname())) {
			exa.createCriteria().andEmailEqualTo(token.getEmail());
		}
		exa.setOrderByClause("id");
		List<UUser> uUserList = uuserMapper.selectByExample(exa);
		List<Map<String, Object>> resultMapList = new ArrayList<Map<String, Object>>();

		for (UUser uUser : uUserList) {
			Map<String, Object> resultMap = new LinkedHashMap<String, Object>();
			resultMap.put("Id", uUser.getId());
			resultMap.put("NickName", uUser.getNickname());
			resultMap.put("Email", uUser.getEmail());
			resultMap.put("Pswd", uUser.getPswd());
			resultMap.put("CreateTime", uUser.getCreateTime());
			resultMap.put("LastLoginTime", uUser.getLastLoginTime());
			resultMap.put("Status", uUser.getStatus());
			resultMapList.add(resultMap);
		}

		return resultMapList;
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