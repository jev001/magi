package org.magi.module.mafengwo.page;

import cn.hutool.core.lang.UUID;
import lombok.Data;
import lombok.experimental.Accessors;
import org.magi.Constans;
import org.magi.module.commons.page.BasePageProcessor;
import org.magi.module.commons.model.CityModel;
import org.springframework.util.CollectionUtils;
import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.Site;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Data
@Accessors(chain = true)
public class IndexPageProcessor extends BasePageProcessor {

    private final Site site = Site.me().setRetryTimes(10);
    private List<CityModel> cityModels;

    @Override
    public void process(Page page) {
        String[] searchPage = getSearchPage(page);
        page.putField(IndexPipeline.URL, searchPage);
    }


    private String[] getSearchPage(Page page) {
        if (CollectionUtils.isEmpty(cityModels)) {
            return new String[0];
        }
        List<String> city = new ArrayList<>();
        List<String> country =
                cityModels.stream().map(x -> Constans.MA_FENG_WO_SEARCH + x.getCountry()).distinct().collect(Collectors.toList());
        List<String> state =
                cityModels.stream().map(x -> Constans.MA_FENG_WO_SEARCH + x.getState()).distinct().collect(Collectors.toList());
        city.addAll(country);
        city.addAll(state);
        Set<String> set = new HashSet<>(city);
        return set.stream()
                       .map(x -> x+ "&seid=" + UUID.fastUUID().toString())
                       .toArray(String[]::new);
    }


    @Override
    public Site getSite() {
        site.addHeader("pragma", "no-cache");
        site.addHeader("cache-control", "no-cache");
        site.addHeader("dnt", "1");
        site.addHeader("upgrade-insecure-requests", "1");
        site.addHeader("user-agent", "Mozilla/5.0 (Macintosh; Intel Mac OS X 11_0_0) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/86.0.4240.198 Safari/537.36");
        site.addHeader("accept", "text/html,application/xhtml+xml,application/xml;q=0.9,image/avif,image/webp,image/apng,*/*;q=0.8,application/signed-exchange;v=b3;q=0.9");
        site.addHeader("sec-fetch-site", "none");
        site.addHeader("sec-fetch-mode", "navigate");
        site.addHeader("sec-fetch-user", "?1");
        site.addHeader("sec-fetch-dest", "document");
        site.addHeader("accept-encoding", "gzip, deflate, br");
        site.addHeader("accept-language", "en-US,en;q=0.9,zh-CN;q=0.8,zh;q=0.7");
        return site;
    }

}