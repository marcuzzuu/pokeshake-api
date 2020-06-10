package it.marcuzzuu.pokeshakeapi.client;

import it.marcuzzuu.pokeshakeapi.model.translatorapi.Translation;

import java.util.Optional;

public interface ITranslatorApi
{
	Optional<Translation> getTranslation(final String text, final String dialect);
}
