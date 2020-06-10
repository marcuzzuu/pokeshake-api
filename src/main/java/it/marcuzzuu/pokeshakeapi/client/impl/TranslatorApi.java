package it.marcuzzuu.pokeshakeapi.client.impl;

import it.marcuzzuu.pokeshakeapi.client.ApiClient;
import it.marcuzzuu.pokeshakeapi.client.ITranslatorApi;
import it.marcuzzuu.pokeshakeapi.client.configuration.TranslatorApiConfig;
import it.marcuzzuu.pokeshakeapi.model.translatorapi.Translation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

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
		return null;
	}

}
