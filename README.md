# TaskCan

TaskCan is a RESTful task management application built with Spring Boot, designed to streamline your task tracking and organization. Whether you're managing personal to-dos or collaborating on projects, TaskCan provides a user-friendly interface and essential features to keep you on top of your tasks.

## Features

* **Task Management:**
    * Create, read, update, and delete tasks.
    * Assign tasks to users.
    * Set due dates and priorities.
    * Organize tasks into projects.
    * Add tags for easy filtering and searching.

* **User Management:**
    * User registration and authentication.
    * Role-based access control (admin, user).

* **Notifications:**
    * Receive notifications for task assignments, updates, and due dates.

* **Team Collaboration:**
    * Create teams and assign tasks to team members.
    * Share projects and tasks with collaborators.

* **API-Driven:**
    * RESTful API endpoints for seamless integration with other applications or frontend frameworks.

## Technologies Used

* **Backend:**
    * Spring Boot
    * Spring Data JPA
    * Hibernate
    * MariaDB (or other supported databases)

* **Frontend:**
    * Thymeleaf (template engine)
    * HTML, CSS, JavaScript

* **Build Tool:**
    * Gradle

## Getting Started

1. **Prerequisites:**
   * Java Development Kit (JDK) 17 or later
   * Gradle
   * MariaDB (or your preferred database)

2. **Clone the repository:**
   ```bash
   git clone https://github.com/your-username/taskcan.git
   ```

3. **Configure database:**
   * Update the `application.properties` file with your database connection details.

4. **Build and run:**
   ```bash
   ./gradlew bootRun
   ```

5. **Access the application:**
   * Open your web browser and navigate to `http://localhost:8080`

## API Documentation

* The API documentation will be available at [https://taskcan.aceking.me/docs](https://taskcan.aceking.me/docs) once the application is running.


## License

This project is licensed under the MIT License - see the [LICENSE.md](LICENSE.md) file for details.

## Acknowledgements

* This project was inspired by the need for a simple yet effective task management tool.
* Thanks to the Spring Boot and Thymeleaf communities for their excellent documentation and support.
