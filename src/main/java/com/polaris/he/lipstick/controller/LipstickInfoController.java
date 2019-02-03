package com.polaris.he.lipstick.controller;

import com.polaris.he.framework.annotation.PathVariableEncryption;
import com.polaris.he.framework.entity.constanst.CosmeticsEnum;
import com.polaris.he.framework.entity.sku.BaseSkuInfo;
import com.polaris.he.framework.entity.sku.Brand;
import com.polaris.he.framework.entity.sku.Category;
import com.polaris.he.framework.service.sku.LipstickProductService;
import com.polaris.he.framework.utils.BaseSkuInfoUtils;
import com.polaris.he.lipstick.entity.LipstickListItem;
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
@RequestMapping("/api/lipstick/product")
public class LipstickInfoController {

    @Resource
    private LipstickProductService lipstickProductService;

    /**
     * 获取所有口红品牌
     *
     * @return
     */
    @GetMapping("/brands")
    public List<Brand> getBrands() {
        return lipstickProductService.getBrands();
    }

    /**
     * 获取口红品牌下的分类，多个品牌用,分隔
     *
     * @param brandCode
     * @return
     */
    @GetMapping("/categories/{brandCode}")
    public List<Category> getCategories(@PathVariable String brandCode) {
        return lipstickProductService.getCategories(Arrays.asList(StringUtils.split(brandCode, ",")));
    }

    /**
     * @param brandCode
     * @param skuCode
     * @return
     */
    @GetMapping("/sku/{brandCode}/{skuCode}")
    public LipstickListItem getBySkuCode(@PathVariable String brandCode, @PathVariableEncryption String skuCode) {
        BaseSkuInfo sku = BaseSkuInfoUtils.create(brandCode, CosmeticsEnum.LIPSTICK.getCode(), skuCode);
        return lipstickProductService.getBySkuCode(sku);
    }
}