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
-   **Service Layer Pattern**: The application separates concerns:
    -   `FlightService`: Handles business logic (searching, booking).
    -   `Main`: Handles user interaction and input.
    -   `Flight` / `Reservation`: Data models (POJOs).
-   **Pre-Seeded Data**: The application initializes with dummy data (flights to New York and London) to allow immediate testing without manual data entry.

## Real-Life Considerations

While this application demonstrates the core logic, a production-grade system would require significant enhancements:

1.  **Persistence**: Replace in-memory lists with a relational database (e.g., PostgreSQL, MySQL) to save data permanently.
2.  **Concurrency**: Implement thread-safe mechanisms (e.g., database transactions/locking) to prevent double-booking when multiple users try to reserve the last seat simultaneously.
3.  **Authentication & Authorization**: Add user login and roles (e.g., Admin vs. Customer) to secure the system.
4.  **Input Validation**: robust validation for all user inputs (dates, names, negative numbers) to prevent errors and security vulnerabilities.
5.  **Scalability**: Microservices architecture for high-demand components (e.g., separate Search Service vs. Booking Service).
6.  **Logging & Monitoring**: Integrate logging frameworks (SLF4J/Logback) instead of `System.out` for better debugging and production monitoring.
