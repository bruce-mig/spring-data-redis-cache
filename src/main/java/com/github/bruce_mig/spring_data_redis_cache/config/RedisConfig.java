package com.github.bruce_mig.spring_data_redis_cache.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisStandaloneConfiguration;
import org.springframework.data.redis.connection.lettuce.LettuceConnectionFactory;

import io.lettuce.core.ClientOptions;
import org.springframework.data.redis.connection.lettuce.LettuceClientConfiguration;

@Configuration
public class RedisConfig {

    @Value("spring.redis.host")
    private String host;

    @Value("spring.redis.username")
    private String username;

    @Value("spring.redis.password")
    private String password;

    @Bean
    public LettuceConnectionFactory redisConnectionFactory(){
        RedisStandaloneConfiguration config = new RedisStandaloneConfiguration("localhost", 6379);
        config.setUsername("redisuser");
        config.setPassword("redispass");

        // Build custom client configuration with ClientOptions
        LettuceClientConfiguration clientConfig = LettuceClientConfiguration.builder()
                .clientOptions(ClientOptions.builder().build())
                .build();

        // Create the LettuceConnectionFactory with the configuration
        return new LettuceConnectionFactory(config, clientConfig);
    }
}
