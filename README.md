# Ktor TODO App

This is a simple TODO app built using the Ktor web framework and Kotlin.

## Getting Started

To run the app, you will need to have the following installed on your machine:

- JDK 17 or later
- Gradle 6.7 or later

To start the app, run the following command in your terminal:

```bash
gradle run
```

Once the app is running, you can access it by navigating to http://localhost:8080/activities in your web browser.

### Features
This app includes the following features:

* List all activities
* Get a specific activity by ID
* Add a new activity
* Update an existing activity
* Delete an existing activity

### Plugins
This app uses the following Ktor plugins:

* Content Negotiation: for handling requests and responses in different formats (JSON, XML, etc.)
* kotlinx.serialization: for serializing and deserializing data classes
* Routing: for defining the app's routes and endpoints