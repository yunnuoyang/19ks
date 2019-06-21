package com.bwl.service;

import com.bwl.pojo.Goods;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageInfo;

import java.util.List;

public interface GoodsService {
    /**
     * 查询所有的商品
     *
     * @return
     */
    PageInfo<Goods> findAllGoods(Page<Goods> page);
    /**
     * 删除商品，根据商品的id
     *
     * @param good 封装商品id
     * @return 返回boolean值进行判定
     */
    boolean deleteGoodById(String id);

    /**
     * 增加商品
     * @param good 封装商品信息
     */
    void addGood(Goods goods);

    /**
     * 修改商品
     * @param good 商品实体
     * @return 影响行数
     */
    int updateGood(Goods goods);


    Goods findGoodById(Goods goods);

    List<Goods> getGoods();
}
