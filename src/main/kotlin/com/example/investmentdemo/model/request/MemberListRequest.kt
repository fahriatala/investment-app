package com.example.investmentdemo.model.request

data class MemberListRequest(
        val userId: Long? = null,
        val page: Int,
        val limit: Int
)