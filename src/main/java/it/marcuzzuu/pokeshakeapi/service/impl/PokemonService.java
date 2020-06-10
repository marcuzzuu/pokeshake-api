package it.marcuzzuu.pokeshakeapi.service.impl;

import com.github.benmanes.caffeine.cache.Cache;
import it.marcuzzuu.pokeshakeapi.model.PokemonDescription;
import it.marcuzzuu.pokeshakeapi.service.IPokemonService;
import org.springframework.beans.factory.annotation.Autowired;

public class PokemonService implements IPokemonService
{
	private final Cache<String,PokemonDescription> cache;

	@Autowired
	public PokemonService(final Cache<String, PokemonDescription> cache)
	{
		this.cache = cache;
	}

	@Override
	public String retrieveDescription(final String name, final String translateTo)
	{
		return null;
	}
}
