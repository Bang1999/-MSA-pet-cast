package com.varchar6.petcast.config;

import org.apache.ibatis.annotations.Mapper;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@MapperScan(basePackages = "com.varchar6.petcast", annotationClass = Mapper.class)
public class MybatisConfiguration {
}
