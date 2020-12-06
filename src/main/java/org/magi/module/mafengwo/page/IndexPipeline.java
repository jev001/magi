package org.magi.module.mafengwo.page;

import cn.hutool.json.JSONUtil;
import lombok.Data;
import lombok.experimental.Accessors;
import lombok.extern.slf4j.Slf4j;
import org.magi.module.commons.pipeline.XiaoXiangProxyProver;
import us.codecraft.webmagic.ResultItems;
import us.codecraft.webmagic.Spider;
import us.codecraft.webmagic.Task;
import us.codecraft.webmagic.downloader.HttpClientDownloader;
import us.codecraft.webmagic.pipeline.Pipeline;
import us.codecraft.webmagic.proxy.Proxy;
import us.codecraft.webmagic.proxy.SimpleProxyProvider;

import java.util.List;

@Data
@Slf4j
@Accessors(chain = true)
public class IndexPipeline implements Pipeline {

    public static final String URL = "url";
    Spider spider = Spider.create(new SpotPageProcessor()).addPipeline(new SpotPipeline()).thread(5);
    private List<Proxy> proxies;


    @Override
    public void process(ResultItems resultItems, Task task) {
        String[] urls = resultItems.get(IndexPipeline.URL);
        HttpClientDownloader httpClientDownloader = new HttpClientDownloader();
        // 代理池
        log.info("proxys = [{}]", JSONUtil.toJsonStr(proxies));
//        SimpleProxyProvider simpleProxyProvider = new SimpleProxyProvider(proxies);
        XiaoXiangProxyProver xiaoXiangProxyProver = new XiaoXiangProxyProver();
        httpClientDownloader.setProxyProvider(xiaoXiangProxyProver);
        spider.addUrl(urls)
                .setDownloader(httpClientDownloader).run();
    }
}