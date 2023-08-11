package com.example.myapplication

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import java.time.LocalTime
import java.util.Date
import kotlin.concurrent.fixedRateTimer

class MainViewModel(private val clock: Clock): ViewModel() {
    private val _time: MutableStateFlow<LocalTime> = MutableStateFlow(clock.now())
    val time: StateFlow<LocalTime> = _time.asStateFlow()

    init {
        viewModelScope.launch {
            fixedRateTimer(period = 60, startAt = Date()) {
                _time.value = clock.now()
            }
        }
    }

    class Factory(private val clock: Clock): ViewModelProvider.NewInstanceFactory() {
        override fun <T : ViewModel> create(modelClass: Class<T>): T {
            if (modelClass.isAssignableFrom(MainViewModel::class.java)) {
                return MainViewModel(clock) as T
            }

            throw IllegalArgumentException("MainViewModel is required")
        }
    }
}
