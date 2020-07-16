package com.igeek.ssm.service;

import com.igeek.ssm.dao.ItemsMapper;
import com.igeek.ssm.pojo.Items;
import com.igeek.ssm.pojo.ItemsExample;
import com.igeek.ssm.vo.PageVO;
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
    public PageVO findAll(String query, Integer pageNow){
        //初始化操作
        //未进行条件搜索查询列表，则默认查询所有
        if(query == null){
            query = "";
        }
        //未进行分页搜索查询列表，则默认查询第一页
        if(pageNow == null){
            pageNow = 1;
        }

        //传入条件
        ItemsExample example = new ItemsExample();
        //封装条件对象
        ItemsExample.Criteria criteria = example.createCriteria();
        //封装模糊查询条件
        criteria.andNameLike("%"+query+"%");
        //计算起始值
        Integer begin = (pageNow-1)*3;
        example.setBegin(begin);
        //执行：条件+分页 查询数据
        List<Items> itemsList = mapper.selectByExampleWithBLOBs(example);

        //查询总记录数
        Integer myCounts = mapper.countByExample(example);
        //计算总页数
        Integer myPages = (Integer)(myCounts%3==0 ? myCounts/3 : myCounts/3+1);

        //封装PageVO
        PageVO vo = new PageVO(pageNow,myPages,query,begin,itemsList);
        return vo;
    }


    //添加商品
    public void add(Items items){
        mapper.insertSelective(items);
    }


    //根据id查询商品
    public Items findOne(Integer id){
        Items items = mapper.selectByPrimaryKey(id);
        return items;
    }

    //更新商品
    public void update(Items items){
        //根据主键进行非空更新
        mapper.updateByPrimaryKeySelective(items);
    }

    //删除商品
    public void delete(Integer[] ids){
        for (Integer id : ids) {
            mapper.deleteByPrimaryKey(id);
        }
    }
}
