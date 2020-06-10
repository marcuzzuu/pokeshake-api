package it.marcuzzuu.pokeshakeapi.client.impl;

import it.marcuzzuu.pokeshakeapi.client.ITranslatorApi;
import it.marcuzzuu.pokeshakeapi.client.configuration.ApiClientConfiguration;
import it.marcuzzuu.pokeshakeapi.model.translatorapi.Translation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

public class TranslatorApi implements ITranslatorApi
{
	private final ApiClientConfiguration configuration;

	@Autowired
	public TranslatorApi(@Qualifier("translatorApiConfiguration") final ApiClientConfiguration configuration)
	{
		this.configuration = configuration;
	}

	@Override
	public Translation getTranslation(final String text, final String dialect)
	{
		return null;
	}
}
