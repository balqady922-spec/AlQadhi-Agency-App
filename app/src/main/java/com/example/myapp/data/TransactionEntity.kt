package com.example.myapp.data

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "transactions")
data class TransactionEntity(
    @PrimaryKey(autoGenerate = true) val transactionId: Int = 0,
    val employeeId: Int,
    val amount: Double,
    val transactionDate: Long = System.currentTimeMillis(),
    val notes: String = ""
)
