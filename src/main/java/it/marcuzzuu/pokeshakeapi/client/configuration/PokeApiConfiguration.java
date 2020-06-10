package it.marcuzzuu.pokeshakeapi.client.configuration;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Data
@Component
@ConfigurationProperties(prefix = "poke-api")
public class PokeApiConfiguration
{
	private String endpoint;
	private String speciesResource;
}
