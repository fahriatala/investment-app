package com.example.investmentdemo.service

import com.example.investmentdemo.model.request.UserDataRequest
import com.example.investmentdemo.model.response.UserDataResponse

interface UserService {
    fun addUser(request: UserDataRequest): UserDataResponse
}