package com.huayun.test.service.impl;

import com.huayun.test.mapper.JdbcConfigMapper;
import com.huayun.test.model.JdbcConfig;
import com.huayun.test.service.JdbcConfigService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service("JdbcConfigService")
public class JdbcConfigServiceImpl implements JdbcConfigService {
    private Logger logger = Logger.getLogger(this.getClass());

    @Autowired
    private JdbcConfigMapper jdbcConfigMapper;

    @Override
    public List<JdbcConfig> getJdbcConfigs(String application) {
        // TODO Auto-generated method stub
        List<JdbcConfig> jdbcConfigs =null;
        jdbcConfigs = jdbcConfigMapper.getJdbcConfigs(application);
        return jdbcConfigs;
    }

    @Override
    public JdbcConfig getJdbcConfigById(String id) {
        // TODO Auto-generated method stub
        JdbcConfig jdbcConfig = null;
        jdbcConfig = jdbcConfigMapper.getJdbcConfigById(id);

        return jdbcConfig;
    }

    @Override
    public JdbcConfig getJdbcConfig(String key, String application, String profile, String label) {
        // TODO Auto-generated method stub
        JdbcConfig jdbcConfig = null;
        jdbcConfig = jdbcConfigMapper.getJdbcConfig(key, application, profile, label);

        return jdbcConfig;
    }

    /*
     * (non-Javadoc)
     *
     * @see
     * com.huayun.csm.service.ISystemService#insert(com.huayun.csm.entity.Page)
     */
    @Transactional(rollbackFor = Exception.class)
    @Override
    public JdbcConfig insert(JdbcConfig jdbcConfig) {
        // TODO Auto-generated method stub
        logger.info("向配置表中添加数据");
        jdbcConfigMapper.insert(jdbcConfig);

        return jdbcConfigMapper.getJdbcConfig(jdbcConfig.getKey(), jdbcConfig.getApplication(), jdbcConfig.getProfile(), jdbcConfig.getLabel());
    }

    /*
     * (non-Javadoc)
     *
     * @see
     * com.huayun.csm.service.ISystemService#update(com.huayun.csm.entity.Page)
     */
    @Transactional(rollbackFor = Exception.class)
    @Override
    public JdbcConfig update(JdbcConfig jdbcConfig) {
        // TODO Auto-generated method stub
        jdbcConfigMapper.update(jdbcConfig);

        return jdbcConfigMapper.getJdbcConfig(jdbcConfig.getKey(), jdbcConfig.getApplication(), jdbcConfig.getProfile(), jdbcConfig.getLabel());
    }

    /*
     * (non-Javadoc)
     *
     * @see com.huayun.csm.service.ISystemService#delete(String[])
     */
    @Transactional(rollbackFor = Exception.class)
    @Override
    public void delete(String[] ids) {
        // TODO Auto-generated method stub
        logger.info("开始删除配置表中配置" + ids);
        if (ids != null) {
            jdbcConfigMapper.delete(ids);
        }
    }
}
