package com.example.myapplication

import java.time.LocalTime

interface Clock {
    fun now(): LocalTime
}

class RealClock: Clock {
    override fun now(): LocalTime = LocalTime.now()
}
