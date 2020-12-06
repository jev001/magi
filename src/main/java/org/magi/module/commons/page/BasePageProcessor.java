package org.magi.module.commons.page;

import org.magi.Constans;
import us.codecraft.webmagic.Site;
import us.codecraft.webmagic.processor.PageProcessor;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

/**
 * @author jonah
 */
public abstract class BasePageProcessor implements PageProcessor {
    private static final List<Pattern> PATTERNS = new ArrayList<>();

    static {
        for (String ignoreUrl : Constans.IGNORE_URL_PATTER) {
            PATTERNS.add(Pattern.compile(ignoreUrl));
        }
    }

    private final Site site = Site.me().setRetryTimes(3).setSleepTime(100);

    protected String[] fetchUrl(List<String> all) {
        return all.stream()
                       .filter(this::extra)
                       .collect(Collectors.toSet())
                       .toArray(String[]::new);
    }

    protected boolean ignore(String url) {
        return ignoreUrl(url) || ignorePatter(url);
    }

    private boolean ignoreUrl(String url) {
        for (String ignoreUrl : Constans.IGNORE_URL) {
            if (ignoreUrl.equalsIgnoreCase(url)) {
                return true;
            }
        }
        return false;
    }

    private boolean ignorePatter(String url) {
        boolean flag;
        String urlLow = url.toLowerCase();

        for (Pattern pattern : PATTERNS) {
            flag = pattern.matcher(urlLow).find();
            if (!flag) {
                return false;
            }
        }
        return true;
    }

    protected boolean extra(String url) {
        return !ignore(url);
    }

    @Override
    public Site getSite() {
        return site;
    }
}