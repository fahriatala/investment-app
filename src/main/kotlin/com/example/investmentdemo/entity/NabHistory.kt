package com.example.investmentdemo.entity

import com.fasterxml.jackson.annotation.JsonFormat
import org.hibernate.annotations.DynamicInsert
import org.hibernate.annotations.DynamicUpdate
import java.util.*
import javax.persistence.*

@Entity
@Table(name = "user_data")
@DynamicInsert
@DynamicUpdate
data  class NabHistory(
        @Id
        @Column(name = "id")
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        val id: Long? = null,

        @Column(name = "nab_amount")
        @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "Asia/Jakarta")
        val nabAmount: Double? = null,

        @Column(name = "date")
        val date: Date? = null
)