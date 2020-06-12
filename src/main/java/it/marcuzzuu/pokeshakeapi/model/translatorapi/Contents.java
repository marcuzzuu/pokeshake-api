package it.marcuzzuu.pokeshakeapi.model.translatorapi;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Contents
{
	private String translated;
	private String text;
	private String translation;
}
