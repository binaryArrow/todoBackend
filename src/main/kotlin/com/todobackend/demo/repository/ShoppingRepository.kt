package com.todobackend.demo.repository

import com.todobackend.demo.dto.ShoppingList
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface ShoppingRepository : JpaRepository<ShoppingList, Long>