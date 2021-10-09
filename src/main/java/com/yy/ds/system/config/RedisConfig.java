package com.yy.ds.system.config;

/*
 *@Description: 如果不需要重置redis的缓存时长，则不用进行redis配置
 *@ClassAuthor: tengYong
 *@Date: 2021-09-26 12:55:19
*/
// @Configuration
public class RedisConfig {

    // @Value("${redisCahe.expireTime:3600}")
    // private Long expireTime;

    // public RedisCacheConfiguration config2() {
    //     RedisCacheConfiguration config = super.config();
    //     config = config.entryTtl(Duration.ofSeconds(expireTime)); // 根据项目设置默认缓存过期时长（基础配置为1天）
    //     return config;
    // }

}