package com.example.investmentdemo.service.investment

import com.example.investmentdemo.model.request.TopUpRequest
import com.example.investmentdemo.model.response.TopUpResponse

interface InvestmentService {

    fun doUserTopUp(request: TopUpRequest): TopUpResponse
}