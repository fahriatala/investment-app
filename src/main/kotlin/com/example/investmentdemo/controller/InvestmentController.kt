package com.example.investmentdemo.controller

import com.example.investmentdemo.model.DataResponse
import org.springframework.http.HttpStatus
import org.springframework.http.MediaType
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping(value = ["/investment"], produces = [MediaType.APPLICATION_JSON_VALUE])
class InvestmentController {

    @GetMapping(value = ["health"])
    fun health(): DataResponse<Boolean> = DataResponse(
            code = HttpStatus.OK.value(),
            status = HttpStatus.OK.reasonPhrase,
            data = true
    )

}