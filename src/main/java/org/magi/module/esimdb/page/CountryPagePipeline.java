package org.magi.module.esimdb.page;

import lombok.AllArgsConstructor;
import lombok.Data;
import us.codecraft.webmagic.ResultItems;
import us.codecraft.webmagic.Spider;
import us.codecraft.webmagic.Task;
import us.codecraft.webmagic.pipeline.Pipeline;

/**
 * 国家 Page
 *
 * @author jonah
 */
@Data
@AllArgsConstructor
public class CountryPagePipeline implements Pipeline {

    public static final String URL = "url";
    private final Spider county = Spider.create(new CarrierPageProcessor());

    @Override
    public void process(ResultItems resultItems, Task task) {
        String[] urls = resultItems.get(URL);
        county.addUrl(urls)
                .addPipeline(new CarrierPipeline())
                .thread(5).run();
    }
}
