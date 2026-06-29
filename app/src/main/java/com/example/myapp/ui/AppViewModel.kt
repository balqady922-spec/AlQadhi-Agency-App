package com.example.myapp.ui

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.example.myapp.data.*
import com.example.myapp.domain.AppRepository

class AppViewModel(private val repository: AppRepository) : ViewModel() {

    // قائمة الموظفين الحالية في الواجهة
    var employeesList = mutableStateOf<List<EmployeeEntity>>(emptyList())
        private set

    // قائمة المسحوبات للموظف المحدد حالياً
    var currentTransactions = mutableStateOf<List<TransactionEntity>>(emptyList())
        private set

    // جلب الموظفين وتحديث الواجهة
    fun loadEmployees() {
        employeesList.value = repository.getAllEmployees()
    }

    fun addEmployee(name: String, dailyWage: Double) {
        repository.insertEmployee(EmployeeEntity(name = name, dailyWage = dailyWage))
        loadEmployees() // تحديث القائمة فوراً
    }

    fun removeEmployee(employee: EmployeeEntity) {
        repository.deleteEmployee(employee)
        loadEmployees()
    }

    // جلب عمليات موظف محدد
    fun loadTransactions(empId: Int) {
        currentTransactions.value = repository.getTransactionsForEmployee(empId)
    }

    fun addTransaction(empId: Int, amount: Double, notes: String) {
        repository.insertTransaction(TransactionEntity(employeeId = empId, amount = amount, notes = notes))
        loadTransactions(empId) // تحديث العمليات فوراً
    }
}
