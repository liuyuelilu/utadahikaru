package com.heroku.shiro;

import com.heroku.entity.UUser;
import com.heroku.entity.UUserExample;
import com.heroku.mapper.UUserMapper;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.*;

public class MyShiroRealm extends AuthorizingRealm {

    @Autowired
    UUserMapper uuserMapper;

    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        System.out.println("权限认证方法：MyShiroRealm.doGetAuthenticationInfo()");



        UUser token = (UUser) SecurityUtils.getSubject().getPrincipal();
        Integer userId = token.getId();
        SimpleAuthorizationInfo info =  new SimpleAuthorizationInfo();
        //根据用户ID查询角色（role），放入到Authorization里。
    /*Map<String, Object> map = new
    <String, Object>();
    map.put("user_id", userId);
    List<SysRole> roleList = sysRoleService.selectByMap(map);
    Set<String> roleSet = new HashSet<String>();
    for(SysRole role : roleList){
        roleSet.add(role.getType());
    }*/
        //实际开发，当前登录用户的角色和权限信息是从数据库来获取的，我这里写死是为了方便测试
        Set<String> roleSet = new HashSet<String>();
        roleSet.add("100002");
        info.setRoles(roleSet);
        //根据用户ID查询权限（permission），放入到Authorization里。
    /*List<SysPermission> permissionList = sysPermissionService.selectByMap(map);
    Set<String> permissionSet = new HashSet<String>();
    for(SysPermission Permission : permissionList){
        permissionSet.add(Permission.getName());
    }*/
        Set<String> permissionSet = new HashSet<String>();
        permissionSet.add("权限添加");
        info.setStringPermissions(permissionSet);
        return info;

    }

    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        System.out.println("身份认证方法：MyShiroRealm.doGetAuthenticationInfo()");

        UsernamePasswordToken token = (UsernamePasswordToken) authenticationToken;

        UUserExample exa = new UUserExample();
        exa.createCriteria().andEmailEqualTo(token.getUsername())
                .andPswdEqualTo(String.valueOf(token.getPassword()));

        List<UUser> uUsers = uuserMapper.selectByExample(exa);
        UUser uUser = null;

        if(uUsers.size()!=0){
            uUser = uUsers.get(0);
        }
        if (null == uUser) {
            throw new AccountException("帐号或密码不正确！");
        }else if(uUser.getStatus()==0){
            /**
             * 如果用户的status为禁用。那么就抛出<code>DisabledAccountException</code>
             */
            throw new DisabledAccountException("帐号已经禁止登录！");
        }else{
            //更新登录时间 last login time
            uUser.setLastLoginTime(new Date());
            uuserMapper.updateByPrimaryKey(uUser);
        }
        return new SimpleAuthenticationInfo(uUser, uUser.getPswd(), getName());

    }
}
