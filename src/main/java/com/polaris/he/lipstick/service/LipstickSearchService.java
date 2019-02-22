package com.polaris.he.lipstick.service;

import com.polaris.he.framework.entity.page.Pull;
import com.polaris.he.lipstick.entity.LipstickListItem;

import java.util.List;

/**
 * User: hexie
 * Date: 2019-01-05 21:51
 * Description:
 */
public interface LipstickSearchService {

    /**
     * @param brandCodes
     * @param categories
     * @param colorNo
     * @return
     */
    List<LipstickListItem> search(List<String> brandCodes, List<String> categories, String colorNo, Pull pull);

    /**
     * @param brandCodes
     * @param categories
     * @param color
     * @param threshold
     * @return
     */
    List<LipstickListItem> similar(List<String> brandCodes, List<String> categories, String color, Double threshold);

}