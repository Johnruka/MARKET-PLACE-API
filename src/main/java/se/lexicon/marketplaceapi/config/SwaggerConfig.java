package se.lexicon.marketplaceapi.config;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import org.springframework.context.annotation.Configuration;

@Configuration
@OpenAPIDefinition(info = @Info(title = "Market place API", version = "0.1", description = "API Information"))
public class SwaggerConfig {
}
