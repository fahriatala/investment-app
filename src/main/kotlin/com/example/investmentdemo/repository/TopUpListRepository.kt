package com.example.investmentdemo.repository

import com.example.investmentdemo.entity.TopUpList
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface TopUpListRepository : JpaRepository<TopUpList, Long>