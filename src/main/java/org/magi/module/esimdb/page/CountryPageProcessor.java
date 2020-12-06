package org.magi.module.esimdb.page;

import org.magi.module.commons.page.BasePageProcessor;
import us.codecraft.webmagic.Page;

import java.util.List;

/**
 * 国家 Page
 *
 * @author jonah
 */
public class CountryPageProcessor extends BasePageProcessor {

    @Override
    public void process(Page page) {
        List<String> all = page.getHtml().links().regex("(https://esimdb.com/\\w+/\\w+)").all();
        String[] url = fetchUrl(all);
        page.putField(CountryPagePipeline.URL, url);
    }
}
