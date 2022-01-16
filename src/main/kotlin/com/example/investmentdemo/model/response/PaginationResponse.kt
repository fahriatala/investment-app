package com.example.investmentdemo.model.response

import com.fasterxml.jackson.annotation.JsonProperty

data class PaginationResponse(
        @JsonProperty(value = "currentPage")
        var currentPage: Int? = 0,

        @JsonProperty(value = "totalPage")
        val totalPage: Int = 0,

        @JsonProperty(value = "totalData")
        val totalData: Long? = 0,

        @JsonProperty(value = "pagingData")
        var pagingData: Any
)