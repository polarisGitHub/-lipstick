package com.polaris.he.framework.utils;

import com.polaris.he.application.utils.EncryptionUtils;

/**
 * User: hexie
 * Date: 2019-02-22 23:38
 * Description:
 */
public class BizEncryptionUtils {

    public static String encode(String content) {
        return EncryptionUtils.AESEncode(content, SpringContextUtils.getProperty("encryption.aes.password.data"));
    }

    public static String decode(String content) {
        return EncryptionUtils.AESDecode(content, SpringContextUtils.getProperty("encryption.aes.password.data"));
    }
}