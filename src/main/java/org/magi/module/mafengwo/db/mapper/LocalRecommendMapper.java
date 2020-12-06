package org.magi.module.mafengwo.db.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.magi.module.commons.model.SpiderLocalRecommend;
import org.magi.module.mafengwo.db.LocalRecommend;
import org.springframework.stereotype.Repository;

@Repository
@Mapper
public interface LocalRecommendMapper extends BaseMapper<LocalRecommend> {

    LocalRecommend spider2db(SpiderLocalRecommend localRecommend);

}
