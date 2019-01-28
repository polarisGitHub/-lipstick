package com.polaris.he.lipstick.service.sku.impl;

import com.google.common.collect.HashMultimap;
import com.google.common.collect.Multimap;
import com.polaris.he.lipstick.dao.GoodsCategoryMappingDao;
import com.polaris.he.lipstick.dao.GoodsDao;
import com.polaris.he.lipstick.dao.object.GoodsCategoryMappingDO;
import com.polaris.he.lipstick.dao.object.GoodsDO;
import com.polaris.he.lipstick.entity.sku.BaseGoodsInfo;
import com.polaris.he.lipstick.entity.sku.BaseSkuInfo;
import com.polaris.he.lipstick.entity.sku.Goods;
import com.polaris.he.lipstick.entity.sku.GoodsCategoryMapping;
import com.polaris.he.lipstick.service.sku.GoodsService;
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
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * User: hexie
 * Date: 2019-01-10 22:54
 * Description:
 */
@Slf4j
@Service
public class GoodsServiceImpl implements GoodsService {

    @Resource
    private GoodsDao goodsDao;

    @Resource
    private GoodsCategoryMappingDao goodsCategoryMappingDao;

    private static final Field GOODS_DO_CODE_FIELD = FieldUtils.getField(GoodsDO.class, "goodsCode", true);

    @Override
    @Transactional
    public int save(Collection<Goods> collection) {
        if (CollectionUtils.isEmpty(collection)) {
            return 0;
        }
        String type = getTypeInCollection(collection);

        Set<String> goodsCodeSet = collection.stream().map(Goods::getGoodsCode).collect(Collectors.toSet());
        List<GoodsDO> goodsInDb = goodsDao.getByCodeList(type, goodsCodeSet);
        List<GoodsDO> goodsToSave = collection.stream().map(l -> {
            GoodsDO data = BeanCopyUtils.copyObject(l, new GoodsDO());
            data.setType(type);
            return data;
        }).collect(Collectors.toList());

        DiffUtils.DiffResult<GoodsDO> goodsDiff = DiffUtils.diff(goodsInDb, goodsToSave, GOODS_DO_CODE_FIELD, GoodsDO::equals);

        Collection<GoodsDO> insert = goodsDiff.getAdd();
        if (CollectionUtils.isNotEmpty(insert)) {
            log.info("goods insert,code={}", insert.stream().map(GoodsDO::getGoodsCode).collect(Collectors.toList()));
            goodsDao.insert(insert);
        }

        Collection<GoodsDO> update = goodsDiff.getNotEqual();
        if (CollectionUtils.isNotEmpty(update)) {
            log.info("goods update,code={}", update.stream().map(GoodsDO::getGoodsCode).collect(Collectors.toList()));
            update.forEach(l -> goodsDao.update(l));
        }
        return 1;
    }

    @Override
    @Transactional
    public int saveGoodsCategoriesMapping(Collection<GoodsCategoryMapping> collection) {
        if (CollectionUtils.isEmpty(collection)) {
            return 0;
        }
        String type = getTypeInCollection(collection.stream().map(GoodsCategoryMapping::getGoods).collect(Collectors.toList()));
        Multimap<String, String> map = HashMultimap.create();
        collection.forEach(l -> l.getCategories().forEach(ll -> map.put(l.getGoods().getGoodsCode(), ll.getCode())));
        goodsCategoryMappingDao.deleteByGoodsCode(type, map.keySet());

        List<GoodsCategoryMappingDO> inserts = map.entries().stream().map(l -> {
            GoodsCategoryMappingDO data = new GoodsCategoryMappingDO();
            data.setType(type);
            data.setGoodsCode(l.getKey());
            data.setCategoryCode(l.getValue());
            return data;
        }).collect(Collectors.toList());
        return goodsCategoryMappingDao.insert(inserts);
    }

    @Override
    public Goods getByCode(String type, String code) {
        GoodsDO goodsDO = goodsDao.getByCode(type, code);
        return BeanCopyUtils.copyObject(goodsDO, new Goods());
    }

    private String getTypeInCollection(Collection<Goods> collection) {
        List<String> types = collection.stream().map(BaseGoodsInfo::getType).distinct().collect(Collectors.toList());
        if (CollectionUtils.isEmpty(types) || types.size() > 1) {
            throw new IllegalArgumentException();
        }
        return types.get(0);
    }
}