package org.magi.module.commons.model;

import lombok.Builder;
import lombok.Data;

/**
 * 运营商模块
 *
 * @author jonah
 */
@Data
@Builder
public class CarrierModel {
    private String name;
    private String url;
    private String desc;
    private String imgUrl;
    private String country;
}
