package com.bwl.service;


import com.bwl.pojo.Role;

import java.util.List;

public interface RoleService {
    /**
     *获取角色信息
     * @return
     */
    List<Role> getAllRole();

    /**
     * 添加角色
     * @param role 角色实体
     */
    void addRole(Role role);

    /**
     * 删除角色
     * @param role 角色实体
     */
     void deleteRole(Role role);

    /**
     * 修改角色
     * @param role
     */
    void updateRole(Role role);

    Role selectRolesByUID(String roleid);
}
