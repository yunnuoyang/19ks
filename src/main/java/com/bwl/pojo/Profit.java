package com.bwl.pojo;

import java.util.Date;

public class Profit {
    private String id;

    private Long profit;

    private String goodsid;

    private Date beginmonth;

    private Date endmonth;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    public Long getProfit() {
        return profit;
    }

    public void setProfit(Long profit) {
        this.profit = profit;
    }

    public String getGoodsid() {
        return goodsid;
    }

    public void setGoodsid(String goodsid) {
        this.goodsid = goodsid == null ? null : goodsid.trim();
    }

    public Date getBeginmonth() {
        return beginmonth;
    }

    public void setBeginmonth(Date beginmonth) {
        this.beginmonth = beginmonth;
    }

    public Date getEndmonth() {
        return endmonth;
    }

    public void setEndmonth(Date endmonth) {
        this.endmonth = endmonth;
    }
}