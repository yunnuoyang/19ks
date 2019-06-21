package com.bwl.service.serviceimpl;

import com.bwl.mapper.RoleMapper;
import com.bwl.pojo.Role;
import com.bwl.pojo.RoleExample;
import com.bwl.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
@Service
@Transactional
public class RoleServiceImpl implements RoleService {
    @Autowired
    private RoleMapper roleMapper;
    @Override
    public List<Role> getAllRole() {
        return roleMapper.selectByExample(null);
    }

    @Override
    public void addRole(Role role) {
        roleMapper.insertSelective(role);
    }

    @Override
    public void deleteRole(Role role) {
        roleMapper.deleteByPrimaryKey(role.getId());
    }

    @Override
    public void updateRole(Role role) {
        RoleExample roleExample=new RoleExample();
        roleMapper.updateByExampleSelective(role,roleExample);
    }

    @Override
    public Role selectRolesByUID(String roleid) {
        Role role = roleMapper.selectByPrimaryKey(roleid);
        return role;
    }
}
