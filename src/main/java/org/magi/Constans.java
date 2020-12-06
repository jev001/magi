package org.magi;

import java.util.ArrayList;
import java.util.List;

/**
 * 忽略配置
 *
 * @author jonah
 */
public class Constans {
    /**
     * 忽略 url
     */
    public static final List<String> IGNORE_URL_PATTER = new ArrayList<>();
    public static final List<String> IGNORE_URL = new ArrayList<>();
    public static final String ESIM_DB_BASE_URL = "https://esimdb.com";
    public static final String MA_FENG_WO_SEARCH = "http://www.mafengwo.cn/search/q.php?q=";

    static {
        IGNORE_URL_PATTER.add("https://esimdb\\.com/ja/\\w+");
        IGNORE_URL.add("https://esimdb.com/ja");
        IGNORE_URL.add("https://esimdb.com/about");
    }
}
