package com.example.investmentdemo.model.response

data class BaseResponse<T>(
        val code: Int,
        val status: String,
        val data: T
)