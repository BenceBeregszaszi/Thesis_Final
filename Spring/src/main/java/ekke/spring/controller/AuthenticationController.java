package ekke.spring.controller;

import ekke.spring.dto.AuthenticationRequestDto;
import ekke.spring.dto.TokenPairDto;
import ekke.spring.dto.UserDto;
import ekke.spring.service.UserService;
import ekke.spring.service.authentication.AuthenticationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AuthenticationController extends ControllerBase {

    @Autowired
    private UserService userService;

    @Autowired
    private AuthenticationService authenticationService;

    @PostMapping("/authentication/authenticate")
    public ResponseEntity<TokenPairDto> authenticate(final AuthenticationRequestDto authRequestDto){
        return ResponseEntity.ok(authenticationService.authenticate(authRequestDto));
    }

    @PostMapping("/authentication/register")
    public ResponseEntity<Void> register(final UserDto userDto) {
        userService.add(userDto);
        return ResponseEntity.ok().build();
    }


    @GetMapping("/authentication/refresh-token")
    public ResponseEntity<TokenPairDto> refreshToken(final TokenPairDto tokenPairDto) {
        return ResponseEntity.ok(authenticationService.refreshToken(tokenPairDto.getRefreshToken()));
    }
}
