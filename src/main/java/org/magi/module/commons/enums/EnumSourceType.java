package org.magi.module.commons.enums;


import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum EnumSourceType {

    /**
     * esimdb
     */
    ESIM_DB("ESIM_DB"),

    /**
     * esimdb
     */
    MA_FENG_WO("MA_FENG_WO"),
    /**
     * esimdb
     */
    RED_TEA_GO("RED_TEA_GO"),
    ;
    private final String sourceType;

}
