package com.polaris.he.lipstick.service;

import com.polaris.he.lipstick.entity.LipstickItem;

import java.util.List;

/**
 * User: hexie
 * Date: 2019-01-05 21:51
 * Description:
 */
public interface LipstickSearchService {

    LipstickItem search(List<String> brandCodes, List<String> categories, String colorNo);
}