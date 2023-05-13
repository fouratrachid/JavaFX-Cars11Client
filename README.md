# Car Rental Management System

This is a JavaFX application developed to manage car rentals for a car rental agency. The application is built using JavaFX, MySQL for the database, and sockets to enable communication between the client and the admin. This README file provides an overview of the application and instructions for setting it up and running.

## Features

- User-friendly interface for easy navigation and interaction.
- User roles: Client and Admin.
- Client Features:
  - Browse available cars.
  - Make car reservations.
  - View and manage personal bookings.
  - Chat with the admin for assistance.
- Admin Features:
  - Add, edit, and remove cars from the inventory.
  - Approve or reject client reservations.
  - View and manage all bookings.
  - Chat with clients for support.

## Prerequisites

Make sure you have the following software installed:

- Java Development Kit (JDK) 8 or later.
- MySQL Server.
- MySQL Connector/J (JDBC driver for MySQL).

## Installation and Setup

1. Clone or download the repository to your local machine.
2. Import the project into your preferred Java IDE (e.g., IntelliJ, Eclipse).
3. Create a MySQL database for the application.
4. Import the database schema from the provided SQL file (`database.sql`) into your MySQL database. This will create the necessary tables and initial data.
5. Update the database connection details in the `config.properties` file located in the `src` directory. Provide the correct values for your MySQL server, database name, username, and password.
6. Add the MySQL Connector/J JAR file to the project's dependencies. You can download it from the official MySQL website or use a dependency management tool like Maven or Gradle.
7. Build the project to ensure there are no compilation errors.

## Usage

1. Run the application by executing the main class (`Main.java`).
2. The login screen will appear.
3. For testing purposes, you can use the following credentials:
   - Admin:
     - Username: root
     - Password: root
   - Client:
     - Username: foufou
     - Password: foufou123
4. Once logged in, you will be presented with the appropriate dashboard based on your role (Admin or Client).
5. From the dashboard, you can navigate through various features of the application, such as browsing cars, making reservations, managing bookings, and accessing the chat functionality.
6. Enjoy using the Car Rental Management System!

## Troubleshooting

- If you encounter any issues related to the database connection, double-check the database connection details in the `config.properties` file.
- Ensure that the MySQL Server is running and accessible.
- Verify that you have imported the correct SQL file and the database schema is set up correctly.

## Contributing

Contributions to this project are welcome. If you encounter any bugs or have suggestions for new features, please create an issue or submit a pull request on the GitHub repository.

## License

This project is licensed under the [MIT License](LICENSE).

## Acknowledgments

- The project utilizes the JavaFX library for building the graphical user interface.
- MySQL is used as the database management system.
- Sockets are used for client-admin communication.
- Thanks to the open-source community for providing valuable resources and examples on JavaFX, MySQL, and socket programming.
