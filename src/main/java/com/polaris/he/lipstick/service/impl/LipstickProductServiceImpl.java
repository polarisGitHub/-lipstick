package com.polaris.he.lipstick.service.impl;

import com.polaris.he.lipstick.dao.BrandCategoryMappingDao;
import com.polaris.he.lipstick.dao.CategoryDao;
import com.polaris.he.lipstick.dao.object.BrandCategoryMappingDO;
import com.polaris.he.lipstick.dao.object.CategoryDO;
import com.polaris.he.lipstick.entity.Brand;
import com.polaris.he.lipstick.entity.Category;
import com.polaris.he.lipstick.entity.LipstickItem;
import com.polaris.he.lipstick.entity.constanst.CosmeticsEnum;
import com.polaris.he.lipstick.service.*;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import javax.annotation.Resource;
import java.util.*;
import java.util.function.Function;
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
    private GoodsService goodsService;

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
        return categoryService.getCategory(CosmeticsEnum.LIPSTICK.getCode(), code);
    }

    @Override
    public List<Category> getCategories(List<String> brandCodes) {
        return categoryService.getCategories(CosmeticsEnum.LIPSTICK.getCode(), brandCodes);
    }

    @Override
    public LipstickItem getBySkuCode(String skuCode) {
        skuService.getByCode(CosmeticsEnum.LIPSTICK.getCode(), skuCode);
        return null;
    }
}