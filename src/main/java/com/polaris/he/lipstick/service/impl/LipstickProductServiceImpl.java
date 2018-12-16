package com.polaris.he.lipstick.service.impl;

import com.polaris.he.lipstick.entity.Brand;
import com.polaris.he.lipstick.entity.Category;
import com.polaris.he.lipstick.entity.ColorCard;
import com.polaris.he.lipstick.service.LipstickProductService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;

/**
 * User: hexie
 * Date: 2018-12-16 20:49
 * Description:
 */
@Slf4j
@Service
public class LipstickProductServiceImpl implements LipstickProductService {
    @Override
    public List<Brand> getBrands() {
        Brand b0 = new Brand();
        b0.setId("1");
        b0.setName("2");
        Brand b1 = new Brand();
        b1.setId("3");
        b1.setName("4");
        return Arrays.asList(b0, b1);
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