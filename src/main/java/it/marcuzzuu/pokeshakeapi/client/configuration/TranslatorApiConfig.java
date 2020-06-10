package it.marcuzzuu.pokeshakeapi.client.configuration;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.net.URI;

@Component
@ConfigurationProperties(prefix = "translator-api")
@Data
public class TranslatorApiConfig
{
	private final String baseEndPoint;
	private final String defaultDialectResource;
}
