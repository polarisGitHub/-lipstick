package com.polaris.he.lipstick.importer.impl;

import com.fasterxml.jackson.databind.node.ArrayNode;
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
import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.lang3.StringUtils;
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
    public List<LipstickUploadData> byteConvert(byte[] data, String extension) {
        return converter.convert(data, extension, LipstickUploadData.class);
    }

    @Override
    public UploadValidateResult validate(List<LipstickUploadData> data) {
        Set<String> brandSet = brandService.getBrands(CosmeticsEnum.LIPSTICK.getCode()).stream().map(Brand::getName).collect(Collectors.toSet());
        Set<String> categorySet = categoryService.getCategories(CosmeticsEnum.LIPSTICK.getCode()).stream().map(Category::getName).collect(Collectors.toSet());

        UploadValidateResult ret = new UploadValidateResult(new LinkedList<>());
        int index = 0;
        for (LipstickUploadData item : data) {
            List<String> errors = new LinkedList<>();
            // brand
            if (!brandSet.contains(item.getBrandName())) {
                errors.add(String.format("【%s】不存在", item.getBrandName()));
            }
            // category
            if (!categorySet.contains(item.getCatalogName())) {
                errors.add(String.format("【%s】不存在", item.getCatalogName()));
            }
            if (!errors.isEmpty()) {
                ret.getResult().add(new UploadValidateErrorLine(index, String.join("，", errors)));
            }
            index++;
        }
        return ret;
    }

    @Override
    public void resolved(List<LipstickUploadData> data) {
        if (CollectionUtils.isEmpty(data)) {
            return;
        }

        Map<String, Brand> brandMap = brandService.getBrands(CosmeticsEnum.LIPSTICK.getCode()).stream().collect(Collectors.toMap(Brand::getName, Function.identity()));
        Map<String, Category> categoryMap = categoryService.getCategories(CosmeticsEnum.LIPSTICK.getCode()).stream().collect(Collectors.toMap(Category::getName, Function.identity()));
        Set<String> skuSet = new HashSet<>();
        Map<String, Goods> goodsMap = new HashMap<>();
        List<Sku> skuList = new LinkedList<>();
        Multimap<String, Category> goodsCategoryMapping = ArrayListMultimap.create();
        data.forEach(l -> {
            // goods
            if (!goodsMap.containsKey(l.getGoodsCode())) {
                Goods goods = new Goods();
                goods.setBrandCode(brandMap.get(l.getBrandName()).getCode());
                goods.setGoodsCode(l.getGoodsCode());
                goods.setGoodsName(l.getGoodsName());
                goods.setUrl(l.getGoodsUrl());
                goods.setIllustration("");
                goodsMap.put(l.getGoodsCode(), goods);
                goodsCategoryMapping.put(goods.getGoodsCode(), categoryMap.get(l.getCatalogName()));
            }
            // sku
            if (!skuSet.contains(l.getSkuCode())) {
                skuSet.add(l.getSkuCode());
                Sku sku = new Sku();
                sku.setBrandCode(brandMap.get(l.getBrandName()).getCode());
                sku.setGoodsCode(l.getGoodsCode());
                sku.setSkuCode(l.getSkuCode());
                sku.setSkuName(l.getSkuName());
                sku.setSkuByName("");
                sku.setUrl(l.getSkuUrl());
                ObjectNode objectNode = JsonUtils.getObjectMapper().createObjectNode();
                objectNode.put("colorNo", l.getColorNo());
                objectNode.put("color", l.getColor());
                String[] imgs = StringUtils.split(l.getSkuImgDownloadFile(), ",");
                if (ArrayUtils.isNotEmpty(imgs)) {
                    objectNode.put("figure", imgs[0]);
                    ArrayNode images = JsonUtils.getObjectMapper().createArrayNode();
                    Arrays.stream(imgs).forEach(images::add);
                    objectNode.put("images", images);
                }
                sku.setExtension(objectNode);
                skuList.add(sku);
            }
        });

        Collection<GoodsCategoryMapping> mappings = new LinkedList<>();
        Collection<Goods> goodsList = goodsMap.values();
        goodsList.forEach(l -> {
            GoodsCategoryMapping mapping = new GoodsCategoryMapping();
            mapping.setGoods(l);
            mapping.setCategories(goodsCategoryMapping.get(l.getGoodsCode()));
            mappings.add(mapping);
        });
        goodsService.saveGoodsCategoriesMapping(CosmeticsEnum.LIPSTICK.getCode(), mappings);
        goodsService.save(CosmeticsEnum.LIPSTICK.getCode(), goodsList);
        skuService.save(CosmeticsEnum.LIPSTICK.getCode(), skuList);
    }
}