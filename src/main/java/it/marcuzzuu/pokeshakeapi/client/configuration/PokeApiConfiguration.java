package it.marcuzzuu.pokeshakeapi.client.configuration;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties(prefix = "poke-api")
@Data
public class PokeApiConfiguration
{
	private String baseEndPoint;
	private String speciesResource;
}
