package org.magi.module.commons.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class SpiderLocalRecommend {
    private String countryName;
    private String countryThumImgUrl;
    private String countryImgUrl;
    private Date version;
    private String sourceType;


    private List<SpiderLocalSpot> spots;

}
