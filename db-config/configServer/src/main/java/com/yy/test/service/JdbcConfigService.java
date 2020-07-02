package com.huayun.test.service;

import com.huayun.test.model.JdbcConfig;

import java.util.List;

public interface JdbcConfigService {

    List<JdbcConfig> getJdbcConfigs(String application);

    JdbcConfig getJdbcConfigById(String id);

    JdbcConfig getJdbcConfig(String key, String application, String profile, String label);

    JdbcConfig insert(JdbcConfig jdbcConfig);

    JdbcConfig update(JdbcConfig jdbcConfig);

    void delete(String[] ids);
}
