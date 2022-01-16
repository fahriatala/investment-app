package com.example.investmentdemo.controller

import com.example.investmentdemo.model.request.UserDataRequest
import com.example.investmentdemo.model.response.BaseResponse
import com.example.investmentdemo.model.response.UserDataResponse
import com.example.investmentdemo.service.user.UserService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.http.MediaType
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import javax.validation.Valid

@RestController
@RequestMapping(value = ["/user"], produces = [MediaType.APPLICATION_JSON_VALUE])
class UserDataController @Autowired constructor(
        private val userService: UserService
) {

    @PostMapping
    fun addUser(@RequestBody @Valid request: UserDataRequest): BaseResponse<Any> {
        val result = userService.addUser(request)
        return BaseResponse(
                code = HttpStatus.CREATED.value(),
                status = HttpStatus.CREATED.reasonPhrase,
                data = result
        )
    }
}