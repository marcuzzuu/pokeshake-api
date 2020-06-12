package it.marcuzzuu.pokeshakeapi.unit;

import com.github.benmanes.caffeine.cache.Cache;
import it.marcuzzuu.pokeshakeapi.client.IPokeApi;
import it.marcuzzuu.pokeshakeapi.client.ITranslatorApi;
import it.marcuzzuu.pokeshakeapi.client.impl.PokeApi;
import it.marcuzzuu.pokeshakeapi.model.PokemonDescription;
import it.marcuzzuu.pokeshakeapi.model.pokeapi.FlavorTextEntry;
import it.marcuzzuu.pokeshakeapi.model.pokeapi.Language;
import it.marcuzzuu.pokeshakeapi.model.pokeapi.PokemonSpecies;
import it.marcuzzuu.pokeshakeapi.model.translatorapi.Contents;
import it.marcuzzuu.pokeshakeapi.model.translatorapi.TranslationResponse;
import it.marcuzzuu.pokeshakeapi.service.impl.PokemonService;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.util.Assert;
import org.springframework.util.StringUtils;

import java.util.Collections;
import java.util.Optional;

import static org.mockito.BDDMockito.given;


@SpringBootTest
class PokemonServiceTest
{
	private IPokeApi pokeApi;
	private ITranslatorApi translatorApi;
	private Cache<String, String> cache;
	private PokemonService service;

	@SuppressWarnings("unchecked")
	PokemonServiceTest()
	{
		this.pokeApi = Mockito.mock(IPokeApi.class);
		this.translatorApi = Mockito.mock(ITranslatorApi.class);
		this.cache = Mockito.mock(Cache.class);
		this.service = new PokemonService(pokeApi, translatorApi, cache);
	}

	@Test
	void retrieveDescriptionWithNotEmptyNameAndExistingPokemonWithEnglishTranslationAndWithFunTranslationShouldReturnFullOptional()
	{
		final String name = "charizard";
		final String translatedDescription = "Charizard's big pokemon";
		given(this.pokeApi.getSpecies(name)).willReturn(Optional.of(PokemonSpecies.builder().flavorTextEntries(Collections.singletonList(FlavorTextEntry.builder().flavorText("Charizard is a big pokemon").language(Language.builder().name(PokeApi.DEFAULT_LANGUAGE).build()).build())).build()));
		given(this.translatorApi.getTranslation("Charizard is a big pokemon", null)).willReturn(Optional.of(TranslationResponse.builder().contents(Contents.builder().translated(translatedDescription).build()).build()));
		given(this.cache.getIfPresent(name)).willReturn(translatedDescription);

		final Optional<PokemonDescription> description = this.service.retrieveDescription(name, null);
		Assert.isTrue(description.isPresent(),"Returned optional is empty");
		Assert.isTrue(!StringUtils.isEmpty(description.get().getName()) && description.get().getName().equals(name), "Name differs from expected value");
		Assert.isTrue(!StringUtils.isEmpty(description.get().getDescription()), "Description is empty");
	}

	@Test
	void retrieveDescriptionWithNotEmptyNameAndExistingPokemonWithEnglishTranslationAndWithoutFunTranslationShouldReturnGivenNameAndEmptyDescription()
	{
		final String name = "charizard";
		given(this.pokeApi.getSpecies(name)).willReturn(Optional.of(PokemonSpecies.builder().flavorTextEntries(Collections.singletonList(FlavorTextEntry.builder().flavorText("Charizard è un grande pokemon").language(Language.builder().name("it").build()).build())).build()));
		given(this.translatorApi.getTranslation("Charizard is a big pokemon", null)).willReturn(Optional.empty());

		final Optional<PokemonDescription> description = this.service.retrieveDescription(name, null);
		Assert.isTrue(description.isPresent(),"Returned optional is empty");
		Assert.isTrue(!StringUtils.isEmpty(description.get().getName()) && description.get().getName().equals(name), "Name differs from expected value");
		Assert.isTrue(StringUtils.isEmpty(description.get().getDescription()), "Description is not empty");

	}

	@Test
	void retrieveDescriptionWithNotEmptyNameAndExistingPokemonWithoutEnglishTranslationShouldReturnGivenNameAndEmptyDescription()
	{
		final String name = "charizard";
		given(this.pokeApi.getSpecies(name)).willReturn(Optional.of(PokemonSpecies.builder().flavorTextEntries(Collections.singletonList(FlavorTextEntry.builder().flavorText("Charizard è un grande pokemon").language(Language.builder().name("it").build()).build())).build()));
		given(this.translatorApi.getTranslation(null, null)).willReturn(Optional.empty());

		final Optional<PokemonDescription> description = this.service.retrieveDescription(name, null);
		Assert.isTrue(description.isPresent(),"Returned optional is empty");
		Assert.isTrue(!StringUtils.isEmpty(description.get().getName()) && description.get().getName().equals(name), "Name differs from expected value");
		Assert.isTrue(StringUtils.isEmpty(description.get().getDescription()), "Description is not empty");
	}

	@Test
	void retrieveDescriptionWithNotEmptyNameAndNotExistingPokemonShouldReturnGivenNameAndEmptyDescription()
	{
		final String name = "charizardos";
		given(this.pokeApi.getSpecies(name)).willReturn(Optional.empty());
		final Optional<PokemonDescription> description = this.service.retrieveDescription(name, null);

		Assert.isTrue(description.isPresent(), "Returned optional is empty");
		Assert.isTrue(!StringUtils.isEmpty(description.get().getName()) && description.get().getName().equals(name), "Name differs from expected value");
		Assert.isTrue(StringUtils.isEmpty(description.get().getDescription()), "Description is not empty");
	}

	@Test
	void retrieveDescriptionWithEmptyNameShouldReturnEmptyOptional()
	{
		final String name = "";
		Assert.isTrue(!this.service.retrieveDescription(name, null).isPresent(), "Returned optional is not empty!");
	}
}
