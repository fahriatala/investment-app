package com.example.investmentdemo.repository

import com.example.investmentdemo.entity.WithdrawList
import org.springframework.data.jpa.repository.JpaRepository

interface WithdrawListRepository : JpaRepository<WithdrawList, Long>