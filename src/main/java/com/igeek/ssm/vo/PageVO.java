package com.igeek.ssm.vo;

import com.igeek.ssm.pojo.Items;

import java.util.List;

//分页的VO类
public class PageVO {

    private Integer pageNow;   //当前页
    private Integer myPages;   //总页数
    private String  query;     //查询条件
    private Integer begin;     //每页起始值
    private List<Items> itemsList;  //每页记录

    public PageVO() {
    }

    public PageVO(Integer pageNow, Integer myPages, String query, Integer begin, List<Items> itemsList) {
        this.pageNow = pageNow;
        this.myPages = myPages;
        this.query = query;
        this.begin = begin;
        this.itemsList = itemsList;
    }

    public Integer getPageNow() {
        return pageNow;
    }

    public void setPageNow(Integer pageNow) {
        this.pageNow = pageNow;
    }

    public Integer getMyPages() {
        return myPages;
    }

    public void setMyPages(Integer myPages) {
        this.myPages = myPages;
    }

    public String getQuery() {
        return query;
    }

    public void setQuery(String query) {
        this.query = query;
    }

    public Integer getBegin() {
        return begin;
    }

    public void setBegin(Integer begin) {
        this.begin = begin;
    }

    public List<Items> getItemsList() {
        return itemsList;
    }

    public void setItemsList(List<Items> itemsList) {
        this.itemsList = itemsList;
    }

    @Override
    public String toString() {
        return "PageVO{" +
                "pageNow=" + pageNow +
                ", myPages=" + myPages +
                ", query='" + query + '\'' +
                ", begin=" + begin +
                ", itemsList=" + itemsList +
                '}';
    }
}
