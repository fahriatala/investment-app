package com.example.investmentdemo.repository

import com.example.investmentdemo.entity.UserBalance
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface UserBalanceRepository : JpaRepository<UserBalance, Long> {

    fun findFirstByUserIdOrderByIdDesc(userId: Long): UserBalance?

    fun findByUserId(userId: Long, pageable: Pageable): Page<UserBalance>
}

