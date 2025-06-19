package io.df.henry.springmultidbtest.event

import io.df.henry.springmultidbtest.service.CampaignBudgetBalanceService
import org.springframework.stereotype.Component
import org.springframework.transaction.event.TransactionPhase
import org.springframework.transaction.event.TransactionalEventListener

@Component
class CampaignEventListener(
    private val balanceService: CampaignBudgetBalanceService
) {
    @TransactionalEventListener(phase = TransactionPhase.AFTER_COMMIT)
    fun handle(e: CampaignSavedEvent) {
//        balanceService.createOrUpdate(e.campaignId, e.initialBudget)
    }
}