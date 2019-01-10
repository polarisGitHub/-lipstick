package com.polaris.he.lipstick.service.impl;

import com.polaris.he.lipstick.dao.GoodsDao;
import com.polaris.he.lipstick.dao.object.GoodsDO;
import com.polaris.he.lipstick.entity.Goods;
import com.polaris.he.lipstick.service.GoodsService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

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
    public Goods getByCode(String type, String code) {
        GoodsDO goodsDO = goodsDao.getByCode(type, code);
        Goods ret = new Goods();
        BeanUtils.copyProperties(goodsDO, ret);
        return ret;
    }
}