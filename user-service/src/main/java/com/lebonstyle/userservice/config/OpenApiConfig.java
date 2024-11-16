package com.lebonstyle.userservice.config;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.info.Contact;

@OpenAPIDefinition(
        info = @Info(
                title = "User API",
                version = "1.0",
                description = "API documentation for managing Users",
                contact = @Contact(name = "Your Name", email = "youremail@example.com")
        )
)
public class OpenApiConfig {
}
