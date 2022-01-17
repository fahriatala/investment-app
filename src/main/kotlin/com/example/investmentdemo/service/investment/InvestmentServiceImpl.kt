package com.example.investmentdemo.service.investment

import com.example.investmentdemo.entity.TopUpList
import com.example.investmentdemo.entity.UserBalance
import com.example.investmentdemo.entity.WithdrawList
import com.example.investmentdemo.model.request.MemberListRequest
import com.example.investmentdemo.model.request.TopUpRequest
import com.example.investmentdemo.model.request.WithdrawRequest
import com.example.investmentdemo.model.response.MemberResponse
import com.example.investmentdemo.model.response.PaginationResponse
import com.example.investmentdemo.model.response.TopUpResponse
import com.example.investmentdemo.model.response.WithdrawResponse
import com.example.investmentdemo.repository.*
import com.example.investmentdemo.roundOffDecimal
import com.example.investmentdemo.toCurrencyFormat
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.data.domain.PageRequest
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

    private val userNotFoundText = "user id not found"

    override fun doUserTopUp(request: TopUpRequest): TopUpResponse {
        when {
            request.userId == null -> throw RuntimeException("user id cannot be null")
            request.amountInRupiah == null || request.amountInRupiah < 0.0 -> throw RuntimeException("invalid amount")
        }

        val userId = request.userId ?: 0
        val amountInRupiah = request.amountInRupiah ?: 0.0

        userDataRepository.findById(userId).orElseThrow { throw RuntimeException(userNotFoundText) }
        val userBalance = userBalanceRepository.findFirstByUserIdOrderByIdDesc(userId)
                ?: UserBalance()

        val currentUnit = userBalance.totalUnit ?: 0.0
        val currentAmount = userBalance.totalAmount ?: 0.0

        val assetValue = nabHistoryRepository.findTopByOrderByIdDesc()?.nabAmount ?: 1.0
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
                userBalance.apply {
                    this.userId = userId
                    this.totalUnit = totalUnit
                    this.totalAmount = totalAmount
                }
        )

        return TopUpResponse(
                unit = unit,
                totalUnit = totalUnit,
                amount = totalAmount.toCurrencyFormat()
        )
    }

    override fun doUserWithdraw(request: WithdrawRequest): WithdrawResponse {
        when {
            request.userId == null -> throw RuntimeException("user id cannot be null")
            request.amountInRupiah == null || request.amountInRupiah < 0.0 -> throw RuntimeException("invalid amount")
        }

        val userId = request.userId ?: 0
        val amountInRupiah = request.amountInRupiah ?: 0.0

        userDataRepository.findById(userId).orElseThrow { throw RuntimeException(userNotFoundText) }
        val userBalance = userBalanceRepository.findFirstByUserIdOrderByIdDesc(userId)
                ?: throw RuntimeException("user balance not found")

        val currentUnit = userBalance.totalUnit ?: 0.0
        val currentAmount = userBalance.totalAmount ?: 0.0

        if (amountInRupiah > currentAmount) {
            throw RuntimeException("request amount bigger than current amount")
        }

        val assetValue = nabHistoryRepository.findTopByOrderByIdDesc()?.nabAmount ?: 1.0
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
                userBalance.apply {
                    this.userId = userId
                    this.totalUnit = totalUnit
                    this.totalAmount = totalAmount
                }
        )

        return WithdrawResponse(
                unit = unit,
                totalUnit = totalUnit,
                amount = totalAmount.toCurrencyFormat()
        )
    }

    override fun getMemberList(request: MemberListRequest): Map<String, Any> {
        request.userId?.let {
            userDataRepository.findById(it).orElseThrow { throw RuntimeException(userNotFoundText) }
        }

        val  pageable = PageRequest.of(request.page - 1, request.limit)
        val memberList = when (request.userId) {
            null -> userBalanceRepository.findAll(pageable)
            else -> userBalanceRepository.findByUserId(request.userId, pageable)
        }

        val data = memberList.mapNotNull {
            MemberResponse(
                    userId = it.userId,
                    totalUnit = it.totalUnit,
                    totalAmountInRupiah = it.totalAmount.toCurrencyFormat()
            )
        }

        val pagination = PaginationResponse(
                currentPage = request.page,
                totalPage = memberList.totalPages,
                totalData = memberList.totalElements,
                pagingData = data
        )

        val currentAssetValue = nabHistoryRepository.findTopByOrderByIdDesc()?.nabAmount ?: 1.0

        return mapOf(
                "pagination" to pagination,
                "current_nab" to currentAssetValue
        )
    }
}