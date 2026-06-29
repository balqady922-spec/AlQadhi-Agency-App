package com.example.myapp.data

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "employees")
data class EmployeeEntity(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val name: String,
    val dailyWage: Double,
    val dateJoined: Long = System.currentTimeMillis()
)
