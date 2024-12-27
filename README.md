# Spring Boot & Spring Security JWT Token Authentication

This project demonstrates how to implement JWT-based authentication using Spring Boot and Spring Security. Below is an overview of the functionality and usage.

## Features

1. User Registration: New users can register to the system.

2. JWT-Based Authentication: Generate an accessToken and refreshToken upon successful registration.

3. Protected API Endpoints: Access specific resources using the accessToken (via Bearer token in HTTP headers).

4. Token Refresh Mechanism: Refresh your accessToken when it expires using the refreshToken.

## Workflow

1. Register a User:
   - Endpoint: /register
   - Method: POST 
   - Request Body:
   ```bash
   {
     "username": "exampleUser",
     "password": "examplePassword"
   }
   ```
   - Response:
   ```bash
   {
    "accessToken": "<JWT_ACCESS_TOKEN>",
    "refreshToken": "<JWT_REFRESH_TOKEN>"
   }
   ```

2. Access Protected Resources:

   - Use the accessToken to authenticate and access protected endpoints. 
   - Example Endpoint: /employee/{id} 
     - Method: GET 
     - Headers:
       ```bash
       Authorization: Bearer <JWT_ACCESS_TOKEN>
       ```

3. Handle Expired Access Tokens:

   - If the accessToken expires, use the refreshToken to obtain a new accessToken.

   - Endpoint: /refresh-token

   - Method: POST

   - Request Body:
   ```json
   {
      "refreshToken": "<JWT_REFRESH_TOKEN>"
   }
   ```

   - Response:
   ```json
   {
     "accessToken": "<NEW_JWT_ACCESS_TOKEN>",
     "refreshToken": "<NEW_JWT_REFRESH_TOKEN>"
   }    
   ```

4. Reattempt Access:

   - Use the newly obtained accessToken to retry the previous request to the protected endpoint.

## How It Works

1. JWT Generation:

   - Upon registration, the system generates an accessToken and a refreshToken.

   - The accessToken is used for authentication and is valid for a short duration.

   - The refreshToken has a longer lifespan and is used to obtain new accessTokens when they expire.

2. Token Verification:

   - All protected endpoints require a valid accessToken passed as a Bearer token in the Authorization header.

   - If the accessToken is invalid or expired, the server returns a 401 Unauthorized response.

3. Token Refresh:

   - When the accessToken expires, the client can request a new one by sending the refreshToken to the /refresh-token endpoint.

## Running the Project

1. Clone the repository:
    ```bash
      git clone git@github.com:bedirhan-1/SpringSecurity-Jwt.git 
    ```

2. Navigate to the project directory:
    ```bash
    cd <project_directory>
    ```

3. Build and run the application:
    ```bash
    ./mvnw spring-boot:run 
    ```

4. Use an API testing tool like Postman or curl to interact with the application as described above.

## Dependencies
- Spring Boot
- Spring Security
- JPA Repository
- postgresql
- Spring Web
- jjwt-api
- jjwt-impl
- jjwt-jackson

## Notes

- Ensure secure storage of refreshTokens on the client-side.

- Consider implementing additional security measures, such as rate-limiting and IP restrictions, for token refresh endpoints.