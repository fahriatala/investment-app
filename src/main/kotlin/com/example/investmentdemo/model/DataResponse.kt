package com.example.investmentdemo.model

data class DataResponse<T>(
        val code: Int,
        val status: String,
        val data: T
)