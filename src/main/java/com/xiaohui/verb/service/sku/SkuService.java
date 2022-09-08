package com.xiaohui.verb.service.sku;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.xiaohui.verb.domain.Sku;
import com.xiaohui.verb.mapper.SkuMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SkuService {

    @Autowired
    private SkuMapper skuMapper;

    public List<Sku> querySku(Sku sku){

        QueryWrapper<Sku> queryWrapper = new  QueryWrapper<>();

        List<Sku> skus = skuMapper.selectList(queryWrapper);

        return skus;

    }


    public void deleteSku(Integer id) {
        skuMapper.deleteById(id);
    }

    public void editSku(Sku sku) {
        skuMapper.updateById(sku);
    }

    public void addSku(Sku sku) {
        skuMapper.insert(sku);
    }

    public IPage<Sku> selectAllPageQuery(String name, int pageNum, int pageSize) {

        Page page=new Page();
        page.setPages(pageNum);
        page.setTotal(pageSize);

        QueryWrapper<Sku> queryWrapper = new  QueryWrapper<>();


        // 分页查询
        IPage iPage = skuMapper.selectPage(page, queryWrapper);

        return iPage;

    }
}
