package com.polaris.he.lipstick.service.impl;

import com.polaris.he.lipstick.entity.Brand;
import com.polaris.he.lipstick.entity.Category;
import com.polaris.he.lipstick.entity.LipstickItem;
import com.polaris.he.lipstick.entity.SkuAggregation;
import com.polaris.he.lipstick.entity.constanst.CosmeticsEnum;
import com.polaris.he.lipstick.service.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import javax.annotation.Resource;
import java.util.*;
import java.util.stream.Collectors;

/**
 * User: hexie
 * Date: 2018-12-16 20:49
 * Description:
 */
@Slf4j
@Service
public class LipstickProductServiceImpl implements LipstickProductService {

    @Resource
    private BrandService brandService;

    @Resource
    private CategoryService categoryService;

    @Resource
    private SkuService skuService;

    @Override
    public List<Brand> getBrands() {
        return brandService.getBrands(CosmeticsEnum.LIPSTICK.getCode());
    }

    @Override
    public Brand getBrand(String code) {
        return brandService.getBrand(CosmeticsEnum.LIPSTICK.getCode(), code);
    }

    @Override
    public Category getCategory(String code) {
        throw new UnsupportedOperationException();
    }

    @Override
    public List<Category> getCategories(List<String> brandCodes) {
        return categoryService.getCategoriesByBrands(CosmeticsEnum.LIPSTICK.getCode(), brandCodes);
    }

    @Override
    public LipstickItem getBySkuCode(String skuCode) {
        Assert.hasText(skuCode, "skuCode不能为空");
        SkuAggregation skuInfo = skuService.getAggregationByCode(CosmeticsEnum.LIPSTICK.getCode(), skuCode);
        if (skuInfo == null) {
            return null;
        }
        LipstickItem ret = new LipstickItem();
        ret.setBrandCode(skuInfo.getBrand().getCode());
        ret.setBrandName(skuInfo.getBrand().getName());
        // TODO
//        ret.setCategoryCode(skuInfo.getCategories().stream().map(Category::getCode).collect(Collectors.joining(",")));
//        ret.setCategoryName(skuInfo.getCategories().stream().map(Category::getName).collect(Collectors.joining(",")));
        ret.setGoodsCode(skuInfo.getGoods().getGoodsCode());
        ret.setGoodsName(skuInfo.getGoods().getGoodsName());
        ret.setSkuCode(skuInfo.getSku().getSkuCode());
        ret.setSkuName(skuInfo.getSku().getSkuName());
        ret.setColorNo(skuInfo.getSku().getExtension().get("colorNo").asText(""));
        ret.setColor(skuInfo.getSku().getExtension().get("color").asText(""));
        return ret;
    }
}