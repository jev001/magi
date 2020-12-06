package org.magi.module.mafengwo.db;

import cn.hutool.json.JSONUtil;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.magi.module.commons.db.PersistenceProcess;
import org.magi.module.commons.model.SpiderLocalRecommend;
import org.magi.module.mafengwo.db.convert.SpiderLocalRecommendConvert;
import org.magi.module.mafengwo.db.mapper.LocalRecommendMapper;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import java.util.List;

@Slf4j
@Component
public class LocalRecommendMysqlProcess extends ServiceImpl<LocalRecommendMapper, LocalRecommend>
        implements PersistenceProcess<SpiderLocalRecommend> {


    @Override
    public void process(List<SpiderLocalRecommend> modes) {
        if (CollectionUtils.isEmpty(modes)) {
            log.debug("modes is empty");
        }
        // TODO 批量
    }

    @Override
    public void process(SpiderLocalRecommend model) {
        List<LocalRecommend> localRecommends = SpiderLocalRecommendConvert.INSTANCE.spider2db(model);
        if (CollectionUtils.isEmpty(localRecommends)) {
            log.error("process=[{}]", JSONUtil.toJsonStr(model));
            return;
        }
        this.saveBatch(localRecommends);
    }

}
