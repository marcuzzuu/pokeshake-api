package it.marcuzzuu.pokeshakeapi.model.translatorapi;

import lombok.Data;

@Data
public class TranslationResponse
{
	private SuccessDescription success;
	private Contents contents;
}
