package com.igeek.ssm.controller;

import com.igeek.ssm.pojo.Items;
import com.igeek.ssm.service.ItemsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/items")
public class ItemsController {

    @Autowired
    private ItemsService service;

    //条件+查询商品列表
    @RequestMapping("/findAll.action")
    public String findAll(String query, Model model){
        //查询数据
        List<Items> itemsList = service.findAll(query);
        //将数据传递至请求域中
        model.addAttribute("itemsList",itemsList);
        //返回将要跳转的视图的逻辑名
        return "itemsList.jsp";
    }

}
