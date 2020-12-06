package org.magi.module.commons.model;

import lombok.Builder;
import lombok.Data;

import java.util.Date;
import java.util.List;

@Data
@Builder
public class SpiderPlan {
    private CarrierModel carrier;
    private Date version;
    private String sourceType;
    private List<CarrierPlanModel> plans;
}
