package com.example.investmentdemo.repository

import com.example.investmentdemo.entity.NabHistory
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface NabHistoryRepository : JpaRepository<NabHistory, Long> {
    fun findTopByOrderByIdDesc(): NabHistory?
}