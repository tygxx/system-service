package com.yy.ds.system.config;

import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.hibernate5.Hibernate5Module;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class HibernateConf {

    @Bean
    public ObjectMapper objectMapper() {
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        Hibernate5Module module = new Hibernate5Module();
        // 禁用(表示要忽略@Transient字段属性,默认为true,设置为false禁用)
        module.disable(Hibernate5Module.Feature.USE_TRANSIENT_ANNOTATION);
        // 延时加载的对象不使用时设置为null
        module.enable(Hibernate5Module.Feature.SERIALIZE_IDENTIFIER_FOR_LAZY_NOT_LOADED_OBJECTS);
        objectMapper.registerModule(module);
        // 序列化时,属性值为null的忽略（让未加载的属性不输出）
        objectMapper.setSerializationInclusion(Include.NON_NULL);
        return objectMapper;
    }

}