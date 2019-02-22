package com.polaris.he.lipstick.service.impl;

import com.polaris.he.application.utils.JsonUtils;
import com.polaris.he.framework.entity.constanst.CosmeticsEnum;
import com.polaris.he.framework.entity.page.Pull;
import com.polaris.he.framework.service.sku.BrandService;
import com.polaris.he.framework.service.sku.CategoryService;
import com.polaris.he.lipstick.algorithm.color.data.ColorDistanceAlgorithm;
import com.polaris.he.lipstick.algorithm.color.data.ColorSpace;
import com.polaris.he.lipstick.algorithm.color.distance.ColorDistance;
import com.polaris.he.lipstick.algorithm.color.distance.ColorDistanceComputerFactory;
import com.polaris.he.lipstick.algorithm.color.utils.HexColorUtils;
import com.polaris.he.lipstick.dao.LipstickSearchDao;
import com.polaris.he.lipstick.dao.objects.LipstickAggregationDO;
import com.polaris.he.lipstick.dao.objects.LipstickSearchDO;
import com.polaris.he.lipstick.entity.LipstickExtension;
import com.polaris.he.lipstick.entity.LipstickListItem;
import com.polaris.he.lipstick.service.LipstickSearchService;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.*;
import java.util.stream.Collectors;

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
    public List<LipstickListItem> search(List<String> brandCodes, List<String> categories, String colorNo, Pull pull) {
        log.info("[产品搜索] 查询，brand={},category={},colorNo={},pull={}", brandCodes, categories, colorNo, pull);
        LipstickSearchDO query = LipstickSearchDO.builder().
                brandCodes(brandCodes).
                categoryCodes(categories).
                colorNo(colorNo).
                build();
        query.setNextId(pull.getNextId());
        query.setPageSize(pull.getPageSize());
        List<LipstickAggregationDO> list = lipstickSearchDao.search(query);
        log.info("[产品搜索] 找到产品，list={}", list);
        List<LipstickListItem> result = new LinkedList<>();
        if (CollectionUtils.isNotEmpty(list)) {
            list.forEach(l -> result.add(convert(l)));
        }
        log.info("[产品搜索] 返回结果，result={}", result);
        return result;
    }

    @Override
    public List<LipstickListItem> similar(List<String> brandCodes, List<String> categories, String color, Double threshold) {
        log.info("[产品颜色搜索] 查询，brand={},category={},color={},threshold={}", brandCodes, categories, color, threshold);
        LipstickSearchDO query = LipstickSearchDO.builder().
                brandCodes(brandCodes).
                categoryCodes(categories).
                build();
        List<LipstickAggregationDO> list = lipstickSearchDao.search(query);

        List<LipstickListItem> result = Collections.emptyList();
        List<SimilarColorDistanceWrapper> wrappers = Collections.synchronizedList(new ArrayList<>());
        if (CollectionUtils.isNotEmpty(list)) {
            ColorDistance algorithm = ColorDistanceComputerFactory.getComputer(ColorDistanceAlgorithm.CIE76);
            ColorSpace target = new ColorSpace(HexColorUtils.hex2Rgb(color));
            list.stream().parallel().forEach(l -> {
                LipstickExtension extension = JsonUtils.toJavaObject(l.getSku().getExtension(), LipstickExtension.class);
                if (extension != null) {
                    double distance = algorithm.compute(target, new ColorSpace(HexColorUtils.hex2Rgb(extension.getColor())));
                    log.info("{}和{}【{}】距离为，{}", color, extension.getColor(), l.getSku().getSkuCode(), distance);
                    wrappers.add(new SimilarColorDistanceWrapper(convert(l), distance));
                }
            });
        }

        if (CollectionUtils.isNotEmpty(wrappers)) {
            result = wrappers.stream().
                    filter(l -> l.getDistance() < threshold).
                    sorted(Comparator.comparingDouble(SimilarColorDistanceWrapper::getDistance)).
                    map(SimilarColorDistanceWrapper::getLipstick).
                    collect(Collectors.toList());
        }
        log.info("[产品颜色搜索] 返回结果，result={}", result);
        return result;
    }

    private LipstickListItem convert(LipstickAggregationDO l) {// TODO sku
        LipstickListItem item = new LipstickListItem();
        item.setId(l.getSku().getId());

        item.setBrandCode(l.getBrandCode());
        item.setBrandName(Optional.ofNullable(brandService.getBrand(CosmeticsEnum.LIPSTICK.getCode(), l.getBrandCode()).getName()).orElse(""));
        // FIXME multi category
//        item.setCategoryCode(l.getCategoryCode());
//        item.setCategoryName(Optional.ofNullable(categoryService.getCategoriesByGoods(CosmeticsEnum.LIPSTICK.getCode(), l.getGoods().getGoodsCode())).orElse(""));
        item.setGoodsCode(l.getGoods().getGoodsCode());
        item.setGoodsName(l.getGoods().getGoodsName());
        item.setSkuCode(l.getSku().getSkuCode());
        item.setSkuName(l.getSku().getSkuName());
        LipstickExtension extension = JsonUtils.toJavaObject(l.getSku().getExtension(), LipstickExtension.class);
        item.setColorNo(Optional.ofNullable(extension).map(LipstickExtension::getColorNo).orElse(""));
        item.setColor(Optional.ofNullable(extension).map(LipstickExtension::getColor).orElse(""));
        item.setColor1(Optional.ofNullable(extension).map(LipstickExtension::getColor1).orElse(null));
        item.setFigure(Optional.ofNullable(extension).map(LipstickExtension::getFigure).orElse(""));
        return item;
    }

    @Getter
    @AllArgsConstructor
    private static class SimilarColorDistanceWrapper {
        private LipstickListItem lipstick;

        private double distance;
    }
}