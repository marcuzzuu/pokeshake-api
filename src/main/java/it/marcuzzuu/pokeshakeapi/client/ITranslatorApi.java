package it.marcuzzuu.pokeshakeapi.client;

import it.marcuzzuu.pokeshakeapi.model.translatorapi.TranslationResponse;

import java.util.Optional;

public interface ITranslatorApi
{
	Optional<TranslationResponse> getTranslation(final String text, final String dialect);
}
