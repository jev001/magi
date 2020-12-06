package org.magi.module.esimdb.db.convert;


import org.magi.module.commons.model.CarrierModel;
import org.magi.module.commons.model.SpiderPlan;
import org.magi.module.commons.model.CarrierPlanModel;
import org.magi.module.esimdb.db.Plan;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.factory.Mappers;

import java.util.ArrayList;
import java.util.List;

/**
 * @author jonah
 */
@Mapper
public interface PlanConvert {

    PlanConvert INSTANCE = Mappers.getMapper(PlanConvert.class);

    default List<Plan> model2plan(SpiderPlan model) {
        List<Plan> plans = new ArrayList<>();
        List<CarrierPlanModel> planModels = model.getPlans();
        for (CarrierPlanModel planModel : planModels) {
            Plan plan = carrier2plan(planModel);
            plan.setVersion(model.getVersion());
            plan.setSourceType(model.getSourceType());
            fillCarrier(plan, model.getCarrier());
            plans.add(plan);
        }
        return plans;
    }

    default void fillCarrier(Plan plan, CarrierModel carrier) {
        plan.setCarrierCountry(carrier.getCountry());
        plan.setCarrierDesc(carrier.getDesc());
        plan.setCarrierImgUrl(carrier.getImgUrl());
        plan.setCarrierName(carrier.getName());
        plan.setCarrierUrl(carrier.getUrl());
    }

    @Mappings({
            @Mapping(source = "title", target = "planTitle"),
            @Mapping(source = "price", target = "planPrice"),
            @Mapping(source = "volume", target = "planVolume"),
            @Mapping(source = "additionalInfo", target = "planAdditionalInfo"),
    })
    Plan carrier2plan(CarrierPlanModel carrier);


}
