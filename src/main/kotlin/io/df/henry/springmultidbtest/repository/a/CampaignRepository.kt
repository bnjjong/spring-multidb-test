package io.df.henry.springmultidbtest.repository.a

import io.df.henry.springmultidbtest.domain.a.Campaign
import org.springframework.data.jpa.repository.JpaRepository

interface CampaignRepository : JpaRepository<Campaign, Long>