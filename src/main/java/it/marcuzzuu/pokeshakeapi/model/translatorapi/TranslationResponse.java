package it.marcuzzuu.pokeshakeapi.model.translatorapi;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class TranslationResponse
{
	private SuccessDescription success;
	private Contents contents;
}
