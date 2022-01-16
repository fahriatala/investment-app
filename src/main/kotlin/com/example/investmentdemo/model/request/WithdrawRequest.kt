package com.example.investmentdemo.model.request

import com.fasterxml.jackson.annotation.JsonProperty

data class WithdrawRequest(
        @JsonProperty("user_id")
        val userId: Long? = null,

        @JsonProperty("amount_rupiah")
        val amountRupiah: Double? = null
)