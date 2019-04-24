package com.project.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.bind.annotation.CrossOrigin;

import com.baomidou.mybatisplus.core.injector.ISqlInjector;
import com.baomidou.mybatisplus.extension.injector.LogicSqlInjector;

@Configuration
public class MyBatisPlusConfig {
	@Bean
	public ISqlInjector sqlInjector() {
		return new LogicSqlInjector();
	}
}
