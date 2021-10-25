package co.com.disney.config;

import org.modelmapper.ModelMapper;
import org.reactivecommons.utils.ObjectMapperImp;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;

import co.com.disney.model.gateways.CharacterGateway;
import co.com.disney.model.gateways.MovieGateway;
import co.com.event.usecase.disney.CharacterUseCase;
import co.com.event.usecase.disney.MovieUseCase;


@Configuration
@ComponentScan(basePackages = "co.com.disney.usecase",
includeFilters = {
		@ComponentScan.Filter(type = FilterType.REGEX, pattern = "^.+UseCase$")
},
useDefaultFilters = false)
public class UseCasesConfig {

	@Bean
	public CharacterUseCase createCharacterUseCase(CharacterGateway gateway) {
		return new CharacterUseCase(gateway);
	}

	@Bean
	public MovieUseCase createMovieUseCase(MovieGateway gateway) {
		return new MovieUseCase(gateway);
	}


	@Bean
	public ObjectMapperImp objectMapper() {
		return new ObjectMapperImp();
	}

}

