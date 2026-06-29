package com.example.myapp.data

import androidx.room.*

@Dao
interface TransactionDao {
    @Query("SELECT * FROM transactions WHERE employeeId = :empId ORDER BY transactionDate DESC")
    fun getTransactionsForEmployee(empId: Int): List<TransactionEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertTransaction(transaction: TransactionEntity)

    @Delete
    fun deleteTransaction(transaction: TransactionEntity)
}
