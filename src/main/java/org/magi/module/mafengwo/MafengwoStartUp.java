package org.magi.module.mafengwo;

import lombok.extern.slf4j.Slf4j;
import org.magi.module.commons.db.CityModeMapper;
import org.magi.module.commons.db.ProxyModelMapper;
import org.magi.module.commons.model.CityModel;
import org.magi.module.commons.model.ProxyModel;
import org.magi.module.commons.pipeline.LocalRecommendPersistencePipeline;
import org.magi.module.mafengwo.db.LocalRecommendMysqlProcess;
import org.magi.module.mafengwo.page.IndexPageProcessor;
import org.magi.module.mafengwo.page.IndexPipeline;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import us.codecraft.webmagic.Spider;
import us.codecraft.webmagic.proxy.Proxy;

import java.util.List;
import java.util.concurrent.Executors;

@Slf4j
@Component
public class MafengwoStartUp implements CommandLineRunner {

    @Autowired
    private CityModeMapper cityModeMapper;
    @Autowired
    private ProxyModelMapper proxyModelMapper;
    @Autowired
    private LocalRecommendMysqlProcess process;

    @Override
    public void run(String... args) throws Exception {
//        Executors.newSingleThreadExecutor().submit(new LocalRecommendPersistencePipeline(process));
//        run();
    }

    private void run() {
        List<CityModel> allCity = cityModeMapper.getAllCity();
        List<ProxyModel> allProxyModel = proxyModelMapper.getAllProxyModel();
        List<Proxy> proxies = proxyModelMapper.convert(allProxyModel);

        Executors.newSingleThreadExecutor().submit(() -> Spider.create(new IndexPageProcessor().setCityModels(allCity))
                                                                 .addPipeline(new IndexPipeline().setProxies(proxies))
                                                                 .addUrl("https://www.baidu.com")
                                                                 .thread(5)
                                                                 .run());
    }
}
