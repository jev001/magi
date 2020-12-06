package org.magi.module.esimdb.db.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.magi.module.esimdb.db.Plan;
import org.springframework.stereotype.Repository;

@Repository
@Mapper
public interface PlanMapper extends BaseMapper<Plan> {
}
