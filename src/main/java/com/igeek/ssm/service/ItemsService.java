package com.igeek.ssm.service;

import com.igeek.ssm.dao.ItemsMapper;
import com.igeek.ssm.pojo.Items;
import com.igeek.ssm.pojo.ItemsExample;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class ItemsService {

    @Autowired
    private ItemsMapper mapper;

    //条件+查询商品列表
    @Transactional(readOnly = true)
    public List<Items> findAll(String query){
        //未进行条件搜索查询列表，则默认查询所有
        if(query == null){
            query = "";
        }
        //封装条件对象
        ItemsExample example = new ItemsExample();
        ItemsExample.Criteria criteria = example.createCriteria();
        //封装条件
        criteria.andNameLike("%"+query+"%");
        //执行
        List<Items> itemsList = mapper.selectByExampleWithBLOBs(example);
        return itemsList;
    }

}
