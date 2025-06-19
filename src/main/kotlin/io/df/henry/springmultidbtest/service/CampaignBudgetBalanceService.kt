package io.df.henry.springmultidbtest.service

import io.df.henry.springmultidbtest.domain.b.CampaignBudgetBalance
import io.df.henry.springmultidbtest.repository.b.CampaignBudgetBalanceRepository
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import java.math.BigDecimal

@Service
class CampaignBudgetBalanceService(
    private val repo: CampaignBudgetBalanceRepository
) {

    /**
     * 새로운 트랜잭션에서 B DB 업데이트
     */
    @Transactional(
        transactionManager = "txManagerB"
    )
    fun createOrUpdate(campaignId: Long, budget: BigDecimal) {
        val bal = repo.findByCampaignIdForUpdate(campaignId)
            .orElseGet { CampaignBudgetBalance(campaignId, BigDecimal.ZERO, BigDecimal.ZERO) }
        bal.initialBudget = budget
        bal.balance       = budget
        Thread.sleep(2000)
        repo.save(bal)
    }

    /**
     * 0.5초마다 호출되는 서빙 로직
     */

    @Transactional(
        transactionManager = "txManagerB"
    )
    fun decrease(campaignId: Long, amount: BigDecimal) {
        val bal = repo.findByCampaignId(campaignId)
            .orElseThrow { IllegalStateException("Balance not found for $campaignId") }
        bal.balance = bal.balance.subtract(amount)
        repo.save(bal)
    }
}

//@Service
//class CampaignBudgetBalanceService(
//    @Qualifier("emfB") // ← 여기 꼭 붙여주세요!
//    private val emfB: EntityManagerFactory,
//    @Qualifier("txManagerB")
//    private val txManager: PlatformTransactionManager
//) {
//    private val txTemplate = TransactionTemplate(txManager).apply {
//        propagationBehavior = TransactionDefinition.PROPAGATION_REQUIRES_NEW
//    }
//
//    fun decrease(campaignId: Long, amount: BigDecimal) {
//        txTemplate.execute {
//            // emfB는 반드시 domain.b 패키지만 스캔한 'puB' 팩토리입니다.
//            val em = emfB.createEntityManager().apply {
//                joinTransaction()
//            }
//            try {
//                // CampaignBudgetBalance가 분명히 매핑되어 있어야 락이 걸립니다
//                val bal = em.find(
//                    io.df.henry.springmultidbtest.domain.b.CampaignBudgetBalance::class.java,
//                    campaignId,
//                    LockModeType.PESSIMISTIC_WRITE
//                ) ?: throw IllegalStateException("Balance not found for campaign $campaignId")
//
//                bal.balance = bal.balance.subtract(amount)
//                em.merge(bal)
//            } finally {
//                em.close()
//            }
//        }
//    }
//}
    /**
     * 최초 예산 생성 또는 업데이트
     * txManagerB 를 사용하고, A 트랜잭션이 커밋된 뒤
     * 별도의 REQUIRES_NEW 로 실행되도록 합니다.
     */
//    @Transactional(
//        transactionManager = "txManagerB"
//    )
//    open fun createOrUpdate(
//        campaignId: Long,
//        budget: BigDecimal
//    ) {
//        val bal = repo.findByCampaignId(campaignId)
//            .orElseGet { CampaignBudgetBalance(campaignId, BigDecimal.ZERO, BigDecimal.ZERO) }
//
//        bal.initialBudget = budget
//        bal.balance = budget
//        repo.save(bal)
//    }
//
//    /**
//     * 서빙 단가 차감 로직
//     * PESSIMISTIC_WRITE 락을 건 후 balance 를 감소시킵니다.
//     */
//
//    @Transactional(
//        transactionManager = "txManagerB"
//    )
//    open fun decrease(
//        campaignId: Long,
//        amount: BigDecimal
//    ) {
//        val bal = repo.findByCampaignIdForUpdate(campaignId)
//            .orElseThrow { IllegalStateException("Balance not found for campaign $campaignId") }
//
//        bal.balance = bal.balance.subtract(amount)
//        repo.save(bal)
//    }
//}
