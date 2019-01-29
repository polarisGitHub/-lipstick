package com.polaris.he.lipstick.annotation;

import lombok.*;

/**
 * User: hexie
 * Date: 2019-01-29 20:23
 * Description:
 */
@Setter
@Getter
@ToString
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
public class EncryptionTestData {

    @Encryption
    private String code;

    private String code1;

    @Encryption
    private Integer id;

    private Integer id1;
}