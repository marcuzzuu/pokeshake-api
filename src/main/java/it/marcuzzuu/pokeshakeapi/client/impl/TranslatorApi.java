package it.marcuzzuu.pokeshakeapi.client.impl;

import it.marcuzzuu.pokeshakeapi.client.ApiClient;
import it.marcuzzuu.pokeshakeapi.client.ITranslatorApi;
import it.marcuzzuu.pokeshakeapi.client.configuration.TranslatorApiConfig;
import it.marcuzzuu.pokeshakeapi.model.translatorapi.TranslationRequest;
import it.marcuzzuu.pokeshakeapi.model.translatorapi.TranslationResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.client.HttpClientErrorException;

import java.util.Optional;

@Slf4j
@Component
public class TranslatorApi extends ApiClient implements ITranslatorApi
{
	private final TranslatorApiConfig configuration;

	@Autowired
	public TranslatorApi(final TranslatorApiConfig configuration)
	{
		this.configuration = configuration;
	}

	@Override
	public Optional<TranslationResponse> getTranslation(final String text, final String dialect)
	{
		try
		{
			final ResponseEntity<TranslationResponse> response = this.restTemplate.exchange(buildURI(dialect), HttpMethod.POST, new HttpEntity<>(new TranslationRequest(text), DEFAULT_HEADERS), TranslationResponse.class);
			return Optional.ofNullable(response != null && response.getStatusCode().equals(HttpStatus.OK) ? response.getBody() : null);
		}catch (HttpClientErrorException ex)
		{
			log.warn("Got problems translating in '{}': {}", !StringUtils.isEmpty(dialect) ? dialect : this.configuration.getDefaultDialectResource(), ex.getResponseBodyAsString());
		}
		return Optional.empty();
	}

	private String buildURI(final String dialect)
	{
		return String.format("%s/%s", this.configuration.getEndpoint(), !StringUtils.isEmpty(dialect) ? dialect : this.configuration.getDefaultDialectResource());
	}
}
