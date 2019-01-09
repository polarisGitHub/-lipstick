package com.polaris.he.lipstick.controller;

import com.polaris.he.lipstick.entity.LipstickItem;
import com.polaris.he.lipstick.service.LipstickSearchService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.Arrays;
import java.util.List;

/**
 * User: hexie
 * Date: 2019-01-05 21:32
 * Description:
 */
@Slf4j
@RestController
@RequestMapping("/api/lipstick/search")
public class LipstickSearchController {

    @Resource
    private LipstickSearchService lipstickSearchService;

    @GetMapping("")
    public List<LipstickItem> search(String brands, String categories, String colorNo) {
        log.info("查找口红，brands={},categories={},colorNo={}", brands, categories, colorNo);
        Assert.hasText(brands, "品牌不能为空");
        Assert.hasText(categories, "类别不能为空");
        Assert.hasText(colorNo, "色卡不能为空");
        List<LipstickItem> ret = lipstickSearchService.search(Arrays.asList(StringUtils.split(brands, ",")), Arrays.asList(StringUtils.split(categories, ",")), colorNo);
        log.info("找到口红：{}", ret);
        return ret;
    }
}