package com.polaris.he.lipstick.importer.impl;

import com.polaris.he.lipstick.entity.LipstickUploadData;
import com.polaris.he.lipstick.importer.AbstractUploadImporter;
import com.polaris.he.lipstick.importer.converter.UploadByteConverter;
import com.polaris.he.lipstick.importer.data.UploadValidateResult;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.List;

/**
 * User: hexie
 * Date: 2019-01-16 23:30
 * Description:
 */
@Component
public class LipstickUploadImporter extends AbstractUploadImporter<LipstickUploadData> {

    @Resource(type = LipstickUploadConverter.class)
    private UploadByteConverter converter;

    @Override
    protected List<LipstickUploadData> byteConvert(byte[] data, String extension) {
        return converter.convert(data, extension, LipstickUploadData.class);
    }

    @Override
    protected UploadValidateResult validate(List<LipstickUploadData> data) {
        return null;
    }

    @Override
    protected void resolved(List<LipstickUploadData> data) {

    }
}