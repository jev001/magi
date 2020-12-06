package org.magi.module.commons.model;

import cn.hutool.core.annotation.Alias;
import lombok.Data;
import lombok.experimental.Accessors;

/**s
 * @author jonah
 */
@Data
@Accessors(chain = true)
public class ProxyModel {
    @Alias("IP")
    private String ip;
    @Alias("PORT")
    private String port;
    @Alias("匿名度")
    private String ignoreName;
    @Alias("类型")
    private String type;
    @Alias("位置")
    private String location;
    @Alias("响应速度")
    private String speed;
    @Alias("最后验证时间")
    private String lastVersionDate;
}
