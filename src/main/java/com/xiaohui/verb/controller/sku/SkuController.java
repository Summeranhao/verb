package com.xiaohui.verb.controller.sku;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.xiaohui.verb.domain.Sku;
import com.xiaohui.verb.service.sku.SkuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@Controller
@RequestMapping("/sku")
public class SkuController {

    @Autowired
    private SkuService skuService;

    // 多个查询(分页）
    @GetMapping("/selectAllPageQuery")
    public IPage<Sku> selectAllPageQuery(@RequestParam("skuName") String skuName, @RequestParam("pageNum") int pageNum,
                                         @RequestParam("pageSize") int pageSize) {
        return skuService.selectAllPageQuery(skuName,pageNum,pageSize);

    }


    @GetMapping ("/queryAll")
    @ResponseBody
    public String getSkuList(){
        List<Sku> skus =skuService.querySku(null);
        return skus.toString();
    }

    @GetMapping("/query")
    @ResponseBody
    public String getSku(String skuName){

        Sku sku=new Sku();
        sku.setSkuName(skuName);
        List<Sku> skus =skuService.querySku(sku);
        return skus.toString();
    }

    @PostMapping("/del")
    @ResponseBody
    public String deleteSku(Integer id){
        skuService.deleteSku( id);
        return "删除成功！";
    }


    @PostMapping("/edit")
    @ResponseBody
    public String editSku(Sku sku){
        skuService.editSku( sku);
        return "编辑成功！";
    }


    @PostMapping("/add")
    @ResponseBody
    public String addSku(Sku sku){
        skuService.addSku( sku);
        return "新增成功！";
    }
}
