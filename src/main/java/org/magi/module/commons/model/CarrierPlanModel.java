package org.magi.module.commons.model;


import lombok.Builder;
import lombok.Data;

/**
 * @author jonah
 */
@Data
@Builder
public class CarrierPlanModel {
    private String title;
    private String price;
    private String volume;
    private String additionalInfo;
}
