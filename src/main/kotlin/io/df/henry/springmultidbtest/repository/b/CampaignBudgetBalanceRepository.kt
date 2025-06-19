package io.df.henry.springmultidbtest.repository.b

import io.df.henry.springmultidbtest.domain.b.CampaignBudgetBalance
import jakarta.persistence.LockModeType
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Lock
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.query.Param
import java.util.*

interface CampaignBudgetBalanceRepository : JpaRepository<CampaignBudgetBalance, Long> {

    fun findByCampaignId(campaignId: Long): Optional<CampaignBudgetBalance>

    @Lock(LockModeType.PESSIMISTIC_WRITE)
    @Query("SELECT c FROM CampaignBudgetBalance c WHERE c.campaignId = :campaignId")
    fun findByCampaignIdForUpdate(@Param("campaignId") id: Long): Optional<CampaignBudgetBalance>


}
