package com.bwl.service;



import com.bwl.pojo.Orders;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageInfo;

import java.util.List;

/**
 * 订单业务层
 */
public interface OrderService {
    /**
     * 查询所有的订单信息
     * @return 所有的订单信息
     */
    PageInfo<Orders> getAllOrder(Page<Orders> page);

    /**
     * 删除订单
     * @param order 订单
     */
    void deleteOrder(Orders order);

    /**
     * 添加订单
     * @param order
     * @param usernum
     */
    void addOrder(Orders order, String usernum);

    /**
     * 修改订单
     * @param order
     */
    void updateOrder(Orders order);

    void rufuseOrder(String id);

    void permissionOrder(Orders orders);
}
