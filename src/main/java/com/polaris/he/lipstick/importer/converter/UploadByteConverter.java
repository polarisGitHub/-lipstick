package com.polaris.he.lipstick.importer.converter;


import java.util.List;

/**
 * User: hexie
 * Date: 2019-01-16 21:14
 * Description:
 */
public interface UploadByteConverter {


    /**
     * @return
     */
    UploadExtensionExtensionEnum extension();

    /**
     * @param data
     * @return
     */
    <T> List<T> convert(byte[] data, Class<T> clazz);

}