package it.marcuzzuu.pokeshakeapi.service.impl;

import com.github.benmanes.caffeine.cache.Cache;
import it.marcuzzuu.pokeshakeapi.client.IPokeApi;
import it.marcuzzuu.pokeshakeapi.client.ITranslatorApi;
import it.marcuzzuu.pokeshakeapi.client.impl.PokeApi;
import it.marcuzzuu.pokeshakeapi.client.impl.TranslatorApi;
import it.marcuzzuu.pokeshakeapi.model.PokemonDescription;
import it.marcuzzuu.pokeshakeapi.model.pokeapi.FlavorTextEntry;
import it.marcuzzuu.pokeshakeapi.model.pokeapi.PokemonSpecies;
import it.marcuzzuu.pokeshakeapi.service.IPokemonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.List;
import java.util.Optional;

@Service
public class PokemonService implements IPokemonService
{
	private final IPokeApi pokeApi;
	private final ITranslatorApi translatorApi;
	private final Cache<String,String> cache;

	@Autowired
	public PokemonService(final IPokeApi pokeApi, final ITranslatorApi translatorApi, final Cache<String, String> cache)
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
			String pokemonDescription = this.cache.getIfPresent(name);
			if(pokemonDescription==null)
			{
				this.pokeApi.getSpecies(name).map(this::extractMostRecentDescription).map(description->this.translatorApi.getTranslation(description, translateTo).orElse(null)).ifPresent(translation -> this.cache.put(name, translation.getContents().getTranslated()));
			}
			return Optional.of(new PokemonDescription(name, this.cache.getIfPresent(name)));
		}
		return Optional.empty();
	}

	// There are multiple descriptions for a given pokemon (based on the game version)
	// It's better to always get the last one if possible
	private String extractMostRecentDescription(final PokemonSpecies pokemonSpecies)
	{
		final List<FlavorTextEntry> textEntries = pokemonSpecies.getFlavorTextEntries();
		for(int i=textEntries.size()-1; i>=0; i--)
		{
			if(textEntries.get(i).getLanguage().getName().equals(PokeApi.DEFAULT_LANGUAGE))
			{
				return textEntries.get(i).getFlavorText();
			}
		}
		return null;
	}
}
