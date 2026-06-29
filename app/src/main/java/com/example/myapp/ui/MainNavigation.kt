package com.example.myapp.ui

import androidx.compose.runtime.*
import com.example.myapp.data.EmployeeEntity

@Composable
fun MainNavigation(viewModel: AppViewModel) {
    // الاحتفاظ بالموظف المحدد حالياً؛ إذا كان فارغاً نعرض شاشة الموظفين
    var selectedEmployee by remember { mutableStateOf<EmployeeEntity?>(null) }

    if (selectedEmployee == null) {
        EmployeeScreen(
            viewModel = viewModel,
            onEmployeeClick = { employee ->
                selectedEmployee = employee
            }
        )
    } else {
        TransactionScreen(
            viewModel = viewModel,
            employee = selectedEmployee!!,
            onBackClick = {
                selectedEmployee = null
            }
        )
    }
}
