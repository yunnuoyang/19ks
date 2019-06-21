package com.bwl.service.serviceimpl;

import com.bwl.mapper.GoodsMapper;
import com.bwl.mapper.OrdersMapper;
import com.bwl.mapper.UserMapper;
import com.bwl.pojo.Goods;
import com.bwl.pojo.Orders;
import com.bwl.pojo.User;
import com.bwl.pojo.UserExample;
import com.bwl.service.OrderService;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Date;
import java.util.List;
import java.util.UUID;

@Service
@Transactional
public class OrderServiceImpl implements OrderService {
    @Autowired
    private OrdersMapper ordersMapper;
    @Autowired
    private GoodsMapper goodsMapper;
    @Autowired
    private UserMapper userMapper;

    @Override
    public PageInfo<Orders> getAllOrder(Page<Orders> page) {
        PageHelper.startPage(page.getPageNum(), page.getPageSize());
        //获取订单表的所有信息，缺少商品名
        List<Orders> orders = ordersMapper.selectByExample(null);
        PageInfo<Orders> pageInfo = new PageInfo<Orders>(orders);
        List<Orders> list = pageInfo.getList();
        for (Orders o :
                list) {
            Goods goods = goodsMapper.selectByPrimaryKey(o.getGoodsid());
            User user = userMapper.selectByPrimaryKey(o.getUserid());
            o.setGoods(goods);
            o.setUser(user);
        }
        pageInfo.setList(orders);
        return pageInfo;
    }

    @Override
    public void deleteOrder(Orders orders) {
        ordersMapper.deleteByPrimaryKey(orders.getId());
    }

    @Override
    public void addOrder(Orders orders, String usernum) {
        //根据员工工号查询员工的id
        UserExample userExample = new UserExample();
        userExample.createCriteria().andUsernumEqualTo(usernum);
        List<User> users = userMapper.selectByExample(userExample);
        String uid = users.get(0).getId();
        //给订单设置用户的id
        orders.setUserid(uid);
        //获取商品的id
        String goodsid = orders.getGoodsid();
        orders.setId(UUID.randomUUID().toString().substring(0, 31));
        //获取系统当前时间
        long l = System.currentTimeMillis();
        Date date = new Date(l);
        orders.setBooking(date);
        orders.setStatus(0l);
        ordersMapper.insertSelective(orders);
    }

    @Override
    public void updateOrder(Orders orders) {

    }

    @Override
    public void rufuseOrder(String id) {
        Orders orders = new Orders();
        orders.setId(String.valueOf(id));
        //默认时间1970-01-01年
        orders.setReplaytime(new Date(20000000));
        orders.setStatus(0l);
        ordersMapper.updateByPrimaryKeySelective(orders);
    }

    /**
     * 通过id将订单批准通过
     *
     * @param id
     */
    @Override
    public void permissionOrder(Orders orders) {
        //获取系统当前时间
        long l = System.currentTimeMillis();
        Date date = new Date(l);
        Orders orders1 = getOrderById(orders.getId());
        System.out.println(orders1.getGoodsid()+orders1.getNumber());
//        //获取到商品的信息,更新商品表
        Goods goods = goodsMapper.selectByPrimaryKey(orders1.getGoodsid());
        goods.setAmount(goods.getAmount() + orders.getNumber());
        goodsMapper.updateByPrimaryKeySelective(goods);
        orders.setReplaytime(date);
        orders.setStatus(1l);
        ordersMapper.updateByPrimaryKeySelective(orders);
    }
    private Orders getOrderById(String id) {
        Orders orders = ordersMapper.selectByPrimaryKey(id.trim());
        return orders;
    }
}
