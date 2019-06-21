package com.bwl.controller;

import com.bwl.pojo.User;
import com.bwl.service.UserService;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserService userService;

    @RequestMapping("/findAll.do")
    public ModelAndView findAllUser() {
        ModelAndView modelAndView = new ModelAndView();
        List<User> allUser = userService.findAllUser();
        modelAndView.addObject("allUser", allUser);
        modelAndView.setViewName("userList");
        return modelAndView;
    }

    @RequestMapping("/save.do")
    public String saveUser(User user) {
        boolean b = userService.addUser(user);
        return "redirect:findAll.do";
    }

    @RequestMapping("/{path}")
    public String path(@PathVariable String path) {
        return path.trim();
    }

    @RequestMapping("/deleteUser.do")
    public String deleteUser(User user) {
        boolean b = userService.deleteUserById(user);
        return "redirect:findAll.do";
    }

    @RequestMapping("/modify.do")
    public ModelAndView modify(User user) {
        ModelAndView mav = new ModelAndView();
        mav.addObject("user", userService.getUser(user));
        mav.setViewName("modifyUser");
        return mav;
    }

    @RequestMapping("/doModify.do")
    public String doModify(User user) {
        userService.updateUser(user);
        return "redirect:findAll.do";
    }

    @RequestMapping("login")
    public String loginSecurity(User user, HttpSession session) {
        String error;
        Subject subject = SecurityUtils.getSubject();
        System.out.println(user.getPassword()+">>>>>");
        UsernamePasswordToken token=new UsernamePasswordToken(user.getUsernum().trim(), user.getPassword().trim());
        try {
            subject.login(token);
        } catch (UnknownAccountException e) {
            error = "用户名/密码错误";
        } catch (IncorrectCredentialsException e) {
            error = "用户名/密码错误";
        } catch (AuthenticationException e) {
            //其他错误，比如锁定，如果想单独处理请单独catch处理
            error = "其他错误：" ;
            System.out.println(error);
        }catch (Exception e) {
            System.out.println("密码或用户名错误");
        }
        boolean authenticated = subject.isAuthenticated();
        System.out.println(authenticated+"====================");
        if (authenticated) {
            session.setAttribute("curUser",user);
            return "main";
        }

        return "redirect:/";
    }
}
