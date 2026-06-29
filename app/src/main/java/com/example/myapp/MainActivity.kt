package com.example.myapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.room.Room
import com.example.myapp.data.AppDatabase
import com.example.myapp.domain.AppRepository
import com.example.myapp.ui.MainNavigation
import com.example.myapp.ui.AppViewModel

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        
        // بناء قاعدة البيانات المحلية داخل الجهاز
        val database = Room.databaseBuilder(
            applicationContext,
            AppDatabase::class.java,
            "agency_database"
        ).allowMainThreadQueries() // لتسهيل العمليات السريعة محلياً
         .build()

        val repository = AppRepository(database)
        val viewModel = AppViewModel(repository)

        setContent {
            MainNavigation(viewModel = viewModel)
        }
    }
}
