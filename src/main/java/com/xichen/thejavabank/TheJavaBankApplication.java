package com.xichen.thejavabank;

import io.swagger.v3.oas.annotations.ExternalDocumentation;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.License;
import io.swagger.v3.oas.annotations.info.Info;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@OpenAPIDefinition(
        info = @Info(
                title = "The Java Bank App",
                description = "Backend Rest APIs for TJ Bank",
                version = "v1.0",
                contact = @Contact(
                        name = "Xi Chen",
                        email = "connerside@outlook.com",
                        url = "https://github.com/sekirodie1000/" +
                                ""
                ),
                license = @License(
                        name = "The Java Academy",
                        url = "https://github.com/sekirodie1000/tj_bank_app"

                )
        ),
        externalDocs = @ExternalDocumentation(
                description = "The Java Bank App Documentation",
                url = "https://github.com/sekirodie1000/tj_bank_app"
        )
)
public class TheJavaBankApplication {

    public static void main(String[] args) {
        SpringApplication.run(TheJavaBankApplication.class, args);
    }

}
