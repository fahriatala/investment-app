package com.example.investmentdemo.entity

import com.fasterxml.jackson.annotation.JsonFormat
import org.hibernate.annotations.CreationTimestamp
import org.hibernate.annotations.DynamicInsert
import org.hibernate.annotations.DynamicUpdate
import java.util.*
import javax.persistence.*

@Entity
@Table(name = "withdraw_list")
@DynamicInsert
@DynamicUpdate
data class WithdrawList(
        @Id
        @Column(name = "id")
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        val id: Long? = null,

        @Column(name = "user_id")
        val userId: Long? = null,

        @Column(name = "total_unit")
        val totalUnit: Double? = null,

        @Column(name = "total_amount")
        val totalAmount: Double? = null,

        @Column(name = "created_at")
        @CreationTimestamp
        @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "Asia/Jakarta")
        var createdAt: Date? = null
)