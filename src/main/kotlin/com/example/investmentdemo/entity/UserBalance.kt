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
        val id: Long? = null,

        @Column(name = "user_id")
        var userId: Long? = null,

        @Column(name = "total_unit")
        var totalUnit: Double? = null,

        @Column(name = "total_amount")
        var totalAmount: Double? = null
)