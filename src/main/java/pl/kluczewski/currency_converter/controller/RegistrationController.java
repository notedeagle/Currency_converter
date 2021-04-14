package pl.kluczewski.currency_converter.controller;

import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;
import pl.kluczewski.currency_converter.config.security.RegistrationCredentials;
import pl.kluczewski.currency_converter.service.RegistrationService;

@RestController
@CrossOrigin
@AllArgsConstructor
public class RegistrationController {

    private final RegistrationService registrationService;

    @PostMapping("/register")
    public void register(@RequestBody RegistrationCredentials request) {
        registrationService.register(request);
    }

    @GetMapping("/confirm")
    public void confirmToken(@RequestParam("token") String token) {
        registrationService.confirmToken(token);
    }
}
