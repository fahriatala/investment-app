package com.example.investmentdemo.entity

import com.fasterxml.jackson.annotation.JsonFormat
import org.hibernate.annotations.CreationTimestamp
import org.hibernate.annotations.DynamicInsert
import org.hibernate.annotations.DynamicUpdate
import java.util.*
import javax.persistence.*

@Entity
@Table(name = "user_data")
@DynamicInsert
@DynamicUpdate
data class UserData(
        @Id
        @Column(name = "id")
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        val id: Long? = null,

        @Column(name = "user_name")
        val username: String? = null,
         @Column(name = "name")
        val name: String? = null,

        @Column(name = "created_at")
        @CreationTimestamp
        @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "Asia/Jakarta")
        var createdAt: Date? = null
)