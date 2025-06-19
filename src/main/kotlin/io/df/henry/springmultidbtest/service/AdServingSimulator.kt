package io.df.henry.springmultidbtest.service

import org.springframework.scheduling.annotation.Scheduled
import org.springframework.stereotype.Component
import java.math.BigDecimal

@Component
class AdServingSimulator(
    private val balanceService: CampaignBudgetBalanceService
) {
    @Scheduled(fixedDelay = 500)
    fun simulate() {
        balanceService.decrease(1L, BigDecimal("0.01"))
        println("Served 0.01")
    }
}
