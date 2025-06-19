package io.df.henry.springmultidbtest.service

import io.df.henry.springmultidbtest.domain.a.Campaign
import io.df.henry.springmultidbtest.event.CampaignSavedEvent
import io.df.henry.springmultidbtest.repository.a.CampaignRepository
import org.springframework.context.ApplicationEventPublisher
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class CampaignService(
    private val repo: CampaignRepository,
    private val publisher: ApplicationEventPublisher
) {
    @Transactional("txManagerA")
    fun save(campaign: Campaign) {
        val saved = repo.save(campaign)
        publisher.publishEvent(CampaignSavedEvent(saved.id!!, saved.initialBudget))
    }
}
