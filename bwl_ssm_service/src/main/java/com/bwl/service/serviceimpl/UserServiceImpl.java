package com.bwl.service.serviceimpl;

import com.bwl.mapper.ResourceMapper;
import com.bwl.mapper.RoleMapper;
import com.bwl.mapper.UserMapper;
import com.bwl.pojo.Role;
import com.bwl.pojo.RoleExample;
import com.bwl.pojo.User;
import com.bwl.pojo.UserExample;
import com.bwl.service.UserService;
import com.bwl.util.MD5;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserMapper userMapper;
    @Autowired
    private RoleMapper roleMapper;
    @Autowired
    private ResourceMapper resourceMapper;

    @Override
    public List<User> findAllUser() {
        return userMapper.selectByExample(null);
    }

    @Override
    public boolean deleteUserById(User user) {
        userMapper.deleteByPrimaryKey(user.getId());
        return false;
    }

    @Override
    public boolean addUser(User user) {
        //根据user的角色名称获取到id，添加进入用户表，角色表
        RoleExample roleExample = new RoleExample();
        roleExample.createCriteria().andRolenameEqualTo(user.getRoleid());
        List<Role> roles = roleMapper.selectByExample(roleExample);
        Role role = null;
        if (roles.size() > 0) {
            role = roles.get(0);
        }
//        int i1 = roleMapper.insertSelective(role);
        //给用户生成uuid作为主键
        user.setId(UUID.randomUUID().toString().substring(0, 31));
        user.setRoleid(role.getId());
        user.setPassword(MD5.toPassWord(user.getPassword()));
        int i = userMapper.insertSelective(user);
//        return i1 > 0 ? i > 0 ? true :false : false;
        return i > 0 ? true : false;
    }

    @Override
    public Integer updateUser(User user) {
        //封装查询条件的类
        RoleExample roleExample = new RoleExample();
        roleExample.createCriteria().andRolenameEqualTo(user.getRoleid());
        List<Role> roles = roleMapper.selectByExample(roleExample);
        Role role = null;
        if (roles.size() > 0) {
            role = roles.get(0);
        }
        user.setRoleid(role.getId());
        user.setPassword(MD5.toPassWord(user.getPassword()));
        UserExample userExample = new UserExample();
        userExample.createCriteria().andIdEqualTo(user.getId());
        return userMapper.updateByExampleSelective(user, userExample);
    }

    @Override
    public User getUser(User user) {
        User key = userMapper.selectByPrimaryKey(user.getId());
        return key;
    }

    @Override
    public User getUserByNum(String username) {
        UserExample userExample = new UserExample();
        userExample.createCriteria().andUsernumEqualTo(username);
        List<User> users = userMapper.selectByExample(userExample);
        return users.size()>0?users.get(0):null;
    }
}
