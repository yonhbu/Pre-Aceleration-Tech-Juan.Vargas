package co.com.disney.config;

import org.reactivecommons.utils.ObjectMapperImp;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;

import co.com.disney.model.gateways.CharacterGateway;
import co.com.disney.model.gateways.GenreGateway;
import co.com.disney.model.gateways.MovieGateway;
import co.com.disney.model.gateways.UserGatewayService;
import co.com.event.usecase.disney.CharacterUseCase;
import co.com.event.usecase.disney.GenreUseCase;
import co.com.event.usecase.disney.MovieUseCase;
import co.com.event.usecase.security.UserDetailUseCase;


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
	public GenreUseCase createGenreUseCase(GenreGateway gateway) {
		return new GenreUseCase(gateway);
	}


	@Bean
	public ObjectMapperImp objectMapper() {
		return new ObjectMapperImp();
	}
	
	@Bean
	public UserDetailUseCase userDetailUseCase (UserGatewayService userGatewayService) {
		return new UserDetailUseCase(userGatewayService);
	}

	

}

