package com.example.investmentdemo.service.nabhistory

import com.example.investmentdemo.model.request.UpdateNabRequest
import com.example.investmentdemo.repository.UserDataRepository
import org.springframework.beans.factory.annotation.Autowired

class NabHistoryServiceImpl @Autowired constructor(
        private val userDataRepository: UserDataRepository
) : NabHistoryService {

    override fun updateNabHistory(request: UpdateNabRequest): Map<String, Double> {
        val totalUser = userDataRepository.findAll()

        val nilaiActivaBersih = when (totalUser.isEmpty()) {
            true -> 0.0
            else -> 1.0
        }

        return mapOf("nab_amount" to nilaiActivaBersih)
    }
}