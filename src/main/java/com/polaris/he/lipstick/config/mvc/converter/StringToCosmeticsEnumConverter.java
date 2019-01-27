package com.polaris.he.lipstick.config.mvc.converter;

import com.polaris.he.lipstick.entity.constanst.CosmeticsEnum;
import org.springframework.core.convert.converter.Converter;

/**
 * User: hexie
 * Date: 2019-01-27 23:05
 * Description:
 */
public class StringToCosmeticsEnumConverter implements Converter<String, CosmeticsEnum> {
    @Override
    public CosmeticsEnum convert(String source) {
        return CosmeticsEnum.getByCode(source);
    }
}