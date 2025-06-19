package io.df.henry.springmultidbtest.config

import org.springframework.boot.context.properties.ConfigurationProperties
import org.springframework.boot.jdbc.DataSourceBuilder
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.context.annotation.Primary
import org.springframework.jdbc.datasource.DataSourceTransactionManager
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter
import org.springframework.transaction.PlatformTransactionManager
import org.springframework.transaction.annotation.EnableTransactionManagement
import javax.sql.DataSource

@Configuration
@EnableTransactionManagement
class DataSourceConfig {

    @Bean
    @Primary
    @ConfigurationProperties(prefix = "app.datasource.db-a")
    fun dataSourceA(): DataSource = DataSourceBuilder.create().build()

    @Bean
    @Primary
    fun emfA(dataSourceA: DataSource): LocalContainerEntityManagerFactoryBean =
        LocalContainerEntityManagerFactoryBean().apply {
            dataSource = dataSourceA
            setPackagesToScan("io.df.henry.springmultidbtest.domain.a")
            jpaVendorAdapter = HibernateJpaVendorAdapter()
        }

    @Bean
    @Primary
    fun txManagerA(emfA: LocalContainerEntityManagerFactoryBean): PlatformTransactionManager =
        DataSourceTransactionManager(emfA.`object`!!)

    @Bean
    @ConfigurationProperties(prefix = "app.datasource.db-b")
    fun dataSourceB(): DataSource = DataSourceBuilder.create().build()

    @Bean
    fun emfB(dataSourceB: DataSource): LocalContainerEntityManagerFactoryBean =
        LocalContainerEntityManagerFactoryBean().apply {
            dataSource = dataSourceB
            setPackagesToScan("io.df.henry.springmultidbtest.domain.b")
            jpaVendorAdapter = HibernateJpaVendorAdapter()
        }

    @Bean
    fun txManagerB(emfB: LocalContainerEntityManagerFactoryBean): PlatformTransactionManager =
        DataSourceTransactionManager(emfB.`object`!!)
}
