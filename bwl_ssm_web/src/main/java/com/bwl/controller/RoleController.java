package com.bwl.controller;

import com.bwl.pojo.Role;
import com.bwl.service.RoleService;
import net.sf.jsqlparser.expression.operators.relational.JsonOperator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
@RequestMapping("/role")
public class RoleController {
    @Autowired
    private RoleService roleService;

    @RequestMapping("/getRoles.do")
    @ResponseBody
    public List<Role> getRoles() {
        List<Role> allRole = roleService.getAllRole();
        return allRole;
    }
}
