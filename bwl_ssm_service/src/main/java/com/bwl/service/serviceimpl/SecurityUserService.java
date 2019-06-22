package com.bwl.service.serviceimpl;

import com.bwl.mapper.RoleMapper;
import com.bwl.mapper.UserMapper;
import com.bwl.pojo.Role;
import com.bwl.pojo.User;
import com.bwl.pojo.UserExample;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

//@Service("userService")
public class SecurityUserService implements UserDetailsService {
    @Autowired
    private UserMapper userMapper;
    @Autowired
    private RoleMapper roleMapper;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserExample userExample=new UserExample();
        //根据员工号登陆，找到用户
        userExample.createCriteria().andUsernumEqualTo(username);
        List<User> users = userMapper.selectByExample(userExample);
        //登陆的用户的所有信息
        User user=null;
        if(users.size()>0){
             user = users.get(0);
        }
        //根据用户的角色id查询到角色信息
        Role role = roleMapper.selectByPrimaryKey(user.getRoleid());


        return null;
    }
}
