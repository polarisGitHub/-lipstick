package com.polaris.he.lipstick.importer.data;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * User: hexie
 * Date: 2019-01-16 23:11
 * Description:
 */
@Getter
@Setter
@ToString
public class UploadValidateErrorLine {

    private int line;

    private String content;

    private String error;
}