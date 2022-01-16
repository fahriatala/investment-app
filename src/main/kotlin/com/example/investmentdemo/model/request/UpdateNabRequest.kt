package com.example.investmentdemo.model.request

import com.fasterxml.jackson.annotation.JsonProperty

data class UpdateNabRequest(
        @JsonProperty("current_balance")
        val currentBalance: Double? = null
)