package com.polaris.he.lipstick.controller;

import com.polaris.he.lipstick.common.constant.ExceptionCodeEnum;
import com.polaris.he.lipstick.common.exception.BizExcepton;
import com.polaris.he.lipstick.entity.Brand;
import com.polaris.he.lipstick.entity.Category;
import com.polaris.he.lipstick.service.LipstickProductService;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.net.BindException;
import java.util.List;

/**
 * User: hexie
 * Date: 2018-12-16 20:41
 * Description:
 */
@Slf4j
@RestController
@RequestMapping("/api/lipstick/product")
public class LipstickInfoController {

    @Resource
    private LipstickProductService lipstickProductService;

    @GetMapping("/bands")
    public List<Brand> getBrands() {
        return lipstickProductService.getBrands();
    }

    @GetMapping("/categories/{brandId}")
    public List<Category> getCategories(@PathVariable String brandId) {
        throw new BizExcepton("test", ExceptionCodeEnum.test, null);
    }
}