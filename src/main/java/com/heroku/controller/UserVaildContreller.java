package com.heroku.controller;

import java.util.Date;
import java.util.LinkedHashMap;
import java.util.Map;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.heroku.entity.UUser;
import com.heroku.entity.UUserExample;
import com.heroku.form.LoginForm;
import com.heroku.mapper.UUserMapper;
import com.heroku.util.MyDES;

@Controller
public class UserVaildContreller {
	@Autowired
	UUserMapper umaper ;

    @RequestMapping("/")
    String index() {
        return "index";
    }

    @RequestMapping("login")
    String login() {
        return "login";
    }

    @RequestMapping("add")
    String add() {
        return "add";
    }
    @RequestMapping("addsuccess")
    String addsuccess() {
        return "addsuccess";
    }
    
    @RequestMapping("db")
    String db() {
        return "db";
    }

    /**
     * ajax登录请求
     * @param loginForm
     * @return
     */
    @RequestMapping(value="ajaxLogin",method= RequestMethod.POST)
    @ResponseBody
    public Map<String,Object> submitLogin(@RequestBody LoginForm loginForm) {
        Map<String, Object> resultMap = new LinkedHashMap<String, Object>();

/*        if(vcode==null||vcode==""){
            resultMap.put("status", 500);
            resultMap.put("message", "验证码不能为空！");
            return resultMap;
        }*/

/*        Session session = SecurityUtils.getSubject().getSession();
        //转化成小写字母
        vcode = Svcode.toLowerCase();
        String v = (String) session.getAttribute("_code");
        //还可以读取一次后把验证码清空，这样每次登录都必须获取验证码
        //session.removeAttribute("_come");
        if(!vcode.equals(v)){
            resultMap.put("status", 500);
            resultMap.put("message", "验证码错误！");
            return resultMap;
        }*/

        try {
            UsernamePasswordToken token = new UsernamePasswordToken(loginForm.getUsername(), loginForm.getPassword(),loginForm.getRememberMe());
           
            SecurityUtils.getSubject().login(token);
            resultMap.put("status", 200);
            resultMap.put("message", "登录成功");

        } catch (Exception e) {
            resultMap.put("status", 500);
            resultMap.put("message", e.getMessage());
        }
        return resultMap;
    }

    /**
	 * 退出
	 * @return
	 */
	@RequestMapping(value="logout",method =RequestMethod.GET)
	@ResponseBody
	public Map<String,Object> logout(){
		Map<String, Object> resultMap = new LinkedHashMap<String, Object>();
		try {
			//退出
			SecurityUtils.getSubject().logout();
		} catch (Exception e) {
			System.err.println(e.getMessage());
		}
		return resultMap;
	}

	/**
	 * 添加
	 * @return
	 */
		@RequestMapping(value = "adduser")
	
		public String add(AuthenticationToken authenticationToken,UUser user,Model model) {
		UsernamePasswordToken token = (UsernamePasswordToken) authenticationToken;	
		UUserExample exa = new UUserExample();
		
	    //密码加密
		String password = String.valueOf(user.getPswd());	
	
		String pawDES = MyDES.encryptBasedDes(password);
		
		user.setPswd(pawDES);
		
		//创建时间应当获取当前的时间
		
	    user.setCreateTime(new Date());
	    
		//确认此账号是否唯一
	    exa.createCriteria().andNicknameEqualTo(token.getUsername());
	    if(umaper.selectByExample(exa).size()!=0){
	    	return "false";
	    }else{
			if (umaper.insertSelective(user)==1) {
				return "/addsuccess";
			} else {
				return "/add";
			}
		}
		}


		/**
		 * 保持唯一
		 * @return
		 */
			@RequestMapping(value = "oneuser")
		
			public @ResponseBody boolean oneuser(UUser user,Model model) {
//			Map<String, Object> resultMap = new LinkedHashMap<String, Object>();	
				
			UUserExample exa = new UUserExample();
			
			//确认此账号是否唯一
		    exa.createCriteria().andNicknameEqualTo(user.getNickname());
//		    if(umaper.selectByExample(exa).size()!=0){
//		    	 resultMap.put("sa", false);
//		    	 return resultMap;
//		    }else{
//		    	resultMap.put("sa", true);
//			    return resultMap;
//			}
		    return umaper.selectByExample(exa).size()==0;
		    
			}			
}
