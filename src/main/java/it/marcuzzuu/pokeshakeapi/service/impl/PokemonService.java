package it.marcuzzuu.pokeshakeapi.service.impl;

import com.github.benmanes.caffeine.cache.Cache;
import it.marcuzzuu.pokeshakeapi.client.IPokeApi;
import it.marcuzzuu.pokeshakeapi.client.ITranslatorApi;
import it.marcuzzuu.pokeshakeapi.client.impl.PokeApi;
import it.marcuzzuu.pokeshakeapi.client.impl.TranslatorApi;
import it.marcuzzuu.pokeshakeapi.model.PokemonDescription;
import it.marcuzzuu.pokeshakeapi.service.IPokemonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.Optional;

@Service
public class PokemonService implements IPokemonService
{
	private final IPokeApi pokeApi;
	private final ITranslatorApi translatorApi;
	private final Cache<String,PokemonDescription> cache;

	@Autowired
	public PokemonService(final IPokeApi pokeApi, final ITranslatorApi translatorApi, final Cache<String, PokemonDescription> cache)
	{
		this.pokeApi = pokeApi;
		this.translatorApi = translatorApi;
		this.cache = cache;
	}

	@Override
	public Optional<PokemonDescription> retrieveDescription(final String name, final String translateTo)
	{
		if(!StringUtils.isEmpty(name))
		{
			PokemonDescription pokemon = this.cache.getIfPresent(name);
			if(pokemon==null)
			{
				this.pokeApi.getSpecies(name);
			}
			return Optional.of(pokemon);
		}
		return Optional.empty();
	}
}
