package com.polaris.he.lipstick.service.impl;

import com.polaris.he.lipstick.dao.BrandCategoryMappingDao;
import com.polaris.he.lipstick.dao.BrandDao;
import com.polaris.he.lipstick.dao.object.BrandCategoryMappingDO;
import com.polaris.he.lipstick.dao.object.BrandDO;
import com.polaris.he.lipstick.entity.Brand;
import com.polaris.he.lipstick.entity.Category;
import com.polaris.he.lipstick.service.LipstickProductService;
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
    private BrandDao brandDao;

    @Resource
    private BrandCategoryMappingDao brandCategoryMappingDao;

    @Override
    public List<Brand> getBrands() {
        List<BrandDO> brands = brandDao.getAll();
        if (CollectionUtils.isNotEmpty(brands)) {
            return brands.stream().map(l -> {
                Brand brand = new Brand();
                BeanUtils.copyProperties(l, brand);
                return brand;
            }).collect(Collectors.toList());
        }
        return null;
    }

    @Override
    public Brand getBrand(String code) {
        throw new UnsupportedOperationException();
    }

    @Override
    public Category getCategory(String code) {
        return null;
    }

    @Override
    public List<Category> getCategories(List<String> brandCodes) {
        Assert.notEmpty(brandCodes, "参数不能为空");
        List<BrandCategoryMappingDO> mappings = brandCategoryMappingDao.getCategoryByBrands(brandCodes);
        if (CollectionUtils.isEmpty(mappings)) {
            return new LinkedList<>();
        }
        Map<String, Category> distinct = mappings.stream().
                map(BrandCategoryMappingDO::getCategory).
                map(l -> {
                    Category data = new Category();
                    BeanUtils.copyProperties(l, data);
                    return data;
                }).collect(Collectors.toMap(Category::getCode, Function.identity(), (u, v) -> v, LinkedHashMap::new));
        return new ArrayList<>(distinct.values());
    }
}