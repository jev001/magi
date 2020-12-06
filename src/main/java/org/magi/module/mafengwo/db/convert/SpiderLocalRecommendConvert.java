package org.magi.module.mafengwo.db.convert;

import org.magi.module.commons.model.SpiderLocalRecommend;
import org.magi.module.commons.model.SpiderLocalSpot;
import org.magi.module.mafengwo.db.LocalRecommend;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.factory.Mappers;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.List;

@Mapper
public interface SpiderLocalRecommendConvert {

    SpiderLocalRecommendConvert INSTANCE = Mappers.getMapper(SpiderLocalRecommendConvert.class);

    Logger LOG = LoggerFactory.getLogger(SpiderLocalRecommendConvert.class);

    default List<LocalRecommend> spider2db(SpiderLocalRecommend spiderLocalRecommend) {
        List<LocalRecommend> list = new ArrayList<>();
        List<SpiderLocalSpot> spots = spiderLocalRecommend.getSpots();
        if (CollectionUtils.isEmpty(spots)) {
            LOG.error("spots is empty");
            return list;
        }
        for (SpiderLocalSpot spot : spots) {
            LocalRecommend localRecommend = spider2dbSimple(spiderLocalRecommend);
            fillSpot(localRecommend, spot);
            list.add(localRecommend);
        }
        return list;
    }

    default LocalRecommend fillSpot(LocalRecommend localRecommend, SpiderLocalSpot spot) {
        localRecommend.setSpotName(spot.getSpotName());
        localRecommend.setSpotContentUrl(spot.getSpotContentUrl());
        localRecommend.setSpotThumbnailImgUrl(spot.getSpotThumbnailImgUrl());
        localRecommend.setSpotImgUrl(spot.getSpotImgUrl());
        return localRecommend;
    }

    @Mappings({
            @Mapping(source = "countryName", target = "name"),
            @Mapping(source = "countryImgUrl", target = "imgUrl"),
            @Mapping(source = "countryThumImgUrl", target = "thumImgUrl"),
            @Mapping(source = "version", target = "version"),
            @Mapping(source = "sourceType", target = "sourceType"),
    })
    LocalRecommend spider2dbSimple(SpiderLocalRecommend spiderLocalRecommend);

}
