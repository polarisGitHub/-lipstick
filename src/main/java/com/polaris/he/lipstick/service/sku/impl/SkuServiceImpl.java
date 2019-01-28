package com.polaris.he.lipstick.service.sku.impl;

import com.polaris.he.lipstick.dao.SkuDao;
import com.polaris.he.lipstick.dao.object.SkuDO;
import com.polaris.he.lipstick.entity.sku.*;
import com.polaris.he.lipstick.service.sku.BrandService;
import com.polaris.he.lipstick.service.sku.CategoryService;
import com.polaris.he.lipstick.service.sku.GoodsService;
import com.polaris.he.lipstick.service.sku.SkuService;
import com.polaris.he.lipstick.utils.BeanCopyUtils;
import com.polaris.he.lipstick.utils.DiffUtils;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.reflect.FieldUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.lang.reflect.Field;
import java.util.Collection;
import java.util.Collections;
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
    public int save(Collection<Sku> collection) {
        if (CollectionUtils.isEmpty(collection)) {
            return 0;
        }
        List<String> types = collection.stream().map(BaseSkuInfo::getType).distinct().collect(Collectors.toList());
        if (CollectionUtils.isEmpty(types) || types.size() > 1) {
            throw new IllegalArgumentException();
        }
        String type = types.get(0);
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
    public Sku getByCode(BaseSkuInfo baseSkuInfo) {
        SkuDO sku = skuDao.getByCode(baseSkuInfo.getType(), baseSkuInfo.getSkuCode());
        if (sku == null) {
            return null;
        }
        return BeanCopyUtils.copyObject(sku, new Sku());
    }

    @Override
    public SkuAggregation getAggregationByCode(BaseSkuInfo baseSkuInfo) {
        Sku sku = getByCode(baseSkuInfo);
        if (sku == null) {
            return null;
        }
        SkuAggregation ret = new SkuAggregation();
        ret.setSku(sku);
        ret.setBrand(getBrand(sku));
        // TODO
//        ret.setCategories(getCategories(type, sku));
        ret.setGoods(getGoods(sku));
        return ret;
    }

    private Goods getGoods(Sku sku) {
        return goodsService.getByCode(sku.getType(), sku.getGoodsCode());
    }

    private List<Category> getCategories(Sku sku) {
        return categoryService.getCategoriesByGoods(sku.getType(), sku.getGoodsCode());
    }

    private Brand getBrand(Sku sku) {
        return brandService.getBrand(sku.getType(), sku.getBrandCode());
    }
}