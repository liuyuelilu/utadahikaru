package com.heroku.config;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.apache.shiro.web.session.mgt.DefaultWebSessionManager;
import org.crazycake.shiro.RedisManager;
import org.crazycake.shiro.RedisSessionDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.heroku.entity.UPermission;
import com.heroku.entity.UPermissionExample;
import com.heroku.mapper.UPermissionMapper;
import com.heroku.shiro.MyShiroRealm;

@Configuration
public class ShiroConfig {

	@Value("${spring.redis.url}")
	private String url;

	@Value("${spring.redis.timeout}")
	private int timeout;

	@Autowired
	UPermissionMapper uPermissionMapper;

	/**
	 * ShiroFilterFactoryBean 处理拦截资源文件问题。 w
	 * 注意：单独一个ShiroFilterFactoryBean配置是或报错的，以为在
	 * 初始化ShiroFilterFactoryBean的时候需要注入：SecurityManager
	 *
	 * Filter Chain定义说明 1、一个URL可以配置多个Filter，使用逗号分隔 2、当设置多个过滤器时，全部验证通过，才视为通过
	 * 3、部分过滤器可指定参数，如perms，roles
	 *
	 */
	@Bean
	public ShiroFilterFactoryBean shirFilter(SecurityManager securityManager) {
		ShiroFilterFactoryBean shiroFilterFactoryBean = new ShiroFilterFactoryBean();
		System.out.println("Shiro拦截器工厂类注入开始");
		// 必须设置 SecurityManager
		shiroFilterFactoryBean.setSecurityManager(securityManager);

		// 如果不设置默认会自动寻找Web工程根目录下的"/login.jsp"页面
		shiroFilterFactoryBean.setLoginUrl("/login");
		// 登录成功后要跳转的链接
		shiroFilterFactoryBean.setSuccessUrl("/index");
		// 未授权界面;
		shiroFilterFactoryBean.setUnauthorizedUrl("/403");

		// 拦截器.
		Map<String, String> filterChainDefinitionMap = new LinkedHashMap<String, String>();

		UPermissionExample uPermissionExample = new UPermissionExample();

		// uPermissionExample.createCriteria().andIdBetween(1, 5);
		// uPermissionExample.createCriteria().andNameEqualTo("anon");
		uPermissionExample.setOrderByClause("id");

		List<UPermission> uPermissionlist = uPermissionMapper.selectByExample(uPermissionExample);

		int i = 1;
		for (UPermission uPermission : uPermissionlist) {
			System.out.println("uPermission" + "第" + i + "个" + uPermission);
			System.out.println("uPermission:::::::::" + uPermission.getId() + uPermission.getUrl() + "---"
					+ uPermission.getName());
			filterChainDefinitionMap.put(uPermission.getUrl(), uPermission.getName());
			i++;
		}

		// // 配置不会被拦截的链接 顺序判断
		// filterChainDefinitionMap.put("/fragments/**", "anon");
		// filterChainDefinitionMap.put("/webjars/**", "anon");
		// filterChainDefinitionMap.put("/js/**", "anon");
		// filterChainDefinitionMap.put("/stylesheets/**", "anon");
		// filterChainDefinitionMap.put("/ajaxLogin", "anon");
		//
		// 配置退出过滤器,其中的具体的退出代码Shiro已经替我们实现了
		// filterChainDefinitionMap.put("/logout", "logout");
		//
		// filterChainDefinitionMap.put("/add", "perms[a]");
		//
		// // <!-- 过滤链定义，从上向下顺序执行，一般将 /**放在最为下边 -->:这是一个坑呢，一不小心代码就不好使了;
		// // <!-- authc:所有url都必须认证通过才可以访问; anon:所有url都都可以匿名访问-->
		// filterChainDefinitionMap.put("/**", "authc");

		shiroFilterFactoryBean.setFilterChainDefinitionMap(filterChainDefinitionMap);
		System.out.println("Shiro拦截器工厂类注入成功");
		return shiroFilterFactoryBean;
	}

	@Bean
	public SecurityManager securityManager() throws URISyntaxException {
		System.out.println("config：securityManager()");
		DefaultWebSecurityManager securityManager = new DefaultWebSecurityManager();
		// 设置realm.
		securityManager.setRealm(myShiroRealm());

		// 自定义session管理 使用redis
		securityManager.setSessionManager(sessionManager());

		return securityManager;
	}

	/**
	 * 配置shiro redisManager 使用的是shiro-redis开源插件
	 * 
	 * @return
	 */
	public RedisManager redisManager() throws URISyntaxException {
		System.out.println("config：redisManager()");
		RedisManager redisManager = new RedisManager();
		URI uri = new URI(url);

		redisManager.setHost(uri.getHost());
		redisManager.setPort(uri.getPort());
		redisManager.setExpire(1800);// 配置缓存过期时间
		redisManager.setTimeout(timeout);
		redisManager.setPassword(uri.getUserInfo().split(":")[1]);
		return redisManager;
	}

	/**
	 * RedisSessionDAO shiro sessionDao层的实现 通过redis 使用的是shiro-redis开源插件
	 */
	@Bean
	public RedisSessionDAO redisSessionDAO() throws URISyntaxException {
		System.out.println("config：redisSessionDAO()");
		RedisSessionDAO redisSessionDAO = new RedisSessionDAO();
		redisSessionDAO.setRedisManager(redisManager());
		return redisSessionDAO;
	}

	/**
	 * Session Manager 使用的是shiro-redis开源插件
	 */
	@Bean
	public DefaultWebSessionManager sessionManager() throws URISyntaxException {
		System.out.println("config：sessionManager()");
		DefaultWebSessionManager sessionManager = new DefaultWebSessionManager();
		sessionManager.setSessionDAO(redisSessionDAO());
		return sessionManager;
	}

	/**
	 * 身份认证realm; (这个需要自己写，账号密码校验；权限等)
	 *
	 * @return
	 */
	@Bean
	public MyShiroRealm myShiroRealm() {
		System.out.println("config：myShiroRealm()");
		MyShiroRealm myShiroRealm = new MyShiroRealm();
		return myShiroRealm;
	}
}