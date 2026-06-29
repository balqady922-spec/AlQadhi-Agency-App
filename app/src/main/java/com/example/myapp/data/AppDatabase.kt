package com.example.myapp.data

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [EmployeeEntity::class, TransactionEntity::class], version = 1, exportSchema = false)
abstract class AppDatabase : RoomDatabase() {
    abstract fun employeeDao(): EmployeeDao
    abstract fun transactionDao(): TransactionDao
}
