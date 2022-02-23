package com.samuel.service.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.ImportResource;

@Configuration
@ImportResource("classpath:applicationContext-security.xml")
@ComponentScan(basePackages = {"com.samuel.repository",
        "com.samuel.service", "com.samuel.desktopclient"})
public class SpringContextConfig {
}