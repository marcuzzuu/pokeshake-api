package it.marcuzzuu.pokeshakeapi.client.impl;

import it.marcuzzuu.pokeshakeapi.client.ApiClient;
import it.marcuzzuu.pokeshakeapi.client.IPokeApi;
import it.marcuzzuu.pokeshakeapi.client.configuration.PokeApiConfiguration;
import it.marcuzzuu.pokeshakeapi.model.pokeapi.PokemonSpecies;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class PokeApi extends ApiClient implements IPokeApi
{
	public static final String DEFAULT_LANGUAGE = "en";
	private final PokeApiConfiguration configuration;

	@Autowired
	public PokeApi(final PokeApiConfiguration configuration)
	{
		this.configuration = configuration;
	}

	@Override
	public Optional<PokemonSpecies> getSpecies(final String name)
	{
		return null;
	}
}
