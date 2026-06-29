package com.example.myapp.data

import androidx.room.*

@Dao
interface EmployeeDao {
    @Query("SELECT * FROM employees ORDER BY name ASC")
    fun getAllEmployees(): List<EmployeeEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertEmployee(employee: EmployeeEntity)

    @Delete
    fun deleteEmployee(employee: EmployeeEntity)
}
