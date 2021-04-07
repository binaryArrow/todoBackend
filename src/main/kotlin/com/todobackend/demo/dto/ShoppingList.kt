package com.todobackend.demo.dto

import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.GenerationType
import javax.persistence.Id

@Entity
data class ShoppingList (
        @Id
        @GeneratedValue(strategy = GenerationType.AUTO)
        val id: Long,
        var description: String,
        var bought: Boolean
        )
