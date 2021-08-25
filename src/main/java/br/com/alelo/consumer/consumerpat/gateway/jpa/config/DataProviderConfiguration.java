package br.com.alelo.consumer.consumerpat.gateway.jpa.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@Configuration
@EnableJpaRepositories(basePackages = "br.com.alelo.consumer.consumerpat.gateway.jpa.respository")
public class DataProviderConfiguration {
}
