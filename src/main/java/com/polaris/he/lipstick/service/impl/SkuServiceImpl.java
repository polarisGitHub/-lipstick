package com.polaris.he.lipstick.service.impl;

import com.polaris.he.lipstick.dao.SkuDao;
import com.polaris.he.lipstick.dao.object.SkuDO;
import com.polaris.he.lipstick.entity.*;
import com.polaris.he.lipstick.service.BrandService;
import com.polaris.he.lipstick.service.CategoryService;
import com.polaris.he.lipstick.service.GoodsService;
import com.polaris.he.lipstick.service.SkuService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.Collection;
import java.util.List;

/**
 * User: hexie
 * Date: 2019-01-10 22:36
 * Description:
 */
@Slf4j
@Service
public class SkuServiceImpl implements SkuService {

    @Resource
    private SkuDao skuDao;

    @Resource
    private BrandService brandService;

    @Resource
    private CategoryService categoryService;

    @Resource
    private GoodsService goodsService;

    @Override
    @Transactional
    public int save(Collection<Sku> collection) {
        return 0;
    }

    @Override
    public Sku getByCode(String type, String code) {
        SkuDO sku = skuDao.getByCode(type, code);
        if (sku == null) {
            return null;
        }
        Sku ret = new Sku();
        BeanUtils.copyProperties(sku, ret);
        return ret;
    }

    @Override
    public SkuAggregation getAggregationByCode(String type, String code) {
        Sku sku = getByCode(type, code);
        if (sku == null) {
            return null;
        }
        SkuAggregation ret = new SkuAggregation();
        ret.setSku(sku);
        ret.setBrand(getBrand(type, sku));
        // TODO
//        ret.setCategories(getCategories(type, sku));
        ret.setGoods(getGoods(type, sku));
        return ret;
    }

    private Goods getGoods(String type, Sku sku) {
        return goodsService.getByCode(type, sku.getGoodsCode());
    }

    private List<Category> getCategories(String type, Sku sku) {
        return categoryService.getCategoriesByGoods(type, sku.getGoodsCode());
    }

    private Brand getBrand(String type, Sku sku) {
        return brandService.getBrand(type, sku.getBrandCode());
    }
}