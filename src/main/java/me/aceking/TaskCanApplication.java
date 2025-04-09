package me.aceking;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class TaskCanApplication {

	public static void main(String[] args) {
		SpringApplication.run(TaskCanApplication.class, args);
	}

	// Springdoc OpenAPISwaggerUI configuration
	@Bean
	public OpenAPI customOpenAPI() {
		return new OpenAPI()
				.info(new Info()
						.title("TaskCan API")
						.version("1.0")
						.description("This is the API documentation for TaskCan application.")
						.termsOfService("http://swagger.io/terms/")
						.contact(new Contact()
								.name("Ace King")
								.url("http://taskcan.aceking.me/docs")
								.email("hackeem@aceking.me"))
						.license(new License()
								.name("Apache 2.0")
								.url("http://springdoc.org")));
	}

}
