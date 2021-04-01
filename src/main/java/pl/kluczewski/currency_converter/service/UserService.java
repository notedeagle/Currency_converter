package pl.kluczewski.currency_converter.service;

import lombok.AllArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import pl.kluczewski.currency_converter.model.entity.ConfirmationToken;
import pl.kluczewski.currency_converter.model.entity.User;
import pl.kluczewski.currency_converter.repository.UserRepository;

import java.time.LocalDateTime;
import java.util.UUID;

@Service
@AllArgsConstructor
public class UserService implements UserDetailsService {

    private final static String USER_NOT_FOUND_MSG = "User with email %s not found";
    private final UserRepository userRepository;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;
    private final ConfirmationTokenService confirmationTokenService;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        return userRepository.findByEmail(email).orElseThrow(() ->
                new UsernameNotFoundException(String.format(USER_NOT_FOUND_MSG, email)));
    }

    public void singUpUser(User user) {
        boolean userExist = userRepository.findByEmail(user.getUsername())
                .isPresent();

        if(userExist) {
            throw new IllegalStateException("Email taken");
        }

        String encodedPassWord = bCryptPasswordEncoder
                .encode(user.getPassword());

        user.setPassword(encodedPassWord);

        userRepository.save(user);

        String token = UUID.randomUUID().toString();

        ConfirmationToken confirmationToken = new ConfirmationToken(token, LocalDateTime.now(),
                LocalDateTime.now().plusMinutes(15), user);

        confirmationTokenService.saveConfimationToken(confirmationToken);

        //TODO: SEND EMAIL
    }

    public void enableUser(String email) {
        userRepository.enableUser(email);
    }
}
