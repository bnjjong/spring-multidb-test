package io.df.henry.springmultidbtest.repository.b

import io.df.henry.springmultidbtest.domain.b.CampaignBudgetBalance
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Lock
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.query.Param
import java.util.Optional
import jakarta.persistence.LockModeType

interface CampaignBudgetBalanceRepository : JpaRepository<CampaignBudgetBalance, Long> {
    fun findByCampaignId(id: Long): Optional<CampaignBudgetBalance>

    @Lock(LockModeType.PESSIMISTIC_WRITE)
    @Query("SELECT c FROM CampaignBudgetBalance c WHERE c.campaignId = :id")
    fun findByCampaignIdForUpdate(@Param("id") id: Long): Optional<CampaignBudgetBalance>
}
