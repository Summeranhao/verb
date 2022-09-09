package com.xiaohui.verb.controller.sku;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.xiaohui.verb.controller.common.BaseResponse;
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
    public BaseResponse selectAllPageQuery(@RequestParam("skuName") String skuName, @RequestParam("pageNum") int pageNum,
                                         @RequestParam("pageSize") int pageSize) {
        return BaseResponse.ok(skuService.selectAllPageQuery(skuName,pageNum,pageSize));

    }


    @GetMapping ("/queryAll")
    @ResponseBody
    public BaseResponse getSkuList(){
        List<Sku> skus =skuService.querySku(null);
        return BaseResponse.ok(skus);
    }

    @GetMapping("/query")
    @ResponseBody
    public BaseResponse getSku(String skuName){

        Sku sku=new Sku();
        sku.setSkuName(skuName);
        List<Sku> skus =skuService.querySku(sku);
        return BaseResponse.ok(skus);
    }

    @PostMapping("/del")
    @ResponseBody
    public BaseResponse deleteSku(Integer id){
        skuService.deleteSku( id);
        return BaseResponse.ok(null);
    }


    @PostMapping("/edit")
    @ResponseBody
    public BaseResponse editSku(Sku sku){
        skuService.editSku( sku);
        return BaseResponse.ok(null);
    }


    @PostMapping("/add")
    @ResponseBody
    public BaseResponse addSku(Sku sku){
        skuService.addSku( sku);
        return BaseResponse.ok(null);
    }
}
