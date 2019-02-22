package com.polaris.he.lipstick.controller;

import com.polaris.he.application.entity.constant.ExceptionCodeEnum;
import com.polaris.he.application.exception.BizException;
import com.polaris.he.framework.entity.page.Pull;
import com.polaris.he.framework.entity.page.PullResult;
import com.polaris.he.framework.utils.BizEncryptionUtils;
import com.polaris.he.lipstick.entity.LipstickListItem;
import com.polaris.he.lipstick.entity.LipstickSearchData;
import com.polaris.he.lipstick.service.LipstickSearchService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;

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
    public PullResult<LipstickListItem> search(LipstickSearchData search) {
        Assert.state(search.getPageSize() == null || search.getPageSize() <= 10, "分页不能大于10");
        log.info("查找口红，search", search);
        Assert.hasText(search.getBrands(), "品牌不能为空");
        if (StringUtils.isEmpty(search.getCategories()) && StringUtils.isEmpty(search.getColorNo())) {
            throw new BizException("【类别，色卡】必须填一个", ExceptionCodeEnum.E00001);
        }
        List<LipstickListItem> ret = lipstickSearchService.search(
                Arrays.asList(StringUtils.split(StringUtils.trimToEmpty(search.getBrands()), ",")),
                Arrays.asList(StringUtils.split(StringUtils.trimToEmpty(search.getCategories()), ",")),
                search.getColorNo(),
                new Pull(
                        Optional.ofNullable(search.getNextId()).map(BizEncryptionUtils::decode).map(Long::valueOf).orElse(0L),
                        search.getPageSize()
                )
        );
        Long nextId = ret.stream().max(Comparator.comparingLong(LipstickListItem::getId)).map(LipstickListItem::getId).orElse(null);
        log.info("找到口红：nextId={},data={}", nextId, ret);
        return new PullResult<>(ret, nextId);
    }

    @GetMapping("/similar")
    public List<LipstickListItem> similar(String brands, String categories, String color, String threshold, Long nextId, int pageSize) {
        log.info("查找颜色相似口红，brands={},categories={},colorNo={},nextId={},pageSize={}", brands, categories, color, nextId, pageSize);
        if (StringUtils.isEmpty(color)) {
            throw new BizException("【颜色必填】必须填一个", ExceptionCodeEnum.E00001);
        }
        List<LipstickListItem> ret = lipstickSearchService.similar(
                Arrays.asList(StringUtils.split(StringUtils.trimToEmpty(brands), ",")),
                Arrays.asList(StringUtils.split(StringUtils.trimToEmpty(categories), ",")),
                color,
                Double.valueOf(threshold));
        log.info("查找颜色相似口红：{}", ret);
        return ret;
    }
}