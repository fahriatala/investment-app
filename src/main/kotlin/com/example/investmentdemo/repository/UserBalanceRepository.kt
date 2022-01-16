package com.example.investmentdemo.repository

import com.example.investmentdemo.entity.UserBalance
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface UserBalanceRepository : JpaRepository<UserBalance, Long> {
    fun findByUserIdOrderByIdDesc(userId: Long): UserBalance?
}

