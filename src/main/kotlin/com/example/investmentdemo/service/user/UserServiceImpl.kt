package com.example.investmentdemo.service.user

import com.example.investmentdemo.entity.UserData
import com.example.investmentdemo.model.request.UserDataRequest
import com.example.investmentdemo.repository.UserDataRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import java.lang.RuntimeException

@Service
class UserServiceImpl @Autowired constructor(
        private val userDataRepository: UserDataRepository
) : UserService {

    override fun addUser(request: UserDataRequest): Map<String, Long?> {
        validateCreateUser(request)
        val userData = userDataRepository.saveAndFlush(
                UserData(
                        username = request.username,
                        name = request.name
                )
        )

        return mapOf("id" to userData.id)
    }

    fun validateCreateUser(request: UserDataRequest) {
        val emptyField = when {
            request.name.isNullOrEmpty() -> "name"
            request.username.isNullOrEmpty() -> "user name"
            else -> null
        }

        emptyField?.let {
            throw RuntimeException("$it cannot be empty")
        }

        val isUserNameExist = userDataRepository.existsByUsername(request.username.orEmpty())
        if (isUserNameExist) {
            throw RuntimeException("username is duplicate")
        }
    }
}