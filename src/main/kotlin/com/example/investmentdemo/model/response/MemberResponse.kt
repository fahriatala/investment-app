package com.example.investmentdemo.model.response

import com.fasterxml.jackson.annotation.JsonProperty

data class MemberResponse(
        @JsonProperty("user_id")
        val userId: Long? = null,

        @JsonProperty("total_unit_per_uid")
        val totalUnit: Double? = null,

        @JsonProperty("total_amount_rupiah")
        val totalAmountInRupiah: String? = null
)