package org.magi.module.commons.pipeline;

import cn.hutool.json.JSONUtil;
import lombok.extern.slf4j.Slf4j;
import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.Task;
import us.codecraft.webmagic.proxy.Proxy;
import us.codecraft.webmagic.proxy.ProxyProvider;

@Slf4j
public class XiaoXiangProxyProver implements ProxyProvider {


    private final static String ProxyUser = "652510250850996224";
    private final static String ProxyPass = "j3REKQOW";

    // 代理服务器
    private final static String ProxyHost = "http-dynamic.xiaoxiangdaili.com";
    private final static Integer ProxyPort = 10030;


    @Override
    public void returnProxy(Proxy proxy, Page page, Task task) {
        // TODO
        log.debug("proxy =[{}],page=[{}],task={}", JSONUtil.toJsonStr(proxy), JSONUtil.toJsonStr(page),
                JSONUtil.toJsonStr(task));
    }

    @Override
    public Proxy getProxy(Task task) {
        return new Proxy(ProxyHost, ProxyPort,ProxyUser,ProxyPass);
    }

}