# Flight Reservation System

A simple, console-based Flight Reservation System implemented in Java.

## Overview

This application allows users to:
1.  **Search Flights**: Find available flights by destination for the next day.
2.  **Book Flights**: Reserve seats on a specific flight.
3.  **View Reservations**: List all confirmed bookings.

## How to Run

### Prerequisites
- Java Development Kit (JDK) 8 or higher.

### Compilation
To compile the project and keep the source directory clean, run the following command from the project root:

```bash
javac -d out src/flight/*.java
```

### Execution
To run the application:

```bash
java -cp out flight.Main
```

## Design Decisions

-   **In-Memory Data Storage**: For simplicity and ease of setup, all data (flights, reservations) is stored in memory (`List` structures). Data is not persisted after the application exits.
-   **Console Interface**: A CLI (Command Line Interface) was chosen to focus on core business logic without the overhead of a GUI or web frontend.
-   **Separation of Concerns**: The application is divided into distinct layers:
    -   *Models* (`Flight`, `Reservation`): Pure POJOs (Plain Old Java Objects) representing the data.
    -   *Service Layer* (`FlightService`): Contains the business logic (searching, validating inventory, booking). This ensures that logic is not tightly coupled to the user interface.
    -   *Interface* (`Main`): Handles user input/output strictly.
-   **Immutability Strategy**: While Flight logic is mutable (decreasing seats), the `FlightService` returns new lists or copies of collections (e.g., `new ArrayList<>(reservations)`) rather than direct references to internal storage. This protects the internal data structure from unintended external modification.
-   **Java Streams**: utilzied the Java Stream API in `searchFlights` to provide a declarative, readable way to filter flights based on destination, date, and availability without complex nested loops.
-   **Exception Handling**: Specific exceptions (`IllegalArgumentException`, `IllegalStateException`) are thrown when business rules are violated (e.g., booking 0 seats or booking a full flight). These are caught in the UI layer to provide friendly error messages to the user without crashing the app.
-   **Pre-Seeded Data**: The application initializes with dummy data (flights to New York and London) to allow immediate testing without manual data entry.
