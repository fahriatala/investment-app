package com.example.investmentdemo.service.nabhistory

import com.example.investmentdemo.model.request.UpdateNabRequest
import com.example.investmentdemo.repository.TopUpListRepository
import com.example.investmentdemo.repository.UserDataRepository
import com.example.investmentdemo.repository.WithdrawListRepository
import com.example.investmentdemo.roundOffDecimal
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import java.lang.RuntimeException

@Service
class NabHistoryServiceImpl @Autowired constructor(
        private val userDataRepository: UserDataRepository,
        private val topUpListRepository: TopUpListRepository,
        private val withdrawListRepository: WithdrawListRepository
) : NabHistoryService {

    override fun updateNabHistory(request: UpdateNabRequest): Map<String, Double> {
        val totalUser = userDataRepository.findAll()

        val assetValue = when (totalUser.isEmpty()) {
            true -> 1.0
            else -> {
                if (request.currentBalance == null) {
                    throw RuntimeException("current balance cannot be empty")
                }

                val totalTopUp = topUpListRepository.findAll().sumByDouble { it.totalUnit ?: 0.0 }
                val totalWithdraw = withdrawListRepository.findAll().sumByDouble { it.totalUnit ?: 0.0 }

                if (totalWithdraw > totalTopUp) {
                    throw RuntimeException("invalid balance")
                }

                val totalUnit = totalTopUp - totalWithdraw
                (request.currentBalance/totalUnit).roundOffDecimal()
            }
        }

        return mapOf("nab_amount" to assetValue)
    }
}