package com.bwl.controller;

import com.bwl.mapper.ResourceMapper;
import com.bwl.pojo.Role;
import com.bwl.pojo.User;
import com.bwl.service.RoleService;
import com.bwl.service.UserService;
import com.bwl.util.MD5;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @author DELL
 * doGetAuthenticationInfo
 */
public class UserRealm extends AuthorizingRealm {
    @Autowired
    private UserService userService;
    @Autowired
    private RoleService roleService;
    //    @Autowired
//    private RolePermissionMapper rolePermissionMapper;
//    @Autowired
//    private PermissionMapper permissionMapper;
    @Autowired
    private ResourceMapper resourceMapper;

    @Override
    protected void clearCache(PrincipalCollection principals) {
        //获取到认证之后的信息
        PrincipalCollection collection = SecurityUtils.getSubject().getPrincipals();
        //手动调用clearCache方法进行 清空缓存操作
        super.clearCache(collection);
    }

    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
        //获取登录时输入的用户名
        String username = principals.getPrimaryPrincipal().toString();
        SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
        if (username != null) {
            //获取用户信息
            User user = userService.getUserByNum(username);
//            //获取角色信息
            Role role = roleService.selectRolesByUID(user.getRoleid());
//            System.out.println(role.getRolename());
            info.addRole(role.getRolename());

//            roles.forEach(role -> {
//                //获取角色的id
//                Long id = role.getId();
//                //根据角色的id获取到资源的许可
//                Resource permission = ResourceMapper.selectByPrimaryKey(id);
//                info.addStringPermission(permission.getName());
//            });

//			for(Role role:roles){
//				info.addRole(role.getName());//加入角色
//				System.out.println(role.getName());
//			}
//            if (roles != null && !roles.isEmpty()) {
//                for (Role role : roles) {
//                    info.addRole(role.getName());//加入角色
//                    System.out.println(role.getName());
//                }
//            }
            return info;
        }
        return null;
    }

    @Override
    public String getName() {
        return "userRealm";
    }

    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
        String username = (String) token.getPrincipal();//获取身份信息
//        System.out.println(username);

//        //通过用户名获取到用户的密码信息
//        //根据用户名到数据库查询密码信息
        User user1 = userService.getUserByNum(username);
//        System.out.println("获取用户输入的用户名=======================" + username + user1.getUsernum() + user1.getPassword());
//        System.out.println(user1.getPassword());
////		//将从数据库中查询到的信息封装到SimpleAuthenticationInfo
        user1.setPassword(MD5.toPassWord(user1.getPassword()));
        SimpleAuthenticationInfo info = new SimpleAuthenticationInfo(username, user1.getPassword().trim(), getName());
//        SimpleAccount simpleAccount=new SimpleAccount(username, user1.getPassword().trim(), getName());
//
//		SimpleAuthenticationInfo info=new SimpleAuthenticationInfo(username, user1.getPassword(), ByteSource.Util.bytes(user1.getPassword_salt().getBytes()), getName());
        return info;
    }

}
