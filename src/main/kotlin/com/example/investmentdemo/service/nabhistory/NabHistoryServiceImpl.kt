package com.example.investmentdemo.service.nabhistory

import com.example.investmentdemo.convert
import com.example.investmentdemo.entity.NabHistory
import com.example.investmentdemo.model.request.UpdateNabRequest
import com.example.investmentdemo.model.response.NabHistoryResponse
import com.example.investmentdemo.repository.*
import com.example.investmentdemo.roundOffDecimal
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import java.lang.RuntimeException

@Service
class NabHistoryServiceImpl @Autowired constructor(
        private val userDataRepository: UserDataRepository,
        private val userBalanceRepository: UserBalanceRepository,
        private val nabHistoryRepository: NabHistoryRepository
) : NabHistoryService {

    override fun updateNabHistory(request: UpdateNabRequest): Map<String, Double> {
        val totalUser = userDataRepository.findAll()

        val assetValue = when (totalUser.isEmpty()) {
            true -> 1.0
            else -> {
                if (request.currentBalance == null) {
                    throw RuntimeException("current balance cannot be empty")
                }

               countNabHistory(request.currentBalance)
            }
        }

        nabHistoryRepository.saveAndFlush(
                NabHistory(nabAmount = assetValue)
        )

        return mapOf("nab_amount" to assetValue)
    }

    override fun getNabList(): List<NabHistoryResponse> = nabHistoryRepository
            .findAll()
            .map {
                NabHistoryResponse(it.nabAmount, it.createdAt.convert())
            }

    private fun countNabHistory(currentBalance: Double): Double =
            when (val totalUnit = userBalanceRepository.findAll().sumByDouble { it.totalUnit ?: 0.0 }) {
                0.0 -> 1.0
                else -> (currentBalance/totalUnit).roundOffDecimal()
            }
}