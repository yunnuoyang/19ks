package com.bwl.controller;

import com.bwl.pojo.ExportOrder;
import com.bwl.pojo.Orders;
import com.bwl.service.OrderService;
import com.bwl.util.ExportExcel;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletResponse;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/export")
public class ExportController {
    @Autowired
    private OrderService orderService;
    @RequestMapping("exportOrders.do")
    public void exportOrders(HttpServletResponse response){
        PageInfo<Orders> allOrder = orderService.getAllOrder(new Page<Orders>());
        //获取到所有的订单的信息
        List<Orders> list = allOrder.getList();
        List<ExportOrder> exportOrders=new ArrayList<ExportOrder>();
        ExportOrder exportOrder=null;
        for (Orders o:
             list) {
            exportOrder=new ExportOrder();
            exportOrder.setId(o.getId());
            exportOrder.setUsername(o.getUser().getUsername());
            exportOrder.setBooking(o.getBooking());
            exportOrder.setGoodsname(o.getGoods().getGoodsname());
            exportOrder.setReplaytime(o.getReplaytime());
            exportOrder.setStatus(o.getStatus());
            exportOrder.setNumber(o.getNumber());
            exportOrders.add(exportOrder);
        }
        ExportExcel<ExportOrder> exportExcel=new ExportExcel<ExportOrder>();
        String[] headers = {"id", "goodsname", "number", "booking", "replaytime"
        ,"username"
        };
        String fileName = "myExcel";
        exportExcel.exportExcel(headers,exportOrders,fileName,response);


    }
}
