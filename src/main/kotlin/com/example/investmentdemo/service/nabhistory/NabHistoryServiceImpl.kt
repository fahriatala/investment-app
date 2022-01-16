package com.example.investmentdemo.service.nabhistory

import com.example.investmentdemo.model.request.UpdateNabRequest
import com.example.investmentdemo.repository.UserDataRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import java.lang.RuntimeException

@Service
class NabHistoryServiceImpl @Autowired constructor(
        private val userDataRepository: UserDataRepository
) : NabHistoryService {

    override fun updateNabHistory(request: UpdateNabRequest): Map<String, Double> {
        val totalUser = userDataRepository.findAll()

        val assetValue = when (totalUser.isEmpty()) {
            true -> 1.0
            else -> {
                if (request.currentBalance == null) {
                    throw RuntimeException("current balance cannot be empty")
                }
                request.currentBalance
            }
        }

        return mapOf("nab_amount" to assetValue)
    }
}