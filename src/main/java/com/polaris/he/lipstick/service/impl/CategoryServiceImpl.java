package com.polaris.he.lipstick.service.impl;

import com.polaris.he.lipstick.dao.BrandCategoryMappingDao;
import com.polaris.he.lipstick.dao.CategoryDao;
import com.polaris.he.lipstick.dao.object.BrandCategoryMappingDO;
import com.polaris.he.lipstick.dao.object.CategoryDO;
import com.polaris.he.lipstick.entity.Category;
import com.polaris.he.lipstick.entity.constanst.CosmeticsEnum;
import com.polaris.he.lipstick.service.CategoryService;
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
 * Date: 2019-01-10 21:19
 * Description:
 */
@Service
public class CategoryServiceImpl implements CategoryService {

    @Resource
    private CategoryDao categoryDao;

    @Resource
    private BrandCategoryMappingDao brandCategoryMappingDao;

    @Override
    public Category getCategory(String type, String code) {
        CategoryDO category = categoryDao.getByCode(CosmeticsEnum.LIPSTICK.getCode(), code);
        Category ret = new Category();
        BeanUtils.copyProperties(category, ret);
        return ret;
    }

    @Override
    public List<Category> getCategories(String type, List<String> brandCodes) {
        Assert.notEmpty(brandCodes, "参数不能为空");
        List<BrandCategoryMappingDO> mappings = brandCategoryMappingDao.getCategoryByBrands(type, brandCodes);
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