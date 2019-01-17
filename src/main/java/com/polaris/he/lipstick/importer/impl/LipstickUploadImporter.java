package com.polaris.he.lipstick.importer.impl;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.google.common.collect.ArrayListMultimap;
import com.google.common.collect.Multimap;
import com.polaris.he.lipstick.common.utils.JsonUtils;
import com.polaris.he.lipstick.entity.*;
import com.polaris.he.lipstick.entity.constanst.CosmeticsEnum;
import com.polaris.he.lipstick.importer.AbstractUploadImporter;
import com.polaris.he.lipstick.importer.converter.UploadByteConverter;
import com.polaris.he.lipstick.importer.data.UploadValidateErrorLine;
import com.polaris.he.lipstick.importer.data.UploadValidateResult;
import com.polaris.he.lipstick.service.BrandService;
import com.polaris.he.lipstick.service.CategoryService;
import com.polaris.he.lipstick.service.GoodsService;
import com.polaris.he.lipstick.service.SkuService;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * User: hexie
 * Date: 2019-01-16 23:30
 * Description:
 */
@Component
public class LipstickUploadImporter extends AbstractUploadImporter<LipstickUploadData> {

    @Resource(type = LipstickUploadConverter.class)
    private UploadByteConverter converter;

    @Resource
    private BrandService brandService;

    @Resource
    private CategoryService categoryService;

    @Resource
    private GoodsService goodsService;

    @Resource
    private SkuService skuService;


    @Override
    protected List<LipstickUploadData> byteConvert(byte[] data, String extension) {
        return converter.convert(data, extension, LipstickUploadData.class);
    }

    @Override
    protected UploadValidateResult validate(List<LipstickUploadData> data) {
        Set<String> brandSet = brandService.getBrands(CosmeticsEnum.LIPSTICK.getCode()).stream().map(Brand::getName).collect(Collectors.toSet());
        Set<String> categorySet = categoryService.getCategories(CosmeticsEnum.LIPSTICK.getCode()).stream().map(Category::getName).collect(Collectors.toSet());

        UploadValidateResult ret = new UploadValidateResult(new LinkedList<>());
        Set<String> set = new HashSet<>();
        int index = 0;
        for (LipstickUploadData item : data) {
            List<String> errors = new LinkedList<>();
            // brand
            if (brandSet.contains(item.getBrandName())) {
                errors.add(String.format("【%s】不存在", item.getBrandName()));
            }
            // category
            if (categorySet.contains(item.getCatalogName())) {
                errors.add(String.format("【%s】不存在", item.getCatalogName()));
            }
            // sku
            if (set.contains(item.getSkuCode())) {
                errors.add(String.format("【%s】重复", item.getSkuCode()));
            }
            set.add(item.getSkuCode());

            if (!errors.isEmpty()) {
                ret.getResult().add(new UploadValidateErrorLine(index, String.join("，", errors)));
            }
            index++;
        }
        return ret;
    }

    @Override
    protected void resolved(List<LipstickUploadData> data) {
        if (CollectionUtils.isEmpty(data)) {
            return;
        }

        Map<String, String> brandMap = brandService.getBrands(CosmeticsEnum.LIPSTICK.getCode()).stream().collect(Collectors.toMap(Brand::getName, Brand::getCode));

        Set<String> goodsSet = new HashSet<>();
        List<Goods> goodsList = new LinkedList<>();
        List<Sku> skuList = new LinkedList<>();
        Multimap<String, String> category = ArrayListMultimap.create();
        data.forEach(l -> {
            // category
            category.put(l.getGoodsCode(), l.getCatalogName());
            // goods
            if (!goodsSet.contains(l.getGoodsCode())) {
                Goods goods = new Goods();
                goods.setBrandCode(brandMap.get(l.getBrandName()));
                goods.setGoodsCode(l.getGoodsCode());
                goods.setGoodsName(l.getGoodsName());
                goods.setUrl(l.getGoodsUrl());
                goods.setIllustration("");
                goodsList.add(goods);
                goodsSet.add(l.getGoodsCode());
                goodsList.add(goods);
            }
            // sku
            Sku sku = new Sku();
            sku.setBrandCode(brandMap.get(l.getBrandName()));
            sku.setGoodsCode(l.getGoodsCode());
            sku.setSkuName(l.getSkuName());
            sku.setSkuByName("");
            sku.setUrl(l.getSkuUrl());
            ObjectNode objectNode = JsonUtils.getObjectMapper().createObjectNode();
            objectNode.put("colorNo", l.getColorNo());
            objectNode.put("color", l.getColor());
            sku.setExtension(objectNode);
            skuList.add(sku);
        });
    }
}