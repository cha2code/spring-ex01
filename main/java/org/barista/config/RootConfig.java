package org.barista.config;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

// 설정 파일을 만들거나 bean을 등록하기 위한 어노테이션
@Configuration
@ComponentScan(basePackages = {"org.barista.sample"})
@MapperScan(basePackages = {"org.galapagos.mapper"})
public class RootConfig {

}
