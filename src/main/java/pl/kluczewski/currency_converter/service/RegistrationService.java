package pl.kluczewski.currency_converter.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pl.kluczewski.currency_converter.config.RegistrationCredentials;
import pl.kluczewski.currency_converter.model.entity.ConfirmationToken;
import pl.kluczewski.currency_converter.model.entity.User;
import pl.kluczewski.currency_converter.model.entity.UserRole;
import pl.kluczewski.currency_converter.validator.EmailValidator;

import java.time.LocalDateTime;

@Service
@AllArgsConstructor
public class RegistrationService {

    private final UserService userService;
    private final EmailValidator emailValidator;
    private final ConfirmationTokenService confirmationTokenService;

    public void register(RegistrationCredentials request) {

        boolean isValidEmail = emailValidator.test(request.getEmail());

        if(!isValidEmail) {
            throw new IllegalStateException("Email not valid");
        }

        userService.singUpUser(new User(
                request.getEmail(),
                request.getPassword(),
                UserRole.USER
        ));
    }

    @Transactional
    public void confirmToken(String token) {
        ConfirmationToken confirmationToken = confirmationTokenService
                .getToken(token)
                .orElseThrow(() ->
                        new IllegalStateException("Token not found"));

        if(confirmationToken.getConfirmationTime() != null) {
            throw new IllegalStateException("Email already confirmed");
        }

        LocalDateTime expiredTime = confirmationToken.getExpiredTime();

        if(expiredTime.isBefore(LocalDateTime.now())) {
            throw new IllegalStateException("Token expired");
        }

        confirmationTokenService.setConfirmationTime(token);
        userService.enableUser(confirmationToken.getUser().getEmail());
    }
}
