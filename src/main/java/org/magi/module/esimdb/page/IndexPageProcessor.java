package org.magi.module.esimdb.page;

import org.magi.module.commons.page.BasePageProcessor;
import us.codecraft.webmagic.Page;

import java.util.List;

public class IndexPageProcessor extends BasePageProcessor {

    @Override
    public void process(Page page) {
        List<String> all = page.getHtml().links().regex("(https://esimdb.com/\\w+)").all();
        String[] fetchUrl = fetchUrl(all);
        page.putField(IndexPipeline.URL, fetchUrl);
    }
}