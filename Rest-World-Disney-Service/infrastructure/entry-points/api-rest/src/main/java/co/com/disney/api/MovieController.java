package co.com.disney.api;


import org.springframework.web.bind.annotation.RestController;

import co.com.event.usecase.disney.MovieUseCase;
import lombok.RequiredArgsConstructor;


@RestController
@RequiredArgsConstructor
public class MovieController {

	private final MovieUseCase movieUseCase;


}



