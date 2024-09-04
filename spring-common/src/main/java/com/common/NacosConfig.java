package com.common;

import com.alibaba.cloud.nacos.NacosDiscoveryProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

/**
 * @author lx
 * @data 2023/8/17 15:34
 */
@Configuration
public class NacosConfig {
    @Bean
    @Primary //当有多个相同类型的bean时，使用@Primary来赋予bean更高的优先级
    public NacosDiscoveryProperties nacosProperties() {
        NacosDiscoveryProperties nacosDiscoveryProperties = new NacosDiscoveryProperties();
        nacosDiscoveryProperties.setIp("localhost");
        return nacosDiscoveryProperties;
    }
}
