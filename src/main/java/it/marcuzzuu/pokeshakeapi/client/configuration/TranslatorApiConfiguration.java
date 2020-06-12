package it.marcuzzuu.pokeshakeapi.client.configuration;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Data
@Component
@ConfigurationProperties(prefix = "translator-api")
public class TranslatorApiConfiguration
{
	private String endpoint;
	private String defaultDialectResource;
}
