package com.polaris.he.lipstick.service.impl;

import com.polaris.he.lipstick.entity.LipstickItem;
import com.polaris.he.lipstick.service.LipstickSearchService;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * TODO 搜索最好走搜索引擎，现在没资源，直接在db中做
 *
 * User: hexie
 * Date: 2019-01-05 21:58
 * Description:
 */
@Service
public class LipstickSearchServiceImpl implements LipstickSearchService {


    @Override
    public LipstickItem search(List<String> brandCodes, List<String> categories, String colorNo) {
        return null;
    }
}