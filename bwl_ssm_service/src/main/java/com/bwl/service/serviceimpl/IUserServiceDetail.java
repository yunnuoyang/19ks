package com.bwl.service.serviceimpl;

import com.bwl.mapper.UserMapper;
import com.bwl.pojo.SecurityUser;
import com.bwl.pojo.User;
import com.bwl.pojo.UserExample;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("userService")
public class IUserServiceDetail implements UserDetailsService {
    @Autowired
    private UserMapper userMapper;

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        System.out.println(s);
        UserExample userExample = new UserExample();
        userExample.createCriteria().andUsernumEqualTo(s);
        List<User> users = userMapper.selectByExample(userExample);
        User user=null;
        if(users.size()>0) {
            user = users.get(0);
        }
        SecurityUser securityUser = new SecurityUser();
        return securityUser;
    }
}
