package com.example.investmentdemo.entity

import com.fasterxml.jackson.annotation.JsonFormat
import com.fasterxml.jackson.annotation.JsonIgnore
import org.hibernate.annotations.CreationTimestamp
import org.hibernate.annotations.DynamicInsert
import org.hibernate.annotations.DynamicUpdate
import java.util.*
import javax.persistence.*

@Entity
@Table(name = "nab_history")
@DynamicInsert
@DynamicUpdate
data  class NabHistory(
        @Id
        @Column(name = "id")
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        @JsonIgnore
        val id: Long? = null,

        @Column(name = "nab_amount")
        val nabAmount: Double? = null,

        @Column(name = "created_at")
        @CreationTimestamp
        @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "Asia/Jakarta")
        var createdAt: Date? = null
)