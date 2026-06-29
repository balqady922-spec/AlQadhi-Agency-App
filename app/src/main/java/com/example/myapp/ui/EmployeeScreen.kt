package com.example.myapp.ui

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.myapp.data.EmployeeEntity

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun EmployeeScreen(viewModel: AppViewModel, onEmployeeClick: (EmployeeEntity) -> Unit) {
    var name by remember { mutableStateOf("") }
    var wage by remember { mutableStateOf("") }

    // جلب البيانات عند فتح الشاشة
    LaunchedEffect(Unit) {
        viewModel.loadEmployees()
    }

    Scaffold(
        topBar = { TopAppBar(title = { Text("إدارة الموظفين والعمال") }) }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .padding(paddingValues)
                .padding(16.dp)
                .fillMaxSize()
        ) {
            // حقول إدخال موظف جديد
            OutlinedTextField(
                value = name,
                onValueChange = { name = it },
                label = { Text("اسم الموظف") },
                modifier = Modifier.fillMaxWidth()
            )
            Spacer(modifier = Modifier.height(8.dp))
            OutlinedTextField(
                value = wage,
                onValueChange = { wage = it },
                label = { Text("الأجر اليومي") },
                modifier = Modifier.fillMaxWidth()
            )
            Spacer(modifier = Modifier.height(16.dp))
            Button(
                onClick = {
                    if (name.isNotBlank() && wage.isNotBlank()) {
                        viewModel.addEmployee(name, wage.toDoubleOrNull() ?: 0.0)
                        name = ""
                        wage = ""
                    }
                },
                modifier = Modifier.fillMaxWidth()
            ) {
                Text("إضافة موظف جديد")
            }

            Spacer(modifier = Modifier.height(24.dp))

            // قائمة عرض الموظفين
            LazyColumn(modifier = Modifier.fillMaxWidth()) {
                items(viewModel.employeesList.value) { employee ->
                    Card(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(vertical = 4.dp),
                        onClick = { onEmployeeClick(employee) }
                    ) {
                        Row(
                            modifier = Modifier
                                .padding(16.dp)
                                .fillMaxWidth(),
                            horizontalArrangement = Arrangement.SpaceBetween
                        ) {
                            Column {
                                Text(text = employee.name, style = MaterialTheme.typography.titleMedium)
                                Text(text = "الأجر: ${employee.dailyWage} د.ع", style = MaterialTheme.typography.bodyMedium)
                            }
                            IconButton(onClick = { viewModel.removeEmployee(employee) }) {
                                Text("حذف")
                            }
                        }
                    }
                }
            }
        }
    }
}
