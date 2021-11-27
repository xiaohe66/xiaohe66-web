package com.xiaohe66.web;

import com.xiaohe66.common.util.jackson.JacksonLongJsonDeserializer;
import org.springframework.boot.autoconfigure.jackson.Jackson2ObjectMapperBuilderCustomizer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author xiaohe
 * @since 2021.11.27 21:58
 */
@Configuration
public class MainConfiguration {

    @Bean
    public Jackson2ObjectMapperBuilderCustomizer jackson2ObjectMapperBuilderCustomizer() {

        return build -> build.deserializerByType(Long.class, new JacksonLongJsonDeserializer());
    }

}
