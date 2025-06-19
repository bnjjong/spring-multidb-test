package io.df.henry.springmultidbtest.config

import org.springframework.context.annotation.Configuration
import org.springframework.data.jpa.repository.config.EnableJpaRepositories

@Configuration
@EnableJpaRepositories(
    basePackages = ["io.df.henry.springmultidbtest.repository.a"],
    entityManagerFactoryRef = "emfA",
    transactionManagerRef = "txManagerA"
)
class RepoConfigA
