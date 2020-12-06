package org.magi.module.commons.model;

import cn.hutool.core.annotation.Alias;
import lombok.Data;

@Data
public class CityModel {
    private String id;
    private String country;
    private String state;
    private String city;
    @Alias("state_code")
    private String stateCode;
    @Alias("city_code")
    private String cityCode;
    @Alias("country_code")
    private String countryCode;

}
