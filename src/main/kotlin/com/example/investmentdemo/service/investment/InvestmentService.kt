package com.example.investmentdemo.service.investment

import com.example.investmentdemo.model.request.MemberListRequest
import com.example.investmentdemo.model.request.TopUpRequest
import com.example.investmentdemo.model.request.WithdrawRequest
import com.example.investmentdemo.model.response.TopUpResponse
import com.example.investmentdemo.model.response.WithdrawResponse

interface InvestmentService {

    fun doUserTopUp(request: TopUpRequest): TopUpResponse

    fun doUserWithdraw(request: WithdrawRequest): WithdrawResponse

    fun getMemberList(request: MemberListRequest): Map<String, Any>
}