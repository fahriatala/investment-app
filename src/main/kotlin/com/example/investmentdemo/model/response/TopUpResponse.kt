package com.example.investmentdemo.model.response

import com.fasterxml.jackson.annotation.JsonProperty

data class TopUpResponse(
        @JsonProperty("nilai_unit_hasil_topup")
        val unit: Double,

        @JsonProperty("nilai_unit_total")
        val totalUnit: Double,

        @JsonProperty("saldo_rupiah_total")
        val amount: Double
)