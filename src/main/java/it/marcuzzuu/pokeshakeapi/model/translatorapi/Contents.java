package it.marcuzzuu.pokeshakeapi.model.translatorapi;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Contents
{
	private String translated;
	private String text;
	private String translation;
}
