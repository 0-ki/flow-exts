package com.homework.flow.exts;

import com.homework.flow.exts.config.JdbcTemplateConfig;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Import;

@Import(JdbcTemplateConfig.class)
@SpringBootApplication(scanBasePackages = "com.homework.flow.exts.web")
public class ExtsApplication {

	public static void main(String[] args) {
		SpringApplication.run(ExtsApplication.class, args);
	}

}
