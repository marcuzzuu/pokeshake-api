package it.marcuzzuu.pokeshakeapi.unit;

import it.marcuzzuu.pokeshakeapi.client.configuration.TranslatorApiConfiguration;
import it.marcuzzuu.pokeshakeapi.client.impl.PokeApi;
import it.marcuzzuu.pokeshakeapi.client.impl.TranslatorApi;
import it.marcuzzuu.pokeshakeapi.model.pokeapi.PokemonSpecies;
import it.marcuzzuu.pokeshakeapi.model.translatorapi.Contents;
import it.marcuzzuu.pokeshakeapi.model.translatorapi.TranslationRequest;
import it.marcuzzuu.pokeshakeapi.model.translatorapi.TranslationResponse;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.Assert;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import java.util.Optional;

import static org.mockito.BDDMockito.given;

@SpringBootTest
class TranslatorApiTest
{
	private final TranslatorApi translatorApi;
	private final RestTemplate restTemplate;

	@Autowired
	TranslatorApiTest(final TranslatorApiConfiguration configuration)
	{
		this.restTemplate = Mockito.mock(RestTemplate.class);
		this.translatorApi = new TranslatorApi(configuration, this.restTemplate);
	}

	@Test
	void getTranslationWithValidTextAndValidDialectAndSuccessfulResponseShouldReturnNotEmptyTranslationResponse()
	{
		final String text = "Charizard is a big pokemon !";
		final String dialect = "shakespeare";
		final String expectedTranslation = "Charizard's a big pokemon !";
		final TranslationResponse translationResponse = TranslationResponse.builder().contents(Contents.builder().text(text).translated(expectedTranslation).translation(dialect).build()).build();

		given(this.restTemplate.exchange(this.translatorApi.buildURI(dialect), HttpMethod.POST, new HttpEntity<>(new TranslationRequest(text), TranslatorApi.DEFAULT_HEADERS), TranslationResponse.class)).willReturn(new ResponseEntity<>(translationResponse, HttpStatus.OK));
		final Optional<TranslationResponse> returnedValue = this.translatorApi.getTranslation(text, dialect);

		Assert.isTrue(returnedValue.isPresent(), "Returned value is empty !");
		Assert.isTrue(returnedValue.get().getContents().getTranslated().equals(expectedTranslation) && returnedValue.get().getContents().getText().equals(text) && returnedValue.get().getContents().getTranslation().equals(dialect), "Returned value differs from expected !");
	}

	@Test
	void getTranslationWithValidTextAndInvalidDialectOrUnsuccessfulResponseShouldReturnEmpty()
	{
		final String text = "Charizard is a big pokemon !";
		final String dialect = "shakespeareans";

		given(this.restTemplate.exchange(this.translatorApi.buildURI(dialect), HttpMethod.POST, new HttpEntity<>(new TranslationRequest(text), TranslatorApi.DEFAULT_HEADERS), TranslationResponse.class)).willReturn(new ResponseEntity<>(HttpStatus.NOT_FOUND));
		final Optional<TranslationResponse> returnedValue = this.translatorApi.getTranslation(text, dialect);

		Assert.isTrue(!returnedValue.isPresent(), "Translation returned when it should be empty!");
	}

	@Test
	void getTranslationWithValidTextAndValidDialectAndTooManyRequestsShouldThrowException()
	{
		final String text = "Charizard is a big pokemon !";
		final String dialect = "shakespeare";
		boolean expectedExceptionThrown = false;

		given(this.restTemplate.exchange(this.translatorApi.buildURI(dialect), HttpMethod.POST, new HttpEntity<>(new TranslationRequest(text), TranslatorApi.DEFAULT_HEADERS), TranslationResponse.class)).willThrow(new HttpClientErrorException(HttpStatus.TOO_MANY_REQUESTS));
		try
		{
			this.translatorApi.getTranslation(text, dialect);
		}
		catch (HttpClientErrorException ex)
		{
			if (ex.getStatusCode().equals(HttpStatus.TOO_MANY_REQUESTS))
			{
				expectedExceptionThrown = true;
			}
		}

		Assert.isTrue(expectedExceptionThrown, "Exception is never been thrown!");
	}
}
