package com.polaris.he.lipstick.service.impl;

import com.polaris.he.lipstick.dao.GoodsDao;
import com.polaris.he.lipstick.dao.object.GoodsDO;
import com.polaris.he.lipstick.entity.Goods;
import com.polaris.he.lipstick.entity.GoodsCategoryMapping;
import com.polaris.he.lipstick.service.GoodsService;
import com.polaris.he.lipstick.utils.BeanCopyUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.Collection;

/**
 * User: hexie
 * Date: 2019-01-10 22:54
 * Description:
 */
@Slf4j
@Service
public class GoodsServiceImpl implements GoodsService {

    @Resource
    private GoodsDao goodsDao;

    @Override
    @Transactional
    public int save(String type, Collection<Goods> collection) {
        return 0;
    }

    @Override
    @Transactional
    public int saveGoodsCategoriesMapping(String type, Collection<GoodsCategoryMapping> collection) {
        return 0;
    }

    @Override
    public Goods getByCode(String type, String code) {
        GoodsDO goodsDO = goodsDao.getByCode(type, code);
        return BeanCopyUtils.copyObject(goodsDO, new Goods());
    }
}