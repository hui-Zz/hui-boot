package com.hui.project;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

//@ComponentScan("com.project")
@SpringBootApplication
@MapperScan("com.hui.project.mapper")
@EnableScheduling
public class HuiBootApiApplication {

	public static void main(String[] args) {
		SpringApplication.run(HuiBootApiApplication.class, args);
	}

	//@Bean
	//public CommandLineRunner commandLineRunner(ApplicationContext ctx) {
	//	return args -> {
	//
	//		System.out.println("Let's inspect the beans provided by Spring Boot:");
	//
	//		String[] beanNames = ctx.getBeanDefinitionNames();
	//		Arrays.sort(beanNames);
	//		for (String beanName : beanNames) {
	//			System.out.println(beanName);
	//		}
	//
	//	};
	//}

}
