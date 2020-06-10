package it.marcuzzuu.pokeshakeapi.client.impl;

import it.marcuzzuu.pokeshakeapi.client.ApiClient;
import it.marcuzzuu.pokeshakeapi.client.ITranslatorApi;
import it.marcuzzuu.pokeshakeapi.client.configuration.TranslatorApiConfig;
import it.marcuzzuu.pokeshakeapi.model.translatorapi.Translation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import java.util.Optional;

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
	public Optional<Translation> getTranslation(final String text, final String dialect)
	{
		final ResponseEntity<Translation> response = this.restTemplate.getForEntity(buildURI(dialect), Translation.class);
		return Optional.ofNullable(response != null && response.getStatusCode().equals(HttpStatus.OK) ? response.getBody() : null);
	}

	private String buildURI(final String dialect)
	{
		return String.format("%s/%s", this.configuration.getEndpoint(), !StringUtils.isEmpty(dialect) ? dialect : this.configuration.getDefaultDialectResource());
	}
}
