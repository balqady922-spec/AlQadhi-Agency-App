package com.example.myapp.ui

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.myapp.data.EmployeeEntity
import java.text.SimpleDateFormat
import java.util.*

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TransactionScreen(viewModel: AppViewModel, employee: EmployeeEntity, onBackClick: () -> Unit) {
    var amount by remember { mutableStateOf("") }
    var notes by remember { mutableStateOf("") }
    val dateFormatter = remember { SimpleDateFormat("yyyy-MM-dd HH:mm", Locale.getDefault()) }

    // جلب سحبيات هذا الموظف فور فتح الشاشة
    LaunchedEffect(employee.id) {
        viewModel.loadTransactions(employee.id)
    }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("مالية: ${employee.name}") },
                navigationIcon = {
                    TextButton(onClick = onBackClick) { Text("رجوع") }
                }
            )
        }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .padding(paddingValues)
                .padding(16.dp)
                .fillMaxSize()
        ) {
            // كارت تفاصيل الموظف الأساسية
            Card(modifier = Modifier.fillMaxWidth()) {
                Column(modifier = Modifier.padding(16.dp)) {
                    Text("الموظف: ${employee.name}", style = MaterialTheme.typography.titleMedium)
                    Text("الأجر اليومي الحالي: ${employee.dailyWage} د.ع", style = MaterialTheme.typography.bodyMedium)
                }
            }

            Spacer(modifier = Modifier.height(16.dp))

            // حقول إضافة سلفة أو مسحوبات جديدة
            OutlinedTextField(
                value = amount,
                onValueChange = { amount = it },
                label = { Text("المبلغ المسحوب (د.ع)") },
                modifier = Modifier.fillMaxWidth()
            )
            Spacer(modifier = Modifier.height(8.dp))
            OutlinedTextField(
                value = notes,
                onValueChange = { notes = it },
                label = { Text("ملاحظات (سلفة، شرش، إلخ...)") },
                modifier = Modifier.fillMaxWidth()
            )
            Spacer(modifier = Modifier.height(16.dp))
            Button(
                onClick = {
                    if (amount.isNotBlank()) {
                        viewModel.addTransaction(
                            empId = employee.id,
                            amount = amount.toDoubleOrNull() ?: 0.0,
                            notes = notes
                        )
                        amount = ""
                        notes = ""
                    }
                },
                modifier = Modifier.fillMaxWidth()
            ) {
                Text("تسجيل العملية المالية")
            }

            Spacer(modifier = Modifier.height(24.dp))
            Text("سجل المسحوبات والسلف:", style = MaterialTheme.typography.titleSmall)
            Spacer(modifier = Modifier.height(8.dp))

            // قائمة عرض العمليات المالية السابقة
            LazyColumn(modifier = Modifier.fillMaxWidth()) {
                items(viewModel.currentTransactions.value) { trans ->
                    Card(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(vertical = 4.dp),
                        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surfaceVariant)
                    ) {
                        Row(
                            modifier = Modifier
                                .padding(12.dp)
                                .fillMaxWidth(),
                            horizontalArrangement = Arrangement.SpaceBetween
                        ) {
                            Column {
                                Text(text = "المبلغ: ${trans.amount} د.ع", style = MaterialTheme.typography.bodyLarge)
                                if (trans.notes.isNotBlank()) {
                                    Text(text = "البيان: ${trans.notes}", style = MaterialTheme.typography.bodyMedium)
                                }
                            }
                            Text(
                                text = dateFormatter.format(Date(trans.transactionDate)),
                                style = MaterialTheme.typography.bodySmall
                            )
                        }
                    }
                }
            }
        }
    }
}
