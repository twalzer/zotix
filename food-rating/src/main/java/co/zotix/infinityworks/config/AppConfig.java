/**
 * AppConfig.java 23/11/2015 21:33 akhettar $$
 * Copyright 2015 zotix.co, L.P. All rights reserved. $$
 */

package co.zotix.infinityworks.config;

import java.util.*;
import java.util.concurrent.*;

import com.google.common.cache.*;
import org.springframework.beans.factory.annotation.*;
import org.springframework.cache.*;
import org.springframework.cache.annotation.*;
import org.springframework.cache.guava.*;
import org.springframework.cache.support.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.GsonHttpMessageConverter;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.config.annotation.*;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;


@Configuration
@EnableWebMvc
@EnableCaching
public class AppConfig extends WebMvcConfigurerAdapter  {


    @Value("${cache.authority.timeout}")
    private int authorityTimeout;

    @Value("${cache.establishment.timeout}")
    private int establishmentTimeout;

    @Override
    public void configureMessageConverters(List<HttpMessageConverter<?>> converters) {
        GsonHttpMessageConverter msgConverter = new GsonHttpMessageConverter();
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        msgConverter.setGson(gson);
        converters.add(msgConverter);
    }

    @Bean
    RestTemplate template() {
        return new RestTemplate();
    }

    @Bean
    public CacheManager cacheManager() {
        SimpleCacheManager cacheManager = new SimpleCacheManager();
        GuavaCache authority = new GuavaCache("authority", CacheBuilder.newBuilder().expireAfterAccess(authorityTimeout, TimeUnit.DAYS).build());
        GuavaCache establishment = new GuavaCache("establishment", CacheBuilder.newBuilder().expireAfterAccess(establishmentTimeout, TimeUnit.HOURS).build());
        GuavaCache aggregator = new GuavaCache("aggregator", CacheBuilder.newBuilder().expireAfterAccess(2, TimeUnit.HOURS)
                .build());
        cacheManager.setCaches(Arrays.asList(authority, establishment, aggregator));
        return cacheManager;
    }

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/webjars/**").addResourceLocations("classpath:/META-INF/resources/webjars/");
    }

}

