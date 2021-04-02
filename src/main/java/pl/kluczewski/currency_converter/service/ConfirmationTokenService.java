package pl.kluczewski.currency_converter.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import pl.kluczewski.currency_converter.model.entity.ConfirmationToken;
import pl.kluczewski.currency_converter.repository.ConfirmationTokenRepository;

import java.time.LocalDateTime;
import java.util.Optional;

@Service
@AllArgsConstructor
public class ConfirmationTokenService {

    private final ConfirmationTokenRepository confirmationTokenRepository;

    public void saveConfirmationToken(ConfirmationToken token) {
        confirmationTokenRepository.save(token);
    }

    public Optional<ConfirmationToken> getToken(String token) {
        return confirmationTokenRepository.findByToken(token);
    }

    public void setConfirmationTime(String token) {
        confirmationTokenRepository.updateConfirmationTime(token, LocalDateTime.now());
    }
}
