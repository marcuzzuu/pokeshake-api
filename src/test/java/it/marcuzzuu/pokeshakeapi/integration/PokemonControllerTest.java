//package it.marcuzzuu.pokeshakeapi.integration;
//
//import com.fasterxml.jackson.databind.ObjectMapper;
//import it.marcuzzuu.pokeshakeapi.controller.PokemonController;
//import it.marcuzzuu.pokeshakeapi.service.impl.PokemonService;
//import org.junit.jupiter.api.Test;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.boot.test.mock.mockito.MockBean;
//import org.springframework.test.web.servlet.MockMvc;
//
//@SpringBootTest
//@WebMvcTest(PokemonController.class)
//class PokemonControllerTest
//{
//	@Autowired
//	private MockMvc mvc;
//	@MockBean
//	private PokemonService pokemonService;
//	@Autowired
//	private ObjectMapper mapper;
//
//	@Test
//	void getDescriptionWithValidNameShouldReturnShakespeareanDescriptionAnd200()
//	{
//
//	}
//
//	@Test
//	void getDescriptionWithInValidNameShouldReturnGivenNameAnd404()
//	{
//
//	}
//
//	@Test
//	void getDescriptionWithNoNameShouldReturn404()
//	{
//
//	}
//
//	@Test
//	void getDescriptionWithValidNameAndErrorsOccurredShouldReturn500()
//	{
//
//	}
//
//	@Test
//	void getDescriptionWithValidNameAndTooManyRequestsOccurredShouldReturn429()
//	{
//
//	}
//}
