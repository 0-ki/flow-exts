package com.homework.flow.exts.config;

import com.homework.flow.exts.repository.extension.ExtsRepository;
import com.homework.flow.exts.repository.extension.ExtsRepositoryImpl;
import com.homework.flow.exts.service.extension.ExtsService;
import com.homework.flow.exts.service.extension.ExtsServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import javax.sql.DataSource;

@Configuration
@RequiredArgsConstructor
public class JdbcTemplateConfig {

        private final DataSource dataSource;

        @Bean
        public ExtsService extsService() {
            return new ExtsServiceImpl(extsRepository());
        }

        @Bean
        public ExtsRepository extsRepository() {
            return new ExtsRepositoryImpl( dataSource);
        }

}
