package org.magi.module.commons.db;

import cn.hutool.core.text.csv.CsvUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.math.NumberUtils;
import org.magi.module.commons.model.ProxyModel;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Repository;
import org.springframework.util.CollectionUtils;
import us.codecraft.webmagic.proxy.Proxy;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.util.ArrayList;
import java.util.List;

@Slf4j
@Repository
public class ProxyModelMapper {

    public List<ProxyModel> getAllProxyModel() {
        ClassPathResource classPathResource = new ClassPathResource("proxy.csv");
        try (InputStream is = classPathResource.getInputStream();
             Reader reader = new BufferedReader(new InputStreamReader(is))) {
            return CsvUtil.getReader().read(reader, ProxyModel.class);
        } catch (IOException e) {
            log.error("io err", e);
        }
        return new ArrayList<>();
    }


    public List<Proxy> convert(List<ProxyModel> proxyModels) {
        if (CollectionUtils.isEmpty(proxyModels)) {
            return new ArrayList<>();
        }

        List<Proxy> proxys = new ArrayList<>();
        for (ProxyModel proxyModel : proxyModels) {
            String ip = proxyModel.getIp();
            String port = proxyModel.getPort();
            if (!NumberUtils.isNumber(port)) {
                log.debug(" number is not number ip=[{}],port=[{}]", ip, port);
                continue;
            }
            proxys.add(new Proxy(ip, Integer.parseInt(port)));
        }
        return proxys;
    }
}
