package com.bwl.controller;

import com.bwl.pojo.Goods;
import com.bwl.service.GoodsService;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
@RequestMapping("/Good")
public class GoodsController {
    @Autowired
    private GoodsService goodService;

    @RequestMapping("/findAll.do")
    public ModelAndView findAll(Page<Goods> page) {
        ModelAndView modelAndView = new ModelAndView();
        if(page.getPageSize()==0){
            page.setPageSize(3);
        }
        PageInfo<Goods> list = goodService.findAllGoods(page);
        modelAndView.addObject("pageInfo", list);
        modelAndView.setViewName("product-list");
        return modelAndView;
    }
    @RequestMapping("goodsInfo.do")
    @ResponseBody
    public List<Goods> goodsInfo(){
        return goodService.getGoods();
    }

    @RequestMapping("/{path}")
    public String path(@PathVariable String path) {
        return path.trim();
    }

    @RequestMapping("/save.do")
    public String addGood(Goods good){
        goodService.addGood(good);
        return "redirect:findAll.do";
    }
    @RequestMapping("/update.do")
    public String updateGood(Goods goods){
        goodService.updateGood(goods);
        return "redirect:findAll.do";
    }
    @RequestMapping("/findGoodById.do")
    public ModelAndView findGoodById(Goods goods) {
        ModelAndView modelAndView = new ModelAndView();
        Goods goods1 = goodService.findGoodById(goods);
        modelAndView.addObject("good", goods1);
        modelAndView.setViewName("product-update");
        return modelAndView;
    }
    @RequestMapping("/deleteGoodById.do")
    public String deleteGoodById(String id, HttpServletRequest request){
        goodService.deleteGoodById(id);
        return "redirect:findAll.do";
    }
}
