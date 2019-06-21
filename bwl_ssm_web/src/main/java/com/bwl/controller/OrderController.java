package com.bwl.controller;

import com.bwl.pojo.Orders;
import com.bwl.pojo.User;
import com.bwl.service.OrderService;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageInfo;
import org.apache.shiro.authz.annotation.RequiresRoles;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
@RequiresRoles("经理")
@RequestMapping("orders")
public class OrderController {
    @Autowired
    private OrderService orderService;

    @RequestMapping("findAll.do")
    public ModelAndView findAll(Page<Orders> page) {
//        page.setPageNum(0);
        if(page.getPageSize()==0){
            page.setPageSize(2);//默认设置为2条数据
        }

        ModelAndView mav = new ModelAndView();
        PageInfo<Orders> allOrder = orderService.getAllOrder(page);

        mav.addObject("pageInfo", allOrder);
        mav.setViewName("orderList");
        return mav;
    }

    @RequestMapping("/deleteOrders.do")
    public String deleteOrders(@Validated Orders orders, BindingResult bindingResult) {
        orderService.deleteOrder(orders);
        return "redirect:findAll.do";
    }

    @RequestMapping("/addOrders.do")
    public String addOrders() {
        return "applayOrders";
    }

    /**
     * 拒绝订单通过
     *
     * @return
     */
    @RequestMapping("/rufuseOrder.do")
    public String rufuseOrder(String id) {
        orderService.rufuseOrder(id);
        return "redirect:findAll.do";
    }

    @RequestMapping("/permission.do")
    public String permissionOrder(Orders orders) {
        orderService.permissionOrder(orders);
        return "redirect:findAll.do";
    }

    /**
     * 提交订单
     *
     * @param orders
     * @return
     */
    @RequestMapping("/save.do")
    public String saveOrder(Orders orders, HttpSession session) {
        //获取当前用户的信息：用户的工号
        User user = (User) session.getAttribute("curUser");
        String usernum = user.getUsernum();
        System.out.println(orders.getNumber());
        orderService.addOrder(orders, usernum);
        return "redirect:findAll.do";
    }
}
