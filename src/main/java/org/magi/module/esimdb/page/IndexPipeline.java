package org.magi.module.esimdb.page;

import us.codecraft.webmagic.ResultItems;
import us.codecraft.webmagic.Spider;
import us.codecraft.webmagic.Task;
import us.codecraft.webmagic.pipeline.Pipeline;

public class IndexPipeline implements Pipeline {

    public static final String URL = "url";

    private final Spider county = Spider.create(new CountryPageProcessor());

    @Override
    public void process(ResultItems resultItems, Task task) {
        String[] urls = resultItems.get(URL);
        county.addUrl(urls).addPipeline(new CountryPagePipeline()).thread(5).run();
    }
}