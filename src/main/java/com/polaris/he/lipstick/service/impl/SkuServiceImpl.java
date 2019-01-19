package com.polaris.he.lipstick.service.impl;

import com.polaris.he.lipstick.dao.SkuDao;
import com.polaris.he.lipstick.dao.object.GoodsDO;
import com.polaris.he.lipstick.dao.object.SkuDO;
import com.polaris.he.lipstick.entity.*;
import com.polaris.he.lipstick.service.BrandService;
import com.polaris.he.lipstick.service.CategoryService;
import com.polaris.he.lipstick.service.GoodsService;
import com.polaris.he.lipstick.service.SkuService;
import com.polaris.he.lipstick.utils.BeanCopyUtils;
import com.polaris.he.lipstick.utils.DiffUtils;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.reflect.FieldUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.lang.reflect.Field;
import java.util.Collection;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * User: hexie
 * Date: 2019-01-10 22:36
 * Description:
 */
@Slf4j
@Service
public class SkuServiceImpl implements SkuService {

    private static final Field SKU_DO_CODE_FIELD = FieldUtils.getField(SkuDO.class, "skuCode", true);

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
    public int save(String type, Collection<Sku> collection) {
        if (CollectionUtils.isEmpty(collection)) {
            return 0;
        }
        Set<String> skuCodeSet = collection.stream().map(Sku::getSkuCode).collect(Collectors.toSet());
        List<SkuDO> skuInDb = skuDao.getByCodeList(type, skuCodeSet);
        List<SkuDO> skuToSave = collection.stream().map(l -> {
            SkuDO data = BeanCopyUtils.copyObject(l, new SkuDO());
            data.setType(type);
            return data;
        }).collect(Collectors.toList());

        DiffUtils.DiffResult<SkuDO> skuDiff = DiffUtils.diff(skuInDb, skuToSave, SKU_DO_CODE_FIELD, SkuDO::equals);

        Collection<SkuDO> insert = skuDiff.getAdd();
        if (CollectionUtils.isNotEmpty(insert)) {
            log.info("sku insert,code={}", insert.stream().map(SkuDO::getSkuCode).collect(Collectors.toList()));
            skuDao.insert(insert);
        }

        Collection<SkuDO> update = skuDiff.getNotEqual();
        if (CollectionUtils.isNotEmpty(update)) {
            log.info("sku update,code={}", update.stream().map(SkuDO::getSkuCode).collect(Collectors.toList()));
            update.forEach(l -> skuDao.update(l));
        }
        return 1;
    }

    @Override
    public Sku getByCode(String type, String code) {
        SkuDO sku = skuDao.getByCode(type, code);
        if (sku == null) {
            return null;
        }
        return BeanCopyUtils.copyObject(sku, new Sku());
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