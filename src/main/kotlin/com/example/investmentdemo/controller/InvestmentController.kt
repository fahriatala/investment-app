package com.example.investmentdemo.controller

import com.example.investmentdemo.model.request.TopUpRequest
import com.example.investmentdemo.model.request.UpdateNabRequest
import com.example.investmentdemo.model.request.WithdrawRequest
import com.example.investmentdemo.model.response.BaseResponse
import com.example.investmentdemo.service.investment.InvestmentService
import com.example.investmentdemo.service.nabhistory.NabHistoryService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.http.MediaType
import org.springframework.web.bind.annotation.*
import javax.validation.Valid

@RestController
@RequestMapping(value = ["/v1/ib"], produces = [MediaType.APPLICATION_JSON_VALUE])
class InvestmentController @Autowired constructor(
        private val nabHistoryService: NabHistoryService,
        private val investmentService: InvestmentService
) {

    @PostMapping(value = ["updateTotalBalance"])
    fun updateNabHistory(@RequestBody request: UpdateNabRequest): BaseResponse<Any> {
        val result = nabHistoryService.updateNabHistory(request)
        return BaseResponse(
                code = HttpStatus.OK.value(),
                status = HttpStatus.OK.reasonPhrase,
                data = result
        )
    }

    @GetMapping(value = ["listNAB"])
    fun getNabList(): BaseResponse<Any> {
        val result = nabHistoryService.getNabList()
        return BaseResponse(
                code = HttpStatus.OK.value(),
                status = HttpStatus.OK.reasonPhrase,
                data = result
        )
    }

    @PostMapping(value = ["topup"])
    fun doUserTopUp(@RequestBody request: TopUpRequest): BaseResponse<Any> {
        val result = investmentService.doUserTopUp(request)
        return BaseResponse(
                code = HttpStatus.CREATED.value(),
                status = HttpStatus.CREATED.reasonPhrase,
                data = result
        )
    }

    @PostMapping(value = ["withdraw"])
    fun doUserWithdraw(@RequestBody request: WithdrawRequest): BaseResponse<Any> {
        val result = investmentService.doUserWithdraw(request)
        return BaseResponse(
                code = HttpStatus.CREATED.value(),
                status = HttpStatus.CREATED.reasonPhrase,
                data = result
        )
    }
}