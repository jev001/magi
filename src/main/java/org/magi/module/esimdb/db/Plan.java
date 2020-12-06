package org.magi.module.esimdb.db;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Plan {

    @TableId(type = IdType.ASSIGN_ID)
    private String id;

    private String carrierName;
    private String carrierUrl;
    private String carrierDesc;
    private String carrierImgUrl;
    private String carrierCountry;


    private String planTitle;
    private String planPrice;
    private String planVolume;
    private String planAdditionalInfo;
    private Date version;
    private String sourceType;

}
