package io.df.henry.springmultidbtest.domain.b

import jakarta.persistence.*
import java.math.BigDecimal

@Entity
@Table(name = "campaign_budget_balance")
class CampaignBudgetBalance(



    @Column(name = "campaign_id", nullable = false)
    val campaignId: Long,

    @Column(name = "initial_budget", nullable = false, precision = 19, scale = 4)
    var initialBudget: BigDecimal,

    @Column(name = "balance",        nullable = false, precision = 19, scale = 4)
    var balance: BigDecimal

) {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    var id: Long? = null
}

