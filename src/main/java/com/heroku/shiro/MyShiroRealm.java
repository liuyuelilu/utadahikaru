package com.heroku.shiro;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AccountException;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.DisabledAccountException;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.heroku.entity.URolePermission;
import com.heroku.entity.URolePermissionExample;
import com.heroku.entity.UUser;
import com.heroku.entity.UUserExample;
import com.heroku.entity.UUserRole;
import com.heroku.entity.UUserRoleExample;
import com.heroku.mapper.URolePermissionMapper;
import com.heroku.mapper.UUserMapper;
import com.heroku.mapper.UUserRoleMapper;

public class MyShiroRealm extends AuthorizingRealm {
	// private final static Logger logger =
	// LoggerFactory.getLogger(MyShiroRealm.class);
	@Autowired
	UUserMapper uuserMapper;

	@Autowired
	UUserRoleMapper uUserRoleMapper;

	@Autowired
	URolePermissionMapper uRolePermissionMapper;
	// @Autowired
	// StringRedisTemplate stringRedisTemplate;
	//
	// // 用户登录次数计数 redisKey 前缀
	// private String SHIRO_LOGIN_COUNT = "shiro_login_count_";
	//
	// // 用户登录是否被锁定 一小时 redisKey 前缀
	// private String SHIRO_IS_LOCK = "shiro_is_lock_";

	@Override
	protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
		System.out.println("权限认证方法：MyShiroRealm.doGetAuthenticationInfo()");
		UUser token = (UUser) SecurityUtils.getSubject().getPrincipal();

		Integer userId = token.getId();
		SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
		// 根据用户ID查询角色（role），放入到Authorization里。

		// Map<String, Object> map = new HashMap<String, Object>();
		// map.put("user_id",userId);
		UUserRoleExample uUserRoleExample = new UUserRoleExample();
		uUserRoleExample.createCriteria().andUidEqualTo(userId);
		// 根据登录用户id查询该用户的角色（role）id
		List<UUserRole> uUserRolelist = uUserRoleMapper.selectByExample(uUserRoleExample);
		Set<String> roleSet = new HashSet<String>();

		for (UUserRole uUserRole : uUserRolelist) {
			// logger.info(uUserRole.getRid().toString());
			roleSet.add(uUserRole.getRid().toString());

		}

		// 实际开发，当前登录用户的角色和权限信息是从数据库来获取的，我这里写死是为了方便测试
		// Set<String> roleSet = new HashSet<String>();
		// roleSet.add("100002");
		info.setRoles(roleSet);
		// 根据用户ID查询权限（permission），放入到Authorization里。
		/*
		 * List<SysPermission> permissionList =
		 * sysPermissionService.selectByMap(map); Set<String> permissionSet =
		 * new HashSet<String>(); for(SysPermission Permission :
		 * permissionList){ permissionSet.add(Permission.getName()); }
		 */
		// permissionSet.add("admin");
		List<Integer> roleList = new ArrayList<Integer>();

		for (String role : roleSet) {
			roleList.add(Integer.parseInt(role));
		}

		URolePermissionExample uRolePermissionExample = new URolePermissionExample();
		uRolePermissionExample.createCriteria().andRidIn(roleList);
		List<URolePermission> uRolePermissionlist = uRolePermissionMapper.selectByExample(uRolePermissionExample);

		Set<String> permissionSet = new HashSet<String>();
		for (URolePermission uRolePermission : uRolePermissionlist) {
			permissionSet.add(uRolePermission.getPid().toString());
		}
		info.setStringPermissions(permissionSet);
		return info;

	}

	@Override
	protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken)
			throws AuthenticationException {
		System.out.println("身份认证方法：MyShiroRealm.doGetAuthenticationInfo()");

		UsernamePasswordToken token = (UsernamePasswordToken) authenticationToken;

		//
		// String name = token.getUsername();
		// // 访问一次，计数一次
		// stringRedisTemplate = new StringRedisTemplate();
		// ValueOperations<String, String> opsForValue =
		// stringRedisTemplate.opsForValue();
		// opsForValue.increment(SHIRO_LOGIN_COUNT + name, 1);
		// // 计数大于5时，设置用户被锁定一小时
		// if (Integer.parseInt(opsForValue.get(SHIRO_LOGIN_COUNT + name)) >= 5)
		// {
		// opsForValue.set(SHIRO_IS_LOCK + name, "LOCK");
		// stringRedisTemplate.expire(SHIRO_IS_LOCK + name, 1,
		// TimeUnit.MINUTES);
		// }
		// if ("LOCK".equals(opsForValue.get(SHIRO_IS_LOCK + name))) {
		// throw new DisabledAccountException("由于密码输入错误次数大于5次，帐号已经禁止1分钟！");
		// }
		UUserExample exa = new UUserExample();
		exa.createCriteria().andEmailEqualTo(token.getUsername()).andPswdEqualTo(String.valueOf(token.getPassword()));
		List<UUser> uUsers = uuserMapper.selectByExample(exa);

		UUser uUser = null;

		if (uUsers.size() != 0) {
			uUser = uUsers.get(0);
		}
		if (null == uUser) {
			throw new AccountException("帐号或密码不正确！");
		} else if (uUser.getStatus() == 0) {
			/**
			 * 如果用户的status为禁用。那么就抛出<code>DisabledAccountException</code>
			 */
			throw new DisabledAccountException("帐号已经禁止登录！");
		} else {
			// 更新登录时间 last login time
			uUser.setLastLoginTime(new Date());
			uuserMapper.updateByPrimaryKey(uUser);
		}
		return new SimpleAuthenticationInfo(uUser, uUser.getPswd(), getName());
	}

}
