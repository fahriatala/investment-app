package com.example.investmentdemo.model.response

import com.fasterxml.jackson.annotation.JsonFormat
import java.util.*

data class NabHistoryResponse(
        val nab: Double? = null,
        val date: String? = null
)