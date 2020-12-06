package org.magi.module.esimdb;

import org.magi.module.commons.pipeline.PlanPersistencePipeline;
import org.magi.module.esimdb.db.PlanMysqlProcess;
import org.magi.module.redteago.page.IndexPageProcessor;
import org.magi.module.redteago.page.IndexPipeline;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import us.codecraft.webmagic.Spider;

import java.util.concurrent.Executors;

@Component
public class EsimDBStartUp implements CommandLineRunner {

    @Autowired
    private PlanMysqlProcess process;

    @Override
    public void run(String... args) throws Exception {
        Executors.newSingleThreadExecutor().submit(new PlanPersistencePipeline(process));
        Executors.newSingleThreadExecutor().submit(new Runnable() {
            @Override
            public void run() {
                Spider.create(new IndexPageProcessor())
                        .addPipeline(new IndexPipeline())
                        .addUrl("https://esimdb.com")
                        .thread(5)
                        .run();
            }
        });


    }
}
