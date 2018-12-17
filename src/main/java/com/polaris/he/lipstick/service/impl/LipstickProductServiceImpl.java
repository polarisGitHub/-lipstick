package com.polaris.he.lipstick.service.impl;

import com.polaris.he.lipstick.dao.BrandDao;
import com.polaris.he.lipstick.dao.object.BrandDO;
import com.polaris.he.lipstick.entity.Brand;
import com.polaris.he.lipstick.entity.Category;
import com.polaris.he.lipstick.entity.ColorCard;
import com.polaris.he.lipstick.service.LipstickProductService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Arrays;
import java.util.List;
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
    public Brand getBrand(String id) {
        return null;
    }

    @Override
    public List<Category> getCategories(String brandId) {
        return null;
    }

    @Override
    public List<ColorCard> getColorCards(String categoryId) {
        return null;
    }

    @Override
    public ColorCard getColorCard(String colorCardId) {
        return null;
    }
}