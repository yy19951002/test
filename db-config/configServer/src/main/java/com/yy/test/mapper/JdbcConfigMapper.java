package com.huayun.test.mapper;

import com.huayun.test.model.JdbcConfig;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface JdbcConfigMapper {

    List<JdbcConfig> getJdbcConfigs(@Param("application") String application);

    JdbcConfig getJdbcConfig(@Param("key") String key,
                             @Param("application") String application,
                             @Param("profile") String profile,
                             @Param("label") String label);

    JdbcConfig getJdbcConfigById(@Param("id") String id);

    void insert(JdbcConfig jdbcConfig);

    void update(JdbcConfig jdbcConfig);

    void delete(String[] ids);
}
