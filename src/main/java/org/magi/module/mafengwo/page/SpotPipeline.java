package org.magi.module.mafengwo.page;

import lombok.extern.slf4j.Slf4j;
import org.magi.module.commons.enums.EnumSourceType;
import org.magi.module.commons.model.SpiderLocalRecommend;
import org.magi.module.commons.pipeline.LocalRecommendPersistencePipeline;
import us.codecraft.webmagic.ResultItems;
import us.codecraft.webmagic.Task;
import us.codecraft.webmagic.pipeline.Pipeline;

@Slf4j
public class SpotPipeline implements Pipeline {

    public static final String MODEL = "model";

    @Override
    public void process(ResultItems resultItems, Task task) {
        SpiderLocalRecommend recommend = resultItems.get(MODEL);
        recommend.setSourceType(EnumSourceType.MA_FENG_WO.getSourceType());
        LocalRecommendPersistencePipeline.push(recommend);
    }
}