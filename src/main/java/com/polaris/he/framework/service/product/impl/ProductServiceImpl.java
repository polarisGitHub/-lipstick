package com.polaris.he.framework.service.product.impl;

import com.polaris.he.framework.entity.sku.BaseSkuInfo;
import com.polaris.he.framework.entity.sku.Brand;
import com.polaris.he.framework.entity.sku.Category;
import com.polaris.he.framework.entity.sku.SkuAggregation;
import com.polaris.he.framework.repository.FrameworkBizConverterRepository;
import com.polaris.he.framework.service.product.ProductService;
import com.polaris.he.framework.service.sku.BrandService;
import com.polaris.he.framework.service.sku.CategoryService;
import com.polaris.he.framework.service.sku.SkuService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import javax.annotation.Resource;
import java.util.List;

/**
 * User: hexie
 * Date: 2018-12-16 20:49
 * Description:
 */
@Slf4j
@Service
@Repository
public class ProductServiceImpl implements ProductService {

    @Resource
    private BrandService brandService;

    @Resource
    private CategoryService categoryService;

    @Resource
    private SkuService skuService;

    @Resource
    private FrameworkBizConverterRepository frameworkBizConverterRepository;

    @Override
    public List<Brand> getBrands(String type) {
        return brandService.getBrands(type);
    }

    @Override
    public Brand getBrand(String type, String code) {
        return brandService.getBrand(type, code);
    }

    @Override
    public Category getCategory(String type, String code) {
        throw new UnsupportedOperationException();
    }

    @Override
    public List<Category> getCategories(String type, List<String> brandCodes) {
        return categoryService.getCategoriesByBrands(type, brandCodes);
    }

    @Override
    public Object getBySkuCode(BaseSkuInfo baseSkuInfo) {
        Assert.notNull(baseSkuInfo, "skuInfo不能为空");
        SkuAggregation skuInfo = skuService.getAggregationBySkuInfo(baseSkuInfo);
        if (skuInfo == null) {
            return null;
        }
        Converter<SkuAggregation, ?> converter = frameworkBizConverterRepository.getConverter(baseSkuInfo.getType(), SkuAggregation.class, "product");
        return converter.convert(skuInfo);
    }
}