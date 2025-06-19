package io.df.henry.springmultidbtest.service

import java.math.BigDecimal

interface CampaignBudgetBalanceService {
    fun createOrUpdateNewTx(id: Long, budget: BigDecimal)
    fun createOrUpdate(id: Long, budget: BigDecimal)
    fun decreaseBudget(id: Long, amount: BigDecimal)
}