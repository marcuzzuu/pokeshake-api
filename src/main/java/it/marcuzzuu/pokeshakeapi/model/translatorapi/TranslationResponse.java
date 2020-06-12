package it.marcuzzuu.pokeshakeapi.model.translatorapi;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TranslationResponse
{
	private SuccessDescription success;
	private Contents contents;
}
