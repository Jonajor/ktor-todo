package com.example.models

import kotlinx.serialization.Serializable
import java.util.concurrent.atomic.AtomicInteger

@Serializable
data class Activity(
    val id: Int? = null,
    val title: String,
    val description: String,
    val status: String
) {

    fun newEntry() = Activity(
        id = idCounter.getAndIncrement(),
        title = this.title,
        description = this.description,
        status = this.status
    )

    companion object {
        private val idCounter = AtomicInteger(1)
    }
}

val activityStorage = mutableListOf<Activity>()