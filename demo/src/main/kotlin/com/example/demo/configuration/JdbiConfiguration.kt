package com.example.demo.configuration

import com.zaxxer.hikari.HikariDataSource
import javax.sql.DataSource
import org.jdbi.v3.core.Handle
import org.jdbi.v3.core.Jdbi
import org.springframework.beans.factory.config.ConfigurableBeanFactory
import org.springframework.boot.autoconfigure.jdbc.DataSourceProperties
import org.springframework.boot.context.properties.ConfigurationProperties
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.context.annotation.Lazy
import org.springframework.context.annotation.Scope

@Configuration
class JdbiConfiguration {

    @Bean
    @ConfigurationProperties("spring.datasource")
    fun dataSourceProperties(): DataSourceProperties? {
        return DataSourceProperties()
    }

    @Bean
    @ConfigurationProperties(prefix = "spring.datasource.hikari")
    fun dataSource(dataSourceProperties: DataSourceProperties): DataSource {
        return dataSourceProperties.initializeDataSourceBuilder()
            .type(HikariDataSource::class.java)
            .build()
    }

    @Bean
    fun jdbiBean(dataSource: DataSource): Jdbi {
        return Jdbi.create(dataSource)
    }

    @Bean
    @Lazy
    @Scope(value = ConfigurableBeanFactory.SCOPE_PROTOTYPE)
    fun handle(jdbi: Jdbi): Handle {
        return jdbi.open().begin()
    }
}