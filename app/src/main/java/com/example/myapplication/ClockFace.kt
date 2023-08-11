package com.example.myapplication

import androidx.compose.foundation.Canvas
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.drawscope.Fill
import androidx.compose.ui.graphics.drawscope.Stroke
import java.time.LocalTime
import kotlin.math.cos
import kotlin.math.sin

@Composable
fun ClockFace(time: LocalTime, modifier: Modifier) {
    ClockBoard(modifier)
    HourHand(time = time, modifier = modifier)
    MinuteHand(time = time, modifier = modifier)
    SecondHand(time = time, modifier = modifier)
}

@Composable
fun HourHand(time: LocalTime, modifier: Modifier = Modifier) {
    val degreePerHour = 30f
    Canvas(modifier = modifier) {
        val deg = degreePerHour * (time.hour % 12) - 90
        val smallDeg = (time.minute / 59f) * degreePerHour
        val rad = (deg + smallDeg) * (Math.PI / 180).toFloat()
        val r = size.width / 5f * 1.8f
        drawLine(
            color = Color.Black,
            start = center,
            end = Offset(
                center.x + r * cos(rad),
                center.y + r * sin(rad)
            ),
            strokeWidth = 4f
        )
    }
}

@Composable
fun MinuteHand(time: LocalTime, modifier: Modifier = Modifier) {
    val degreePerMinute = 6f
    val deg = time.minute * degreePerMinute - 90f
    val smallDeg = time.second / 59f * degreePerMinute
    val rad = (deg + smallDeg) * (Math.PI / 180).toFloat()
    Canvas(modifier = modifier) {
        val r = size.width / 5f * 2.4f
        drawLine(
            color = Color.Black,
            start = center,
            end = Offset(
                center.x + r * cos(rad),
                center.y + r * sin(rad)
            ),
            strokeWidth = 4f
        )
    }
}

@Composable
fun SecondHand(time: LocalTime, modifier: Modifier = Modifier) {
    val degreePerSecond = 6f
    Canvas(modifier = modifier) {
        val deg = time.second * degreePerSecond - 90f
        val smallDeg = time.nano / 1000000f / 999f * degreePerSecond
        val rad = (deg + smallDeg) * (Math.PI / 180).toFloat()
        val r = size.width / 5f * 2.4f
        drawLine(
            color = Color.Red,
            start = center,
            end = Offset(
                center.x + r * cos(rad),
                center.y + r * sin(rad)
            ),
            strokeWidth = 4f
        )
    }
}

@Composable
fun ClockBoard(modifier: Modifier = Modifier) {
    Canvas(modifier = modifier) {
        val r = size.width / 2f
        val innerR = size.width / 5f
        drawCircle(
            color = Color.Black,
            center = center,
            style = Stroke(width = 2f)
        )

        drawCircle(
            color = Color.Black,
            center = center,
            radius = 8f,
            style = Fill
        )

        for (minute in 0..59) {
            val deg = 6 * minute
            val rad = deg * (Math.PI / 180).toFloat()
            if (deg % 30 == 0) {
                drawLine(
                    color = Color.Black,
                    start = Offset(
                        center.x + innerR * 2 * cos(rad),
                        center.y + innerR * 2 * sin(rad)
                    ),
                    end = Offset(
                        center.x + r * cos(rad),
                        center.y + r * sin(rad)
                    ),
                    strokeWidth = 4f
                )
            } else {
                drawLine(
                    color = Color.Gray,
                    start = Offset(
                        center.x + innerR * 2.2f * cos(rad),
                        center.y + innerR * 2.2f * sin(rad)
                    ),
                    end = Offset(
                        center.x + r * cos(rad),
                        center.y + r * sin(rad)
                    ),
                    strokeWidth = 2f
                )
            }
        }
    }
}
