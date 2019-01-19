package com.polaris.he.lipstick.service.impl;

import com.polaris.he.lipstick.dao.LipstickSearchDao;
import com.polaris.he.lipstick.dao.object.LipstickAggregationDO;
import com.polaris.he.lipstick.dao.object.LipstickSearchDO;
import com.polaris.he.lipstick.entity.LipstickListItem;
import com.polaris.he.lipstick.entity.constanst.CosmeticsEnum;
import com.polaris.he.lipstick.service.BrandService;
import com.polaris.he.lipstick.service.CategoryService;
import com.polaris.he.lipstick.service.LipstickSearchService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections4.CollectionUtils;
import org.checkerframework.checker.nullness.Opt;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

/**
 * TODO 搜索最好走搜索引擎，现在没资源，直接在db中做
 * <p>
 * User: hexie
 * Date: 2019-01-05 21:58
 * Description:
 */
@Slf4j
@Service
public class LipstickSearchServiceImpl implements LipstickSearchService {

    @Resource
    private LipstickSearchDao lipstickSearchDao;

    @Resource
    private BrandService brandService;

    @Resource
    private CategoryService categoryService;

    @Override
    public List<LipstickListItem> search(List<String> brandCodes, List<String> categories, String colorNo) {
        log.info("[产品搜索] 查询，brand={},category={},colorNo={}", brandCodes, categories, colorNo);
        LipstickSearchDO query = LipstickSearchDO.builder().
                brandCodes(brandCodes).
                categoryCodes(categories).
                colorNo(colorNo).
                build();
        List<LipstickAggregationDO> list = lipstickSearchDao.search(query);
        log.info("[产品搜索] 找到产品，list={}", list);
        List<LipstickListItem> result = new LinkedList<>();
        if (CollectionUtils.isNotEmpty(list)) {
            list.forEach(l -> result.add(convert(l)));
        }
        log.info("[产品搜索] 返回结果，result={}", result);
        return result;
    }

    private LipstickListItem convert(LipstickAggregationDO l) {// TODO sku
        LipstickListItem item = new LipstickListItem();
        item.setBrandCode(l.getBrandCode());
        item.setBrandName(Optional.ofNullable(brandService.getBrand(CosmeticsEnum.LIPSTICK.getCode(), l.getBrandCode()).getName()).orElse(""));
        // FIXME multi category
//        item.setCategoryCode(l.getCategoryCode());
//        item.setCategoryName(Optional.ofNullable(categoryService.getCategoriesByGoods(CosmeticsEnum.LIPSTICK.getCode(), l.getGoods().getGoodsCode())).orElse(""));
        item.setGoodsCode(l.getGoods().getGoodsCode());
        item.setGoodsName(l.getGoods().getGoodsName());
        item.setSkuCode(l.getSku().getSkuCode());
        item.setSkuName(l.getSku().getSkuName());
        item.setColorNo(l.getSku().getExtension().get("colorNo").asText(""));
        item.setColor(l.getSku().getExtension().get("color").asText(""));
        item.setFigure(Optional.ofNullable(l.getSku().getExtension().get("figure")).map(ll -> ll.asText("")).orElse(""));
        return item;
    }
}