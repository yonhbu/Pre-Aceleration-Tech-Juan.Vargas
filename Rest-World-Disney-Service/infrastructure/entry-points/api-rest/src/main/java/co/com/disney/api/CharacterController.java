package co.com.disney.api;


import org.springframework.web.bind.annotation.RestController;

import co.com.event.usecase.disney.CharacterUseCase;
import lombok.RequiredArgsConstructor;


@RestController
@RequiredArgsConstructor
public class CharacterController {

	private final CharacterUseCase characterUseCase;


}



