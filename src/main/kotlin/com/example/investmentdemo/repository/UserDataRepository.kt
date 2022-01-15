package com.example.investmentdemo.repository

import com.example.investmentdemo.entity.UserData
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface UserDataRepository : JpaRepository<UserData, Long>