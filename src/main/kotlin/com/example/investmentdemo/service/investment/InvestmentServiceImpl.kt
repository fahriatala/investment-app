package com.example.investmentdemo.service.investment

import com.example.investmentdemo.entity.TopUpList
import com.example.investmentdemo.entity.UserBalance
import com.example.investmentdemo.entity.WithdrawList
import com.example.investmentdemo.model.request.TopUpRequest
import com.example.investmentdemo.model.request.WithdrawRequest
import com.example.investmentdemo.model.response.TopUpResponse
import com.example.investmentdemo.model.response.WithdrawResponse
import com.example.investmentdemo.repository.*
import com.example.investmentdemo.roundOffDecimal
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import java.lang.RuntimeException

@Service
class InvestmentServiceImpl @Autowired constructor(
        private val userDataRepository: UserDataRepository,
        private val userBalanceRepository: UserBalanceRepository,
        private val nabHistoryRepository: NabHistoryRepository,
        private val topUpListRepository: TopUpListRepository,
        private val withdrawListRepository: WithdrawListRepository
) : InvestmentService {

    override fun doUserTopUp(request: TopUpRequest): TopUpResponse {
        when {
            request.userId == null -> throw RuntimeException("user id cannot be null")
            request.amountInRupiah == null || request.amountInRupiah < 0.0 -> throw RuntimeException("invalid amount")
        }

        val userId = request.userId ?: 0
        val amountInRupiah = request.amountInRupiah ?: 0.0

        userDataRepository.findById(userId).orElseThrow { throw RuntimeException("user id not found") }
        val userBalance = userBalanceRepository.findByUserIdOrderByIdDesc(userId)
                ?: throw RuntimeException("user balance not found")

        val currentUnit = userBalance.totalUnit ?: 0.0
        val currentAmount = userBalance.totalAmount ?: 0.0

        val assetValue = nabHistoryRepository.findTopByOrderByIdDesc()?.nabAmount ?: 0.0
        val unit = (amountInRupiah / assetValue).roundOffDecimal()

        val totalUnit = currentUnit.plus(unit)
        val totalAmount = currentAmount.plus(amountInRupiah)

        topUpListRepository.saveAndFlush(
                TopUpList(
                        userId = request.userId,
                        totalUnit = unit,
                        totalAmount = amountInRupiah
                )
        )

        userBalanceRepository.saveAndFlush(
                UserBalance(
                        userId = userId,
                        totalUnit = totalUnit,
                        totalAmount = totalAmount
                )
        )

        return TopUpResponse(
                unit = unit,
                totalUnit = totalUnit,
                amount = totalAmount
        )
    }

    override fun doUserWithdraw(request: WithdrawRequest): WithdrawResponse {
        when {
            request.userId == null -> throw RuntimeException("user id cannot be null")
            request.amountInRupiah == null || request.amountInRupiah < 0.0 -> throw RuntimeException("invalid amount")
        }

        val userId = request.userId ?: 0
        val amountInRupiah = request.amountInRupiah ?: 0.0

        userDataRepository.findById(userId).orElseThrow { throw RuntimeException("user id not found") }
        val userBalance = userBalanceRepository.findByUserIdOrderByIdDesc(userId)
                ?: throw RuntimeException("user balance not found")

        val currentUnit = userBalance.totalUnit ?: 0.0
        val currentAmount = userBalance.totalAmount ?: 0.0

        if (amountInRupiah > currentAmount) {
            throw RuntimeException("request amount bigger than current amount")
        }

        val assetValue = nabHistoryRepository.findTopByOrderByIdDesc()?.nabAmount ?: 0.0
        val unit = (amountInRupiah / assetValue).roundOffDecimal()

        val totalUnit = currentUnit.minus(unit)
        val totalAmount = currentAmount.minus(amountInRupiah)

        withdrawListRepository.saveAndFlush(
                WithdrawList(
                        userId = request.userId,
                        totalUnit = unit,
                        totalAmount = amountInRupiah
                )
        )

        userBalanceRepository.saveAndFlush(
                UserBalance(
                        userId = userId,
                        totalUnit = totalUnit,
                        totalAmount = totalAmount
                )
        )

        return WithdrawResponse(
                unit = unit,
                totalUnit = totalUnit,
                amount = totalAmount
        )
    }
}