package com.example.investmentdemo.controller

import com.example.investmentdemo.model.DataResponse
import org.springframework.http.HttpStatus
import org.springframework.http.MediaType
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping(value = ["/user"], produces = [MediaType.APPLICATION_JSON_VALUE])
class UserController {

    @PostMapping
    fun addUser(): DataResponse<Boolean> = DataResponse(
            code = HttpStatus.OK.value(),
            status = HttpStatus.OK.reasonPhrase,
            data = true
    )
}