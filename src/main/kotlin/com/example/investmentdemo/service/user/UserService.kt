package com.example.investmentdemo.service.user

import com.example.investmentdemo.model.request.UserDataRequest
import com.example.investmentdemo.model.response.UserDataResponse

interface UserService {

    fun addUser(request: UserDataRequest): UserDataResponse
}