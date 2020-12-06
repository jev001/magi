package org.magi.module.esimdb.db;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.magi.module.commons.db.PersistenceProcess;
import org.magi.module.commons.model.SpiderPlan;
import org.magi.module.esimdb.db.convert.PlanConvert;
import org.magi.module.esimdb.db.mapper.PlanMapper;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import java.util.List;

@Slf4j
@Component
public class PlanMysqlProcess extends ServiceImpl<PlanMapper, Plan>
        implements PersistenceProcess<SpiderPlan> {


    @Override
    public void process(List<SpiderPlan> modes) {
        if (CollectionUtils.isEmpty(modes)) {
            log.debug("modes is empty");
        }
        // TODO
    }

    @Override
    public void process(SpiderPlan model) {
        List<Plan> plans = PlanConvert.INSTANCE.model2plan(model);
        if (CollectionUtils.isEmpty(plans)) {
            log.warn("plans is empty");
            return;
        }
        // 处理重复数据
        this.saveBatch(plans);
    }

}
