package com.igeek.ssm.controller;

import com.igeek.ssm.pojo.Items;
import com.igeek.ssm.service.ItemsService;
import com.igeek.ssm.vo.PageVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.UUID;

@Controller
@RequestMapping("/items")
public class ItemsController {

    @Autowired
    private ItemsService service;

    /**
     * 条件+查询商品列表
     * 参数绑定：
     * 1.若参数是简单类型，要求请求参数中的key与方法的形参名称一致
     *   若是上传图片，要求请求参数中的key与方法的形参名称一致
     * 2.若参数是pojo类型，要求请求参数中的key与方法的形参pojo中的属性名称一致
     * 注意：
     *   url?key1=value1&key2=value2       请求参数就是key1、key2
     *   form表单中的input标签的name="key"  请求参数就是key
     */
    @RequestMapping("/findAll.action")
    public String findAll(String query , Integer pageNow , Model model){
        //查询PageVO
        PageVO vo = service.findAll(query, pageNow);
        //将数据传递至请求域中
        model.addAttribute("vo",vo);
        //返回将要跳转的视图的逻辑名
        return "itemsList.jsp";
    }

    //添加商品  MultipartFile 实现上传图片
    @RequestMapping("/add.action")
    public String add(Items items, MultipartFile file) throws IOException {
        //上传图片
        if(file!=null){
            //获得原始图片名称
            String oldName = file.getOriginalFilename();
            System.out.println("oldName = "+oldName);
            //当前若上传图片
            if(oldName!=null && oldName.length()>0){
                //产生新的图片名称 = 随机数+原图片的后缀
                String newName = UUID.randomUUID()+oldName.substring(oldName.lastIndexOf("."));

                //将此图片上传至本地图片服务器路径
                file.transferTo(new File("E:/ssm/day3/temp/"+newName));

                //将此图片传值到items商品中
                items.setPic("/pic/"+newName);
            }
        }

        //添加商品
        service.add(items);
        //为了避免重复提交表单的操作，可以选择重定向，地址栏显示目标地址
        return "redirect:findAll.action";
    }

    //根据id查询商品
    @RequestMapping("/findOne.action")
    public String findOne(Integer id , Model model){
        Items items = service.findOne(id);
        //将数据传递至页面，数据放入到请求域
        model.addAttribute("items",items);
        return "editItem.jsp";
    }


    //更新商品信息
    @RequestMapping("/update.action")
    public String update(Items items,MultipartFile file) throws IOException {
        if(file!=null){
            String oldName = file.getOriginalFilename();
            if(oldName!=null && oldName.length()>=0){
                String newName = UUID.randomUUID() + oldName.substring(oldName.lastIndexOf("."));
                file.transferTo(new File("E:\\ssm\\day3\\temp\\"+newName));
                items.setPic("/pic/"+newName);
            }
        }

        String itemsPic = items.getPic();
        if(itemsPic==null){
            //未上传，获得原来图片
            String pic = service.findOne(items.getId()).getPic();
            items.setPic(pic);
        }
        service.update(items);
        return "redirect:findAll.action";
    }

    //批量删除商品
    @RequestMapping("/delete.action")
    public String delete(@RequestParam("id") Integer[] ids){
        service.delete(ids);
        return "redirect:findAll.action";
    }
}
