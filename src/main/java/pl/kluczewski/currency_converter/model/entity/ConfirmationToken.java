package pl.kluczewski.currency_converter.model.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "confirmationTokens")
public class ConfirmationToken {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false)
    private String token;
    @Column(nullable = false)
    private LocalDateTime createdTime;
    @Column(nullable = false)
    private LocalDateTime expiredTime;
    private LocalDateTime confirmationTime;

    @ManyToOne
    @JoinColumn(
            nullable = false,
            name = "user_id"
    )
    private User user;

    public ConfirmationToken(String token, LocalDateTime createdTime, LocalDateTime expiredTime, User user) {
        this.token = token;
        this.createdTime = createdTime;
        this.expiredTime = expiredTime;
        this.user = user;
    }
}
