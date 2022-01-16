package com.example.investmentdemo.model.response

import com.fasterxml.jackson.annotation.JsonProperty

data class WithdrawResponse(
        @JsonProperty("nilai_unit_hasil_withdraw")
        val unit: Double,

        @JsonProperty("nilai_unit_total")
        val totalUnit: Double,

        @JsonProperty("saldo_rupiah_total")
        val amount: String
)