# Gmail Spring Boot

A Spring Boot application demonstrating integration with Gmail, providing functionality for sending and receiving emails through Gmail's API. This project is ideal for learning OAuth2 authentication, Spring Boot email handling, and integrating Google services in Java applications.

## Features

- **Send Emails**: Easily send emails using Gmail from your Spring Boot application.
- **Receive Emails**: Retrieve emails from your Gmail inbox using the Gmail API.
- **OAuth2 Authentication**: Securely authenticate with Gmail using OAuth2.
- **RESTful Endpoints**: Expose email operations as REST APIs for easy integration.
- **Configuration Driven**: Easily configure Gmail credentials and properties.

## Getting Started

### Prerequisites

- Java 8 or higher
- Maven or Gradle
- Google Cloud Project with Gmail API enabled
- OAuth2 credentials (Client ID & Secret)

### Setup

1. **Clone the repository:**
   ```bash
   git clone https://github.com/theskysid/gmail-springboot.git
   cd gmail-springboot
   ```

2. **Configure Gmail Credentials:**
   - Place your `credentials.json` (downloaded from Google Cloud Console) in the `src/main/resources` directory.
   - Set up environment variables or `application.properties` for Gmail configuration.

3. **Build the project:**
   ```bash
   mvn clean install
   ```

4. **Run the application:**
   ```bash
   mvn spring-boot:run
   ```

## Usage

- **Send Email Endpoint:**
  ```
  POST /api/email/send
  ```
  Body:
  ```json
  {
    "to": "recipient@example.com",
    "subject": "Hello!",
    "body": "This is a test email."
  }
  ```

- **Fetch Emails Endpoint:**
  ```
  GET /api/email/inbox
  ```

## Technologies Used

- Spring Boot
- Gmail API (Google)
- OAuth2
- Maven/Gradle

## Contributing

Pull requests are welcome! For major changes, please open an issue first to discuss what you would like to change.

## License

This project is licensed under the MIT License.

## Author

- [Sid](https://github.com/theskysid)

## References

- [Google Gmail API Documentation](https://developers.google.com/gmail/api)
- [Spring Boot Reference](https://spring.io/projects/spring-boot)
