package com.example.investmentdemo.service.nabhistory

import com.example.investmentdemo.model.request.UpdateNabRequest

interface NabHistoryService {

    fun updateNabHistory(request: UpdateNabRequest): Map<String, Double>
}