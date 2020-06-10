package it.marcuzzuu.pokeshakeapi.client;

import it.marcuzzuu.pokeshakeapi.model.translatorapi.Translation;

public interface ITranslatorApi
{
	Translation getTranslation(final String text, final String dialect);
}
