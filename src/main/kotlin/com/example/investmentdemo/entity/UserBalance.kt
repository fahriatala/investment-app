package com.example.investmentdemo.entity

import com.fasterxml.jackson.annotation.JsonIgnore
import org.hibernate.annotations.DynamicInsert
import org.hibernate.annotations.DynamicUpdate
import javax.persistence.*

@Entity
@Table(name = "user_balance")
@DynamicInsert
@DynamicUpdate
data class UserBalance(
        @Id
        @Column(name = "id")
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        @JsonIgnore
        val id: Long? = null,

        @Column(name = "user_id")
        val userId: Long? = null,

        @Column(name = "total_unit")
        val totalUnit: Double? = null,

        @Column(name = "total_amount")
        val totalAmount: Double? = null
)