package io.df.henry.springmultidbtest.config


//@Configuration
//@EnableJpaRepositories(
//    basePackages = [
//        "io.df.henry.springmultidbtest.repository.a"],
//    entityManagerFactoryRef = "entityManagerFactoryA",
//    transactionManagerRef = "transactionManager"
//)
class XAConfigA(
) {

//    @Bean
//    @Primary
//    @ConfigurationProperties("spring.jta.atomikos.datasource.db-a")
//    fun xaDataSourceA(): AtomikosDataSourceBean = AtomikosDataSourceBean().apply {
//        // Unique name for Atomikos
//        uniqueResourceName = "db-a"
//    }
//
//    @Bean(name = ["entityManagerFactoryA"])
//    @Primary
//    fun emfA(xaDataSourceA: AtomikosDataSourceBean): LocalContainerEntityManagerFactoryBean =
//        LocalContainerEntityManagerFactoryBean().apply {
//            setDataSource(xaDataSourceA)
//            setPackagesToScan("io.df.henry.springmultidbtest.domain.a")
//            setJtaDataSource(xaDataSourceA)
//            jpaVendorAdapter = HibernateJpaVendorAdapter().apply {
//                setDatabasePlatform("org.hibernate.dialect.MySQL8Dialect")
//                setShowSql(true)
//            }
//        }
}


//@Configuration
//@EnableJpaRepositories(
//    basePackages = [
//        "io.df.henry.springmultidbtest.repository.b"],
//    entityManagerFactoryRef = "entityManagerFactoryB",
//    transactionManagerRef = "transactionManager"
//)
//class XAConfigB(
//) {
//    @Bean
//    @ConfigurationProperties("spring.jta.atomikos.datasource.db-b")
//    fun xaDataSourceB(): AtomikosDataSourceBean = AtomikosDataSourceBean().apply {
//        // Unique name for Atomikos
//        uniqueResourceName = "db-b"
//    }
//
//    @Bean(name = ["entityManagerFactoryB"])
//    fun emfB(xaDataSourceB: AtomikosDataSourceBean): LocalContainerEntityManagerFactoryBean =
//        LocalContainerEntityManagerFactoryBean().apply {
//            setDataSource(xaDataSourceB)
//            setPackagesToScan("io.df.henry.springmultidbtest.domain.b")
//            setJtaDataSource(xaDataSourceB)
//            jpaVendorAdapter = HibernateJpaVendorAdapter().apply {
//                setDatabasePlatform("org.hibernate.dialect.PostgreSQLDialect")
//                setShowSql(true)
//            }
//        }
//}