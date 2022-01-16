package com.example.investmentdemo.service.user

import com.example.investmentdemo.model.request.UserDataRequest

interface UserService {

    fun addUser(request: UserDataRequest): Map<String, Long?>
}