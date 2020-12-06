package org.magi.module.commons.db;

import cn.hutool.core.text.csv.CsvUtil;
import lombok.extern.slf4j.Slf4j;
import org.magi.module.commons.model.CityModel;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Repository;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.util.ArrayList;
import java.util.List;

@Slf4j
@Repository
public class CityModeMapper {

    public List<CityModel> getAllCity() {
        ClassPathResource classPathResource = new ClassPathResource("city_cn.csv");
        try (InputStream is = classPathResource.getInputStream();
             Reader reader = new BufferedReader(new InputStreamReader(is))) {
            return CsvUtil.getReader().read(reader, CityModel.class);
        } catch (IOException e) {
            log.error("io err", e);
        }
        return new ArrayList<>();
    }

}
