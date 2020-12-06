package org.magi.module.commons.pipeline;

import cn.hutool.core.date.DateUtil;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.magi.module.commons.db.PersistenceProcess;
import org.magi.module.commons.model.SpiderPlan;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

/**
 * 数据库
 *
 * @author jonah
 */
@Slf4j
@AllArgsConstructor
public class PlanPersistencePipeline implements Runnable {

    private static final BlockingQueue<SpiderPlan> MESSAGE = new LinkedBlockingQueue<>();
    private final PersistenceProcess<SpiderPlan> process;

    public static void push(SpiderPlan model) {
        MESSAGE.add(model);
    }


    @Override
    public void run() {
        while (true) {
            try {
                log.debug("current message size=[{}]", MESSAGE.size());
                SpiderPlan take = MESSAGE.take();
                take.setVersion(DateUtil.date());
                process.process(take);
            } catch (Exception e) {
                log.error("take failed ", e);
            }
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                log.debug("sleep failed");
            }

        }
    }
}
