package org.magi.module.mafengwo.db;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

import java.util.Date;

@Data
public class LocalRecommend {
    @TableId(type = IdType.ASSIGN_ID)
    private String id;
    private String name;
    private String imgUrl;
    private String thumImgUrl;
    private Date version;
    private String sourceType;
    private String spotName;
    private String spotContentUrl;
    private String spotThumbnailImgUrl;
    private String spotImgUrl;
}
