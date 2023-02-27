package com.example.plugins

import com.example.models.Activity
import com.example.models.activityStorage
import io.ktor.http.*
import io.ktor.server.routing.*
import io.ktor.server.response.*
import io.ktor.server.application.*
import io.ktor.server.request.*

fun Application.configureRouting() {
    routing {
        get("/") {
            call.respondText("Activities")
        }

        route("/activities") {
            get {
                takeIf { activityStorage.isNotEmpty() }?.let {
                    call.respond(activityStorage)
                } ?: call.respondText("No activies found", status = HttpStatusCode.OK)
            }

            get("{id?}") {
                val id = call.parameters["id"] ?: return@get call.respondText(
                    "Missing id", status = HttpStatusCode.BadRequest
                )

                val activity = activityStorage.find { it.id == id.toInt() } ?: return@get call.respondText(
                    "No activity with id $id", status = HttpStatusCode.NotFound
                )

                call.respond(activity)
            }

            post {
                val activity = call.receive<Activity>()
                activityStorage.add(activity.newEntry())
                call.respondText("Activity stored correctly", status = HttpStatusCode.Created)
            }

            put("{id?}") {
                val id = call.parameters["id"] ?: return@put call.respond(HttpStatusCode.BadRequest)
                val activity = call.receive<Activity>()

                activityStorage.find { it.id == id.toInt() } ?: return@put call.respondText(
                    "No activity with id $id", status = HttpStatusCode.NotFound
                )

                activityStorage.replaceAll {
                    it.copy(
                        title = activity.title,
                        description = activity.description,
                        status = activity.status
                    )
                }

                call.respondText("Activity updated correctly", status = HttpStatusCode.OK)
            }

            delete("{id?}") {
                val id = call.parameters["id"] ?: return@delete call.respond(HttpStatusCode.BadRequest)

                if (activityStorage.removeIf { it.id == id.toInt() }) {
                    call.respondText(
                        "Activity removed correctly", status = HttpStatusCode.Accepted
                    )
                } else {
                    (call.respondText("Not Found", status = HttpStatusCode.NotFound))
                }

            }
        }
    }
}
