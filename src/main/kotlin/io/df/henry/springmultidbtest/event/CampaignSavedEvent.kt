package io.df.henry.springmultidbtest.event

import java.math.BigDecimal

data class CampaignSavedEvent(
    val campaignId: Long,
    val initialBudget: BigDecimal
)