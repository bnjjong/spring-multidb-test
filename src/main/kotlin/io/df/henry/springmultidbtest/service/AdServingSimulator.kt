package io.df.henry.springmultidbtest.service

import org.springframework.scheduling.annotation.Scheduled
import org.springframework.stereotype.Component
import java.math.BigDecimal
import kotlin.random.Random

@Component
class AdServingSimulator(
    private val balanceService: CampaignBudgetBalanceService
) {
    @Scheduled(fixedDelay = 50000)
    fun simulate() {
        balanceService.decrease(1L, BigDecimal("0.01"))
        println("Served 1")
    }

    @Scheduled(fixedDelay = 200000)
    fun simulate2() {
        balanceService.createOrUpdate(1L, BigDecimal.valueOf(Random.nextLong(10000)))
        println("update budget valance 1")
    }
}
