package org.magi.module.commons.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class SpiderLocalSpot {

    private String spotName;
    private String spotContentUrl;
    private String spotThumbnailImgUrl;
    private String spotImgUrl;


}
