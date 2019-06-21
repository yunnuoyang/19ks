package com.bwl.service.serviceimpl;


import com.bwl.mapper.GoodsMapper;
import com.bwl.pojo.Goods;
import com.bwl.pojo.GoodsExample;
import com.bwl.service.GoodsService;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class GoodServiceImpl implements GoodsService {
    @Autowired
    private GoodsMapper goodsMapper;

    @Override
    public PageInfo<Goods> findAllGoods(Page<Goods> page) {
        PageHelper.startPage(page.getPageNum(),page.getPageSize());
       List<Goods> goods = goodsMapper.selectByExample(null);
       PageInfo<Goods> pageInfo = new PageInfo<Goods>(goods);
        List<Goods> list = pageInfo.getList();
        pageInfo.setList(goods);
        return pageInfo;
    }

    @Override
    public boolean deleteGoodById(String id) {
       GoodsExample goodsExample = new GoodsExample();
       GoodsExample.Criteria criteria = goodsExample.createCriteria();
       criteria.andIdEqualTo(String.valueOf(id));
       goodsMapper.deleteByExample(goodsExample);
       return goodsMapper.deleteByPrimaryKey(String.valueOf(id))>0?true:false;
    }
    @Override
    public Goods findGoodById(Goods goods) {
        System.out.println(goods.getId());
       Goods goods1 = goodsMapper.selectByPrimaryKey(goods.getId());
        return goods1;
    }

    @Override
    public List<Goods> getGoods() {
        return goodsMapper.selectByExample(null);
    }

    @Override
    public void addGood(Goods goods) {
            goods.setId(UUID.randomUUID().toString().substring(0,31));
        goodsMapper.insertSelective(goods);
    }

    @Override
    public int updateGood(Goods goods) {
   GoodsExample goodsExample = new GoodsExample();
         return goodsMapper.updateByPrimaryKey(goods);
//        return goodsMapper.updateByExampleSelective(goods,goodsExample);
    }


}
