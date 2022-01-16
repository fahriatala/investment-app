package com.example.investmentdemo.service.nabhistory

import com.example.investmentdemo.entity.NabHistory
import com.example.investmentdemo.model.request.UpdateNabRequest
import com.example.investmentdemo.model.response.NabHistoryResponse

interface NabHistoryService {

    fun updateNabHistory(request: UpdateNabRequest): Map<String, Double>

    fun getNabList(): List<NabHistoryResponse> = emptyList()
}