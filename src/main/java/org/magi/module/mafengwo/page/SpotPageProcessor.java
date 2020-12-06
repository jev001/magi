package org.magi.module.mafengwo.page;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.magi.module.commons.model.SpiderLocalRecommend;
import org.magi.module.commons.model.SpiderLocalSpot;
import org.magi.module.commons.page.BasePageProcessor;
import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.Site;
import us.codecraft.webmagic.selector.Selectable;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Slf4j
public class SpotPageProcessor extends BasePageProcessor {

    private final Site site = Site.me().setRetryTimes(10);

    Pattern pattern = Pattern.compile("url\\((?<url>(.*)?)\\)");

    @Override
    public void process(Page page) {
        String hotAtt = page.getHtml().css(".hot-att").get();
        if (StringUtils.isBlank(hotAtt)) {
            log.debug("not match hotAtt");
            page.setSkip(true);
            return;
        }

        SpiderLocalRecommend localRecommend = getSpiderLocalRecommend(page);
        List<SpiderLocalSpot> spots = getSpots(page);
        localRecommend.setSpots(spots);
        page.putField(SpotPipeline.MODEL, localRecommend);

    }

    private SpiderLocalRecommend getSpiderLocalRecommend(Page page) {
        String countryName = page.getHtml().css(".search-mdd-wrap").css(".title", "allText").get();
        String countryUrlWrapper = page.getHtml().css(".search-mdd-wrap", "style").get();
        Matcher matcher = pattern.matcher(countryUrlWrapper);
        String countryUrlThum = "";
        String countryUrl = "";
        if (matcher.find()) {
            countryUrlThum = matcher.group("url");
        }

        if (StringUtils.isNotBlank(countryUrlThum)) {
            countryUrl = countryUrlThum.replaceAll("\\?.*", "");
        }

        return SpiderLocalRecommend.builder()
                       .countryName(countryName)
                       .countryThumImgUrl(countryUrlThum)
                       .countryImgUrl(countryUrl)
                       .build();
    }

    private List<SpiderLocalSpot> getSpots(Page page) {
        List<Selectable> nodes = page.getHtml().css(".hot-att>ul>li").nodes();
        List<SpiderLocalSpot> spots = new ArrayList<>();
        for (Selectable node : nodes) {
            String spotName = node.$("p>a", "allText").get();
            String spotContentUrl = node.$("p>a").links().get();
            String spotThumbnailImgUrl = node.$("img", "src").get();
            String spotImgUrl = spotThumbnailImgUrl.replaceAll("\\?.*", "");
            SpiderLocalSpot spot = SpiderLocalSpot.builder()
                                           .spotName(spotName)
                                           .spotImgUrl(spotImgUrl)
                                           .spotContentUrl(spotContentUrl)
                                           .spotThumbnailImgUrl(spotThumbnailImgUrl)
                                           .build();
            spots.add(spot);
        }
        return spots;
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