package com.example.investmentdemo.service

import com.example.investmentdemo.entity.UserData
import com.example.investmentdemo.model.request.UserDataRequest
import com.example.investmentdemo.model.response.UserDataResponse
import com.example.investmentdemo.repository.UserDataRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
class UserServiceImpl @Autowired constructor(
        private val userDataRepository: UserDataRepository
) : UserService {

    override fun addUser(request: UserDataRequest): UserDataResponse {
       val userData = userDataRepository.saveAndFlush(
               UserData(
                       userName = request.userName,
                       name = request.name
               )
       )

        return UserDataResponse(userData.id, userData.name)
    }
}