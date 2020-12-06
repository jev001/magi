package org.magi.module.esimdb.page;

import lombok.extern.slf4j.Slf4j;
import org.magi.module.commons.enums.EnumSourceType;
import org.magi.module.commons.model.SpiderPlan;
import org.magi.module.commons.pipeline.PlanPersistencePipeline;
import us.codecraft.webmagic.ResultItems;
import us.codecraft.webmagic.Task;
import us.codecraft.webmagic.pipeline.Pipeline;

/**
 * @author jonah
 */
@Slf4j
public class CarrierPipeline implements Pipeline {

    protected static final String MODELS = "carrierPage";

    @Override
    public void process(ResultItems resultItems, Task task) {
        SpiderPlan model = resultItems.get(MODELS);
        model.setSourceType(EnumSourceType.ESIM_DB.getSourceType());
        PlanPersistencePipeline.push(model);


    }
}
