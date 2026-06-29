package com.example.myapp.domain

import com.example.myapp.data.*

class AppRepository(private val db: AppDatabase) {

    // عمليات الموظفين
    fun getAllEmployees(): List<EmployeeEntity> {
        return db.employeeDao().getAllEmployees()
    }

    fun insertEmployee(employee: EmployeeEntity) {
        db.employeeDao().insertEmployee(employee)
    }

    fun deleteEmployee(employee: EmployeeEntity) {
        db.employeeDao().deleteEmployee(employee)
    }

    // عمليات المسحوبات المالية
    fun getTransactionsForEmployee(empId: Int): List<TransactionEntity> {
        return db.transactionDao().getTransactionsForEmployee(empId)
    }

    fun insertTransaction(transaction: TransactionEntity) {
        db.transactionDao().insertTransaction(transaction)
    }

    fun deleteTransaction(transaction: TransactionEntity) {
        db.transactionDao().deleteTransaction(transaction)
    }
}
