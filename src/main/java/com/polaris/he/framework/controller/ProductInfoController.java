package com.polaris.he.framework.controller;

import com.polaris.he.framework.annotation.PathVariableEncryption;
import com.polaris.he.framework.entity.constanst.CosmeticsEnum;
import com.polaris.he.framework.entity.sku.BaseSkuInfo;
import com.polaris.he.framework.entity.sku.Brand;
import com.polaris.he.framework.entity.sku.Category;
import com.polaris.he.framework.service.product.ProductService;
import com.polaris.he.framework.utils.BaseSkuInfoUtils;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.Arrays;
import java.util.List;

/**
 * User: hexie
 * Date: 2018-12-16 20:41
 * Description:
 */
@Slf4j
@RestController
@RequestMapping("/api/{type}/product")
public class ProductInfoController {

    @Resource
    private ProductService productService;

    /**
     * 获取所有品牌
     *
     * @return
     */
    @GetMapping("/brands")
    public List<Brand> getBrands(@PathVariable CosmeticsEnum type) {
        return productService.getBrands(type.getCode());
    }

    /**
     * 获取品牌下的分类，多个品牌用,分隔
     *
     * @param brandCode
     * @return
     */
    @GetMapping("/categories/{brandCode}")
    public List<Category> getCategories(@PathVariable CosmeticsEnum type, @PathVariable String brandCode) {
        return productService.getCategories(type.getCode(), Arrays.asList(StringUtils.split(brandCode, ",")));
    }

    /**
     * 获取sku
     * @param brandCode
     * @param skuCode
     * @return
     */
    @GetMapping("/sku/{brandCode}/{skuCode}")
    public Object getBySkuCode(@PathVariable CosmeticsEnum type, @PathVariable String brandCode, @PathVariableEncryption String skuCode) {
        BaseSkuInfo sku = BaseSkuInfoUtils.create(brandCode, type.getCode(), skuCode);
        return productService.getBySkuCode(sku);
    }
}