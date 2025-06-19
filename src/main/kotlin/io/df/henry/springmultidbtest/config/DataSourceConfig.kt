package io.df.henry.springmultidbtest.config

import com.zaxxer.hikari.HikariConfig
import com.zaxxer.hikari.HikariDataSource
import org.springframework.beans.factory.annotation.Qualifier
import org.springframework.boot.context.properties.ConfigurationProperties
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.context.annotation.Primary
import org.springframework.orm.jpa.JpaTransactionManager
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter
import org.springframework.transaction.PlatformTransactionManager

@Configuration
class DataSourceConfig {

    // A용 HikariConfig  — Primary
    @Bean
    @Primary
    @ConfigurationProperties("app.datasource.db-a")
    fun hikariConfigA(): HikariConfig = HikariConfig()

    // B용 HikariConfig
    @Bean
    @ConfigurationProperties("app.datasource.db-b")
    fun hikariConfigB(): HikariConfig = HikariConfig()

    // A용 DataSource (Primary)
    @Bean
    @Primary
    fun dataSourceA(
        @Qualifier("hikariConfigA") cfg: HikariConfig
    ): HikariDataSource = HikariDataSource(cfg)

    // B용 DataSource
    @Bean
    fun dataSourceB(
        @Qualifier("hikariConfigB") cfg: HikariConfig
    ): HikariDataSource = HikariDataSource(cfg)

    // A용 EMF
    @Bean
    @Primary
    fun emfA(@Qualifier("dataSourceA") dataSourceA: HikariDataSource): LocalContainerEntityManagerFactoryBean =
        LocalContainerEntityManagerFactoryBean().apply {
            dataSource = dataSourceA
            setPackagesToScan("io.df.henry.springmultidbtest.domain.a")
            persistenceUnitName = "puA"
            jpaVendorAdapter = HibernateJpaVendorAdapter().apply {
                setShowSql(true)
                setDatabasePlatform("org.hibernate.dialect.MySQLDialect")
            }
        }

    // B용 EMF
    @Bean
    fun emfB(@Qualifier("dataSourceB") dataSourceB: HikariDataSource): LocalContainerEntityManagerFactoryBean =
        LocalContainerEntityManagerFactoryBean().apply {
            println("jdbcURL ============= " + (dataSourceB as HikariDataSource).jdbcUrl)
            dataSource = dataSourceB
            setPackagesToScan("io.df.henry.springmultidbtest.domain.b")
            persistenceUnitName = "puB"
            jpaVendorAdapter = HibernateJpaVendorAdapter().apply {
                setShowSql(true)
                setDatabasePlatform("org.hibernate.dialect.PostgreSQLDialect")
            }
        }

    // A용 TxMgr
    @Bean
    @Primary
    fun txManagerA(emfA: LocalContainerEntityManagerFactoryBean): PlatformTransactionManager =
        JpaTransactionManager(emfA.`object`!!)

    // B용 TxMgr
    @Bean
    fun txManagerB(@Qualifier("emfB") emfB: LocalContainerEntityManagerFactoryBean): PlatformTransactionManager =
        JpaTransactionManager(emfB.`object`!!)
}
