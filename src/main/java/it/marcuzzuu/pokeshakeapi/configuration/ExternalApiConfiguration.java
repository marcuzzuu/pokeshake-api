package it.marcuzzuu.pokeshakeapi.configuration;

import it.marcuzzuu.pokeshakeapi.client.configuration.ApiClientConfiguration;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.net.URI;

@Configuration
public class ExternalApiConfiguration
{
	@Bean("pokeApiConfig")
	private ApiClientConfiguration pokeApiConfig(@Value("${poke-api.endpoint}") final String endpoint)
	{
		return new ApiClientConfiguration(URI.create(endpoint));
	}

	@Bean("translatorApiConfig")
	public ApiClientConfiguration translatorApiConfig(@Value("${translator-api.endpoint}") final String endpoint)
	{
		return new ApiClientConfiguration(URI.create(endpoint));
	}
}
