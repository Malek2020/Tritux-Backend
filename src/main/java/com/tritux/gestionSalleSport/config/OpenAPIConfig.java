package com.tritux.gestionSalleSport.config;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.OpenAPI;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

public class OpenAPIConfig {
     @Configuration
    public class SpringDocConfig {
        @Bean
        public OpenAPI springShopOpenAPI() {
            return new OpenAPI()
                    .info(infoAPI());

        }

        public Info infoAPI() {
            return new Info().title("Documentation")
                    .description("Tritux")
                    .contact(contactAPI());

        }

        public Contact contactAPI() {
            contact contact = new Contact().name("Stage Tritux")
                    .email("malek.azaouzi@esprit.tn")
                    .url("https://www.linkedin.com/in/**********/");
            return contact;
        }
    }

}
