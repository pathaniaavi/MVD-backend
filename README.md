# MVD-Backend

MVD-Backend is a Java 17 project built using Spring Boot. This README provides information on how to set up and run the project, as well as details on various commands and functionalities available.

## Project Description

MVD-Backend serves as the backend service for the MVDUI data dashboard application. It is designed to manage data operations and integrations required by the frontend, ensuring smooth data flow and processing.

## Prerequisites

Before you begin, ensure you have met the following requirements:

- Java 17 installed
- Minimum viable dataspace (MVD) configured and running
- Frontend service set up

To set up the Minimum Viable Dataspace (MVD), refer to the [MVD GitHub repository](https://github.com/eclipse-edc/MinimumViableDataspace).

To set up the frontend service, refer to the [MVD-UI repository](https://github.com/pathaniaavi/MVD-UI).

## Setup and Running

1. **Clone the repository:**

    ```bash
    git clone https://github.com/pathaniaavi/MVD-backend.git
    cd MVD-backend
    ```

2. **Build the project:**

    ```bash
    mvn clean install
    ```

3. **Run the application:**

    ```bash
    mvn spring-boot:run
    ```

    The backend service will start on `http://localhost:8080/`.

## Endpoints

The backend service provides several RESTful endpoints to support the frontend application. Detailed API documentation can be found [here](API_DOCUMENTATION_LINK).

## Further Help


For more details on the MVD-Backend project, visit the [MVD-Backend repository](https://github.com/pathaniaavi/MVD-backend).

For more information on the complete setup, refer to the [MVD-UI repository](https://github.com/pathaniaavi/MVD-UI) and the [Minimum Viable Dataspace repository](https://github.com/eclipse-edc/MinimumViableDataspace).
